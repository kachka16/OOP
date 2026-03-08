package ex04;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;

import ex02.View;
import ex02.ViewResult;
import ex03.ViewableTable;

public class Application{
    private static Application instance = new Application();
    private Application(){}
    public static Application getInstance(){
        return instance;
    }
    private View view = new ViewableTable().getView();
    private Menu menu = new Menu();
    public void run(){
        new java.io.File("items.bin").delete();
        menu.add(new ViewConsoleCommand(view));
        menu.add(new  GenerateConsoleCommand(view));
        menu.add(new ChangeConsoleCommand(view));
        menu.add(new SaveConsoleCommand (view));
        menu.add(new RestoreConsoleCommand(view));
        menu.execute();
    }
}