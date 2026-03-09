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