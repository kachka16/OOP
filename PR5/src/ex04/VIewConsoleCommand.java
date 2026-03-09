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
