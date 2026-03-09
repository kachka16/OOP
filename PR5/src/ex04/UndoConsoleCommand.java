package ex04;
import java.io.IOException;
import ex02.View;
    /** Консольна команда
     * Undo - скасовує останню виконану операцію
     * шаблон Command
     * @author Левковська Марія
     * @version 1.0*/
public class UndoConsoleCommand implements ConsoleCommand{
    /** Команда, для якої виконується undo*/
    private ChangeConsoleCommand cmd;
    public UndoConsoleCommand(ChangeConsoleCommand cmd){
        this.cmd = cmd;
    }
    public char getKey(){
        return 'u';
    }
    public String toString(){
        return "'u'ndo";
    }
    public void execute(){
        System.out.println("~Undo last action");
        cmd.undo();
    }
}
