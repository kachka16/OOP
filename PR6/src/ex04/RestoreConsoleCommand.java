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
