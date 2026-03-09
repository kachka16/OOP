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
```

## Command.java
``` java
```

## ConsoleCommand.java
``` java
```

## GenerateConsoleCommand.java
``` java
```

## Main.java
``` java
```

## Menu.java
``` java
```

## RestoreConsoleCommand.java
``` java
```

## SaveConsoleCommand.java
``` java
```

## UndoConsoleCommand.java
``` java
```

## ViewConsoleCommand.java
``` java
```

## 📷Скріншот виконання
![Приклад1](https://github.com/kachka16/OOP/blob/task-5/PR5/img/priklad1.png?raw=true)

![Приклад2](https://github.com/kachka16/OOP/blob/task-5/PR5/img/priklad2.png?raw=true)

![Приклад3](https://github.com/kachka16/OOP/blob/task-5/PR5/img/priklad3.png?raw=true)

![Приклад4](https://github.com/kachka16/OOP/blob/task-5/PR5/img/priklad4.png?raw=true)
