# Паралельнdа обробка елементів 

## Постановка задачі

### **Завдання:**

1. Паралельна обробка
   * Продемонструвати можливість паралельної обробки елементів колекції (пошук мінімуму, максимуму, обчислення середнього значення, відбір за критерієм, статистична обробка тощо).
2. Worker Thread
   * Управління чергою завдань (команд) реалізувати за допомогою шаблону Worker Thread.

### Структура проекту

![Структура проекту](https://github.com/kachka16/OOP/blob/task-6/PR6/img/structura.png?raw=true)

### Тестування проекту 

![Тест](https://github.com/kachka16/OOP/blob/task-6/PR6/img/test.png?raw=true)

## AvgCommand.java
``` java
package ex05;
import java.util.concurrent.TimeUnit;
import ex01.Item2d;
import ex02.ViewResult;
import ex04.Command;
    /**Задача, що використовується обробником потоку;
     * шаблон Worker Thread
     * @author Левковська Марія 
     * @version 1.0
     * @see Command
     * @see CommandQueue
     */
public class AvgCommand implements Command{
    /** Зберігає результат обробки колекції */
    private double result = 0.0;
     /** Прапорець готовності результату */
    private int progress = 0;
    /** Обслуговує колекцію об'єктів {@linkplain ex01.Item2d} */
    private ViewResult viewResult;
     /**Повертає поле {@linkplain AvgCommand#viewResult}
     * @return значення {@linkplain AvgCommand#viewResult}*/
    public ViewResult getViewResult(){
        return viewResult;
    }
    /**Встановлює поле {@linkplain AvgCommand#viewResult}
     * @param viewResult значення для {@linkplain AvgCommand#viewResult}
     * @return нове значення {@linkplain AvgCommand#viewResult}
     * */
    public ViewResult setViewResult(ViewResult viewResult){
        return this.viewResult = viewResult;
    }

     /**Ініціалізує поле {@linkplain AvgCommand#viewResult}
     * @param viewResult об'єкт класу {@linkplain ViewResult}
     */
    public AvgCommand(ViewResult viewResult){
        this.viewResult = viewResult;
    }
    /**Повертає результат
     * @return поле {@linkplain AvgCommand#result}
     */
    public double getResult(){
        return result;
    }
    /**Перевіряє готовність результату
     * @return false – якщо результат отримано, інакше – true
     * @see AvgCommand#result
     */
    public boolean running(){
        return progress <100;
    }
    /** Використовується обробником потоку {@linkplain CommandQueue};
     * шаблон Worker Thread*/
    @Override
    public void execute(){
        progress = 0;
        System.out.println("Average execute...");
        result = 0.0;
        int idx = 1, size = viewResult.getItems().size();
        for(Item2d item : viewResult.getItems()){
            result += item.getY();
            progress = idx *100 / size;
            if(size >=2 && idx++ % (size / 2) == 0){
                System.out.println("Average " + progress + "%");
            }
            try{
                TimeUnit.MILLISECONDS.sleep(2000/size);
            }
            catch (InterruptedException e){
                System.err.println(e);
            }
        }
        result /= size;
        System.out.println(" ~Average done. Result = " + String.format("%.2f",result));
        progress = 100;
    }
}


```

## CommandQueue.java
``` java
package ex05;
import java.util.Vector;
import ex04.Command;    
    /*** Створює обробник потоку, що виконує об'єкти з інтерфейсом
     * Command; шаблон
     * Worker Thread
     * @author Левковська Марія
     * @version 1.0
     * @see Command
     */
public class CommandQueue implements Queue{
    /** Черга задач */
    private Vector<Command> tasks;
     /** Прапорець очікування */
    private boolean waiting;
    /** Прапорець завершення роботи */
    private boolean shutdown;
    /** Встановлює прапорець завершення */
    public void shutdown(){
        shutdown = true;
    }
    /**Ініціалізація {@linkplain CommandQueue#tasks}
     * {@linkplain CommandQueue#waiting};
     * створює потік для класу {@linkplain CommandQueue.Worker}
     */
    public CommandQueue(){
        tasks = new Vector<Command>();
        waiting = false;
        new Thread(new Worker()).start();
    }
    /**Додає нову задачу до черги
     * @param r об'єкт типу {@linkplain Command}
     */
    public void put(Command r){
        tasks.add(r);
        if(waiting){
            synchronized (this){
                notifyAll();
            }
        }
    }
    /**Отримує задачу з черги
     * @return об'єкт {@linkplain Command}
     */
    public Command take(){
        if (tasks.isEmpty()){
            synchronized (this){
                waiting = true;
                try{
                    wait();
                }
                catch(InterruptedException ie){
                    waiting = false;
                }
            }
        }
        return (Command) tasks.remove(0);
    }
    /** Обслуговує чергу задач; шаблон
     * Worker Thread 
     * @author Левковська Марія
     * @version 1.0
     * @see Runnable
     */
    private class Worker implements Runnable{
        public void run(){
            while(!shutdown){
                Command r = take();
                r.execute();
            }
        }
    }
    
}

```

## ExecuteConsoleCommand.java
``` java
package ex05;
import java.util.concurrent.TimeUnit;
import ex02.View;
import ex02.ViewResult;
import ex04.ConsoleCommand;
    /**Консольна команда
     * Execute all threads;
     * шаблон Command
     * @author  Левковська марія
     * @version 1.0
     */
public class ExecuteConsoleCommand implements ConsoleCommand{
    /**Об'єкт, що реалізує інтерфейс {@linkplain View};
     * обслуговує колекцію об'єктів {@linkplain ex01.Item2d}
     */
    private View view;
     /**Повертає поле {@linkplain ExecuteConsoleCommand#view}
     * @return значення {@linkplain ExecuteConsoleCommand#view}
     */
    public View getView(){
        return view;
    }
    /**Встановлює поле {@linkplain ExecuteConsoleCommand#view}
     * @param view значення для {@linkplain ExecuteConsoleCommand#view}
     * @return нове значення {@linkplain ExecuteConsoleCommand#view}
     */
    public View setView(View view){
        return this.view = view;
    }
    /**Ініціалізує поле {@linkplain ExecuteConsoleCommand#view}
     * @param view об'єкт, що реалізує {@linkplain View}
     */
    public ExecuteConsoleCommand(View view){
        this.view =view;
    }
    @Override
    public char getKey(){
        return 'x';
    }
    @Override
    public String toString(){
        return "e'x'ecute";
    }
    @Override
    public void execute(){
        CommandQueue queue1 = new CommandQueue();
        CommandQueue queue2 = new CommandQueue();
        MaxCommand maxCommand = new MaxCommand((ViewResult)view);
        AvgCommand avgCommand = new AvgCommand((ViewResult)view);
        MinMaxCommand minMaxCommand = new MinMaxCommand((ViewResult)view);
        System.out.println("Execute all threads...");
        queue1.put(minMaxCommand);
        queue2.put(maxCommand);
        queue2.put(avgCommand);
        try{
          while(avgCommand.running() || maxCommand.running() || minMaxCommand.running()){
            TimeUnit.MILLISECONDS.sleep(100);
          }  
          queue1.shutdown();
          queue2.shutdown();
          TimeUnit.SECONDS.sleep(1);
        }
        catch(InterruptedException e){
            System.err.println(e);
        }
        System.out.println("All done.");
    }
}

```

## Main.java
``` java
package ex05;
import ex02.View;
import ex02.ViewableResult;
import ex03.ViewableTable;
import ex04.ChangeConsoleCommand;
import ex04.GenerateConsoleCommand;
import ex04.Menu;
import ex04.RestoreConsoleCommand;
import ex04.SaveConsoleCommand;
import ex04.UndoConsoleCommand;
import ex04.ViewConsoleCommand;
    /**Обчислення та відображення результатів; містить реалізацію статичного методу main()
     * @author Левковська Марія
     * @version 5.0
     * @see Main#main
     */
public class Main {
    /**Об'єкт, що реалізує інтерфейс {@linkplain View};
     * обслуговує колекцію об'єктів {@linkplain ex01.Item2d};
     * ініціалізується за допомогою Factory Method
     */
    private View view = new ViewableTable().getView();
    /**Об'єкт класу {@linkplain Menu};
     * макрокоманда (шаблон Command)
     */
    private Menu menu = new Menu();
    /**Обробка команд користувача*/
    public void run(){
        menu.add(new ViewConsoleCommand(view));
        menu.add(new  GenerateConsoleCommand(view));
        ChangeConsoleCommand ccm = new ChangeConsoleCommand(view);
        menu.add(ccm);
        menu.add(new UndoConsoleCommand(ccm));
        menu.add(new SaveConsoleCommand (view));
        menu.add(new RestoreConsoleCommand(view));
        menu.add(new ExecuteConsoleCommand(view));
        menu.execute();
    }

    /**Виконується під час запуску програми
     * @param args параметри запуску програми
     */
    public static void main(String[] args){
        Main main = new Main();
        main.run();
    }
}

```

## MaxCommand.java
``` java
package ex05;
import java.util.concurrent.TimeUnit;
import ex02.ViewResult;
import ex04.Command;
    /**Задача, що використовується обробником потоку;
     * шаблон Worker Thread
     * @author Левковська Марія
     * @version 1.0
     * @see Command
     * @see CommandQueue
     */
public class MaxCommand implements Command {
    /** Зберігає результат обробки колекції */
    private int result = -1;
    /** Прапорець готовності результату */
    private int progress = 0;
    /** Обслуговує колекцію об'єктів {@linkplain ex01.Item2d} */
    private ViewResult viewResult;
    /**Повертає поле {@linkplain MaxCommand#viewResult}
     * @return значення {@linkplain MaxCommand#viewResult}
     */
    public ViewResult getViewResult(){
        return viewResult;
    }
    /**Встановлює поле {@linkplain MaxCommand#viewResult}
     * @param viewResult значення для {@linkplain MaxCommand#viewResult}
     * @return нове значення {@linkplain MaxCommand#viewResult}
     */
    public ViewResult setViewResult(ViewResult viewResult){
        return this.viewResult = viewResult;
    }
    /**Ініціалізує поле {@linkplain MaxCommand#viewResult}
     * @param viewResult об'єкт класу {@linkplain ViewResult}
     */
    public MaxCommand(ViewResult viewResult){
        this.viewResult = viewResult;
    }
    /**Повертає результат
     * @return поле {@linkplain MaxCommand#result}
     */
    public int getResult(){
        return result;
    }
    /**Перевіряє готовність результату
     * @return false – якщо результат знайдено, інакше – true
     * @see MaxCommand#result
     */
    public boolean running(){
        return progress < 100;
    }
    /**Використовується обробником потоку {@linkplain CommandQueue};
     * шаблон Worker Thread
     */
    @Override
    public void execute(){
        progress = 0;
        System.out.println("Max execute...");
        int size = viewResult.getItems().size();
        result = 0;
        for(int idx = 1; idx <size; idx++){
            if(viewResult.getItems().get(result).getY() < viewResult.getItems().get(idx).getY()){
                result = idx;
            }
            progress = idx * 100/ size;
            if(size >=3 && idx % (size /3)==0){
                System.out.println("Max " + progress + "%");
            }
            try{
                TimeUnit.MILLISECONDS.sleep(3000/size);
            }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        System.out.println(" ~Max positive #" + result + " found: " + "\"" + viewResult.getItems().get(result).getX() + "\"" + " golosni = " + viewResult.getItems().get(result).getY());
        progress = 100;
    }
}
```

## MinMaxCommand.java
``` java
package ex05;
import java.util.concurrent.TimeUnit;
import ex01.Item2d;
import ex02.ViewResult;
import ex04.Command;
    /**Задача, що використовується обробником потоку;
     * шаблон Worker Thread
     * @author Левковська Марія
     * @version 1.0
     * @see Command
     * @see CommandQueue
     */
public class MinMaxCommand implements Command{
    /** Зберігає результат обробки колекції (мінімум) */
    private int resultMin = -1;
    /** Зберігає результат обробки колекції (максимум) */
    private int resultMax = -1;
    /** Прапорець готовності результату */
    private int progress = 0;
    /** Обслуговує колекцію об'єктів {@linkplain ex01.Item2d} */
    private ViewResult viewResult;
    /**Повертає поле {@linkplain MinMaxCommand#viewResult}
     * @return значення {@linkplain MinMaxCommand#viewResult}
     */
    public ViewResult getViewResult(){
        return viewResult;
    }
    /**Встановлює поле {@linkplain MinMaxCommand#viewResult}
     * @param viewResult значення для {@linkplain MinMaxCommand#viewResult}
     * @return нове значення {@linkplain MinMaxCommand#viewResult}
     */
    public ViewResult setViewResult(ViewResult viewResult){
        return this.viewResult = viewResult;
    }
    /**Ініціалізує поле {@linkplain MinMaxCommand#viewResult}
     * @param viewResult об'єкт класу {@linkplain ViewResult}
     */
    public MinMaxCommand(ViewResult viewResult){
        this.viewResult = viewResult;
    }

    public int getResult(){
        return resultMin;
    }
    /**Повертає результат мінімального значення
     * @return поле {@linkplain MinMaxCommand#resultMin}
     */
    public int getResultMin(){
        return resultMin;
    }
    /**Повертає результат максимального значення
     * @return поле {@linkplain MinMaxCommand#resultMax}
     */
    public int getResultMax(){
        return resultMax;
    }
     /**Перевіряє готовність результату
     * @return false – якщо результат отримано, інакше – true
     */
    public boolean running(){
        return progress < 100;
    }
    /**Використовується обробником потоку {@linkplain CommandQueue};
     * шаблон Worker Thread
     */
    public void execute(){
        progress = 0;
        System.out.println("MinMax executed...");
        int idx = 0 , size = viewResult.getItems().size();
        for(Item2d item : viewResult.getItems()){
                if((resultMax == -1) || (viewResult.getItems().get(resultMax).getY()< item.getY())){
                    resultMax = idx;
                }

                if((resultMin == -1) || (viewResult.getItems().get(resultMin).getY()> item.getY())){
                    resultMin = idx;
                }
            idx++;
            progress = idx * 100/size;
            if(size >=5 && idx %(size/5) == 0){
                System.out.println("MinMax "+ progress + "%");
            }
            try{
                TimeUnit.MILLISECONDS.sleep(5000/size);
            }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        System.out.print(" ~MinMax doxe. ");
        if(resultMin > -1){
            System.out.println("Min positive #" + resultMin + " found: " + "\"" + viewResult.getItems().get(resultMin).getX() + "\"" + " golosni = " + viewResult.getItems().get(resultMin).getY());
        }
        else{
            System.out.println(" ~Min positive not found");
        }
        if(resultMax > -1){
            System.out.println("Min positive #" + resultMax + " found: " + "\"" + viewResult.getItems().get(resultMax).getX() + "\"" + " golosni = " + viewResult.getItems().get(resultMax).getY());
        }
        else{
            System.out.println(" ~Max negative not found");
        }
        progress = 100;
    }
}

```

## Queue.java
``` java
package ex05;
import ex04.Command;
 /**Представляє методи для розміщення та вилучення завдань обробником потоку
  * шаблон Worker Thread
  * @author Левковська Марія
  * @version 1.0
  * @see Command
  */
public interface Queue {
    /**Додає нове завдання в чергу; 
     * шаблон Worker Thread
     * @param cmd завдання*/
    void put(Command cmd);
    /**Видаляє завдання з черги; 
     * шаблон Worker Thread
     * @return завдання, що видаляється*/
    Command take();
    
}
```

## MainTest.java
``` java
package ex05;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ex02.ViewResult;
    /**Тестування розроблених класів
     * @author Левковська Марія
     * @version 5.0
     * @see CommandQueue
     * @see MaxCommand
     * @see AvgCommand
     * @see MinMaxCommand
     */
public class MainTest {
    /** Кількість слів для тестування */
    private final static int N = 12;
    /** Об'єкт для зберігання результатів */
    private static ViewResult view = new ViewResult();
    /** Об'єкти команд для тестування */
    private static MaxCommand max1 = new MaxCommand(view);
    private static MaxCommand max2 = new MaxCommand(view);
    private static AvgCommand avg1 = new AvgCommand(view);
    private static AvgCommand avg2 = new AvgCommand(view);
    private static MinMaxCommand min1 = new MinMaxCommand(view);
    private static MinMaxCommand min2 = new MinMaxCommand(view);
    /** Черга команд (шаблон Worker Thread) */
    private CommandQueue queue = new CommandQueue();

    /**Виконується перед запуском тестів*/
    @BeforeClass
    public static void setUpBeforeClass(){
        view.viewInit("text for testing my MainTest. Different words just like interesting smart sad");
        assertEquals(N, view.getItems().size());
    }

    /**Виконується після завершення всіх тестів*/
    @AfterClass
    public static void tearDownAfterClass(){
        assertEquals(max1.getResult(),max2.getResult());
        assertEquals(avg1.getResult(),avg2.getResult(), 1e-10);
        assertEquals(min1.getResultMax(),min2.getResultMax());
        assertEquals(min1.getResultMin(),min2.getResultMin());
    }
    /**Перевірка основної функціональності класу {@linkplain MaxCommand}*/
    @Test
    public void testMax(){
        max1.execute();
        assertTrue(max1.getResult()> -1);
    }
    /**Перевірка основної функціональності класу {@linkplain MinMaxCommand}*/
    @Test
    public void testMin(){
        min1.execute();
        assertTrue(min1.getResultMin()> -1);
    }
    /**Перевірка основної функціональності класу {@linkplain AvgCommand}*/
    @Test
    public void testAvg(){
        avg1.execute();
        assertTrue(avg1.getResult() != 0.0);
    }
    /**Перевірка роботи {@linkplain CommandQueue}
     * з задачею {@linkplain MaxCommand}*/
    @Test
    public void testMaxQueue(){
        queue.put(max2);
        try{
            while(max2.running()){
                TimeUnit.MILLISECONDS.sleep(100);
            }
            queue.shutdown();
            TimeUnit.SECONDS.sleep(1);
        }
        catch(InterruptedException e){
            fail(e.toString());
        }
    }
    /**Перевірка роботи {@linkplain CommandQueue}
     * з задачею {@linkplain AvgCommand}*/
    @Test
    public void testAvgQueue(){
        queue.put(avg2);
        try{
            while (avg2.running()) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            queue.shutdown();
            TimeUnit.SECONDS.sleep(1);
        }
        catch(InterruptedException e){
            fail(e.toString());
        }
    }
    /**Перевірка роботи {@linkplain CommandQueue}
     * з задачею {@linkplain MinMaxCommand}*/
    @Test
    public void testMinQueue(){
        queue.put(min2);
        try{
            while (min2.running()) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            queue.shutdown();
            TimeUnit.SECONDS.sleep(1);
        }
        catch(InterruptedException e){
            fail(e.toString());
        }
    }

}

```

## 📷Скріншот виконання
![Приклад1](https://github.com/kachka16/OOP/blob/task-6/PR6/img/priklad1.png?raw=true)
