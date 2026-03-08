package ex04;
import ex01.Item2d;
import ex01.Calc;
import ex02.View;
import ex02.ViewResult;

public class ChangeConsoleCommand extends ChangeItemCommand implements ConsoleCommand{

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
    public void execute(){
        ViewResult vresult = (ViewResult) view;
        vresult.getItems().remove(vresult.getItems().size()-1);
       view.viewShow();
    }
}
