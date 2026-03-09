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
