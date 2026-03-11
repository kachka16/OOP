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
