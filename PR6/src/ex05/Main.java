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

public class Main {
    private View view = new ViewableTable().getView();
    private Menu menu = new Menu();
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
    public static void main(String[] args){
        Main main = new Main();
        main.run();
    }
}
