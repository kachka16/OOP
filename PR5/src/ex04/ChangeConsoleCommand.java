package ex04;
import ex01.Item2d;
import ex01.Calc;
import ex02.View;
import ex02.ViewResult;

public class ChangeConsoleCommand extends ChangeItemCommand implements ConsoleCommand{

    private Item2d deleteLastWord;
    private View view;
    public View getView(){
        return view;
    }
    public View setView(View view){
        return this.view = view;
    }
    public ChangeConsoleCommand(View view){
        this.view = view;
    }
    public char getKey(){
        return'd';
    }
    public String toString(){
        return "'d'elete(last word)";
    }
     public void undo(){
        ViewResult vresult = (ViewResult) view;
        if(deleteLastWord != null){
            vresult.getItems().add(deleteLastWord);
             System.out.println("Undo last action");
        }
        view.viewShow();
    }
    public void execute(){
        ViewResult vresult = (ViewResult) view;
        if(vresult.getItems().size()>0){
        deleteLastWord = vresult.getItems().remove(vresult.getItems().size()-1);
        System.out.println("Delete last word");
        }
       view.viewShow();
    }
}
