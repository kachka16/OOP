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
