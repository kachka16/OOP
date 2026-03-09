# Консольний додаток для обробки колекцій

## Постановка задачі

### **Завдання:**

1. Undo Command
   * Реалізувати можливість скасування (undo) операцій (команд).
2. Макрокоманда
   * Продемонструвати поняття "макрокоманда"
3. Шаблон Singletone
   * При розробці програми використовувати шаблон Singletone.
4. Діалоговий інтерфейс
   * Забезпечити діалоговий інтерфейс із користувачем.
5. Тестування програми
   * Розробити клас для тестування функціональності програми.

### Структура проекту

![Структура проекту](https://github.com/kachka16/OOP/blob/task-5/PR5/img/structura.png?raw=true)

### Тестування проекту 

![Тест](https://github.com/kachka16/OOP/blob/task-5/PR5/img/test.png?raw=true)

## Application.java
``` java
package ex04;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;

import ex02.View;
import ex02.ViewResult;
import ex03.ViewableTable;
    /** Формує та відображає меню програми;
     * реалізує шаблон Singleton
     * @author Левковська Марія
     * @version 1.0*/
public class Application{
    /** Посилання на єдиний екземпляр класу Application;
     * шаблон Singleton
     * @see Application*/
    private static Application instance = new Application();
     /** Закритий конструктор;
     * шаблон Singleton
     * @see Application*/
    private Application(){}
    /** Повертає екземпляр класу Application;
     * шаблон Singleton
     * @see Application*/
    public static Application getInstance(){
        return instance;
    }
    /** Об'єкт, що реалізує інтерфейс {@linkplain View};
     * обслуговує колекцію об'єктів {@linkplain ex01.Item2d}*/
    private View view = new ViewableTable().getView();
    /** Об'єкт класу {@linkplain Menu};
     * макрокоманда (шаблон Command)*/
    private Menu menu = new Menu();
    /** Обробка команд користувача
     * @see Application*/
    public void run(){
        new java.io.File("items.bin").delete();
        menu.add(new ViewConsoleCommand(view));
        menu.add(new  GenerateConsoleCommand(view));
        ChangeConsoleCommand ccm = new ChangeConsoleCommand(view);
        menu.add(ccm);
        menu.add(new UndoConsoleCommand(ccm));
        menu.add(new SaveConsoleCommand (view));
        menu.add(new RestoreConsoleCommand(view));
        menu.execute();
    }
}
```

## ChangeConsoleCommand.java
``` java
package ex04;
import ex01.Item2d;
import ex01.Calc;
import ex02.View;
import ex02.ViewResult;
        /** Консольна команда
         * Delete last word;
         * шаблон Command
         * @author Левковська Марія
         * @version 1.0*/
public class ChangeConsoleCommand extends ChangeItemCommand implements ConsoleCommand{
    /** Збережене видалене слово для можливості відміни (undo)*/
    private Item2d deleteLastWord;
    /** Об'єкт, що реалізує інтерфейс {@linkplain View};
     * обслуговує колекцію об'єктів {@linkplain ex01.Item2d}*/
    private View view;
     /** Повертає поле {@linkplain ChangeConsoleCommand#view}
     * @return значення {@linkplain ChangeConsoleCommand#view}*/   
    public View getView(){
        return view;
    }
    /** Встановлює поле {@linkplain ChangeConsoleCommand#view}
     * @param view значення для {@linkplain ChangeConsoleCommand#view}
     * @return нове значення {@linkplain ChangeConsoleCommand#view}*/
    public View setView(View view){
        return this.view = view;
    }
    /** Ініціалізує поле {@linkplain ChangeConsoleCommand#view}
     * @param view об'єкт, що реалізує інтерфейс {@linkplain View}*/
    public ChangeConsoleCommand(View view){
        this.view = view;
    }
    public char getKey(){
        return'd';
    }
    public String toString(){
        return "'d'elete(last word)";
    }
     /** Відміняє останню операцію видалення*/
     public void undo(){
        ViewResult vresult = (ViewResult) view;
        if(deleteLastWord != null){
            vresult.getItems().add(deleteLastWord);
        }
        view.viewShow();
    }
    public void execute(){
        ViewResult vresult = (ViewResult) view;
        if(vresult.getItems().size()>0){
        deleteLastWord = vresult.getItems().remove(vresult.getItems().size()-1);
        System.out.println("~Delete last word");
        }
       view.viewShow();
    }
}

```

## ChangeItemCommand.java
``` java
package ex04;
import ex01.Item2d;
import ex01.Calc;
    /** Команда обробки елемента;
     * шаблон Command
     * використовується для обчислення кількості голосних у рядку
     * @author Левковська Марія
     * @version 1.0
     */
public class ChangeItemCommand implements Command{
    /** Оброблюваний об'єкт;
     * шаблон Command*/
    private Item2d item;
    /** Параметр команди*/
    private double offset;
    /** Встановлює поле {@linkplain ChangeItemCommand#item}
     * @param item значення для {@linkplain ChangeItemCommand#item}
     * @return нове значення {@linkplain ChangeItemCommand#item}*/
    public Item2d setItem(Item2d item){
        return this.item = item;
    }
    /** Повертає поле {@linkplain ChangeItemCommand#item}
     * @return значення {@linkplain ChangeItemCommand#item}*/
    public Item2d getItem(){
        return item;
    }
    public double setOffset(double offset){
        return this.offset = offset;
    }
    public double getOffset(){
        return offset;
    }
    /** Виконує команду;
     * обчислює кількість голосних у рядку*/
    public void execute(){
        String rows = item.getX();
        item.setY(Calc.calc(rows));
    }
}

```

## Command.java
``` java
package ex04;
    /** Інтерфейс команди або задачі;
     * шаблон Command
     * @author Левковська Марія
     * @version 1.0
     */
public interface Command {
     /** Виконання команди
     * шаблон Command*/
    public void execute();
}
```

## ConsoleCommand.java
``` java
package ex04;
    /** Інтерфейс консольної команди;
     * шаблон Command
     * @author Левковська Марія
     * @version 1.0*/
public interface ConsoleCommand extends Command {
     /** Гаряча клавіша команди
     * @return символ гарячої клавіші*/
    public char getKey();
}
```

## GenerateConsoleCommand.java
``` java
package ex04;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ex02.View;
import ex03.ViewTable;
    /** Консольна команда Enter new row;
     *  дозволяє вводити новий рядок, щоб підрахувати кількість голосних
     * шаблон Command
     * @author Левковська Марія
     * @version 1.0
     */

public class GenerateConsoleCommand implements ConsoleCommand{
    /** Об'єкт, що реалізує інтерфейс {@linkplain View};
     * обслуговує колекцію {@linkplain ex01.Item2d}*/
    private View view;
     /** Ініціалізує поле {@linkplain GenerateConsoleCommand#view}
     * @param view об'єкт {@linkplain View} */
    public GenerateConsoleCommand(View view){
        this.view = view;
    }
    public char getKey(){
        return 'e';
    }
    public String toString(){
        return "'e'nter";
    }
    public void execute(){
        String s = null;
        System.out.print("~Enter new row: ");     
         BufferedReader in = new BufferedReader(new InputStreamReader (System.in));   
          try{
            s = in.readLine();
            view.viewInit(s);
            view.viewShow();
            }
            catch(IOException e){
             System.out.println("~Error: "+ e);
            }
    }
}
```

## Main.java
``` java
package ex04;
    /** Запуск програми;
     * містить реалізацію статичного методу main()
     * @author Левковська Марія
     * @version 1.0
     * @see Main#main */
public class Main {
    /** Виконується при запуску програми;
     * викликає метод {@linkplain Application#run()}
     * @param args параметри запуску*/
    public static void main(String[] args){
        Application app = Application.getInstance();
        app.run();
    }
}
```

## Menu.java
``` java
package ex04; 
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import ex03.ViewTable;
import java.util.List; 
     /** Макрокоманда
     * (шаблон Command);
     * контейнер консольних команд
     * @see ConsoleCommand*/
public class Menu implements Command{
    /** Колекція консольних команд
     * @see ConsoleCommand*/
    private List<ConsoleCommand> menu = new ArrayList<ConsoleCommand>();
    ViewTable vtable = new ViewTable();
    /** Додає нову команду до колекції
     * @param command реалізує {@linkplain ConsoleCommand}
     * @return command*/
    public ConsoleCommand add(ConsoleCommand command){
        menu.add(command);
        return command;
    }
    public String toString(){
        System.out.println("\n==========================================================================\n\t\t\tKILKIST GOLOSNIKH");
        String s = "~Enter command:";
        for(ConsoleCommand c: menu){
            s+= c+", ";
        }
        s+= "'q'uit: ";
        return s;
    }

    public void execute(){
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        menu: while(true){
            do{
                System.out.print(this);
                try{
                    s = in.readLine();
                    System.out.println("==========================================================================");
                }
                catch(IOException e){
                    System.err.println("~Error: "+ e);
                    System.exit(0);
                }
            } while(s.length()!=1);
            char key = s.charAt(0);
            if(key=='q'){
                System.out.println("~Exit.");
                vtable.outLine();
                break menu;
            }
            for(ConsoleCommand c: menu){
                if(s.charAt(0) == c.getKey()){
                    c.execute();
                    continue menu;
                }
            }
            System.out.println("~Wrong command.");
            continue menu;
        }
    }
}
```

## RestoreConsoleCommand.java
``` java
package ex04;
import ex02.View;
    /** Консольна команда
     * Restore - відновлює збережені дані
     * шаблон Command
     * @author Левковська Марія
     * @version 1.0 */
public class RestoreConsoleCommand implements ConsoleCommand{
    /** Об'єкт {@linkplain View},
     * що обслуговує колекцію {@linkplain ex01.Item2d}*/
    private View view;
    public RestoreConsoleCommand(View view){
        this.view = view;
    }
    public char getKey(){
        return 'r';
    }
    public String toString(){
        return "'r'estore";
    }
    public void execute(){
        System.out.println("~Restore last saved");
        try{
            view.viewRestore();
        }
        catch(Exception e){
            System.err.println("~Serialization error: " + e);
        }
        view.viewShow();
    }
}
```

## SaveConsoleCommand.java
``` java
package ex04;
import java.io.IOException;
import ex02.View;
    /** Консольна команда
     * Save - зберігає поточний стан даних
     * шаблон Command
     * @author Левковська Марія
     * @version 1.0*/
public class SaveConsoleCommand implements ConsoleCommand{
     /** Об'єкт {@linkplain View},
     * що обслуговує колекцію {@linkplain ex01.Item2d}*/
    private View view;
    public SaveConsoleCommand(View view){
        this.view = view;
    }
    public char getKey(){
        return 's';
    }
    public String toString(){
        return "'s'ave";
    }
    public void execute(){
        System.out.println("~Save current.");
        try{
            view.viewSave();
        }
        catch(IOException e){
            System.err.println("~Serialization error: " + e );
        }
        view.viewShow();
    }
}

```

## UndoConsoleCommand.java
``` java
package ex04;
import java.io.IOException;
import ex02.View;
    /** Консольна команда
     * Undo - скасовує останню виконану операцію
     * шаблон Command
     * @author Левковська Марія
     * @version 1.0*/
public class UndoConsoleCommand implements ConsoleCommand{
    /** Команда, для якої виконується undo*/
    private ChangeConsoleCommand cmd;
    public UndoConsoleCommand(ChangeConsoleCommand cmd){
        this.cmd = cmd;
    }
    public char getKey(){
        return 'u';
    }
    public String toString(){
        return "'u'ndo";
    }
    public void execute(){
        System.out.println("~Undo last action");
        cmd.undo();
    }
}

```

## ViewConsoleCommand.java
``` java
package ex04;
import ex02.View;
import ex02.ViewResult;
    /** Консольна команда
     * View - відображає поточні дані
     * шаблон Command
     * @author Левковська Марія
     * @version 1.0*/
public class ViewConsoleCommand implements ConsoleCommand {
    /** Об'єкт {@linkplain View},
     * що обслуговує колекцію {@linkplain ex01.Item2d}*/
    private View view;
    public ViewConsoleCommand(View view){
        this.view = view;
    }
    public char getKey(){
        return 'v';
    }
    public String toString(){
        return "\n'v'iew";
    }
    public void execute(){
       System.out.println("~View current");
        view.viewShow();
    }
}
```

## 📷Скріншот виконання
![Приклад1](https://github.com/kachka16/OOP/blob/task-5/PR5/img/priklad1.png?raw=true)

![Приклад2](https://github.com/kachka16/OOP/blob/task-5/PR5/img/priklad2.png?raw=true)

![Приклад3](https://github.com/kachka16/OOP/blob/task-5/PR5/img/priklad3.png?raw=true)

![Приклад4](https://github.com/kachka16/OOP/blob/task-5/PR5/img/priklad4.png?raw=true)
