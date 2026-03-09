package ex04;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ex02.View;
import ex03.ViewTable;
public class UndoConsoleCommand implements ConsoleCommand{
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
