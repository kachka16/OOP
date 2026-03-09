package ex04;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ex02.View;
import ex03.ViewTable;
public class GenerateConsoleCommand implements ConsoleCommand{
    private View view;
    public GenerateConsoleCommand(View view){
        this.view = view;
    }
    public char getKey(){
        return 'e';
    }
    public String toString(){
        return "'e'nter";
    }
    public void execute(){
        String s = null;
        System.out.print("~Enter new row: ");     
         BufferedReader in = new BufferedReader(new InputStreamReader (System.in));   
          try{
            s = in.readLine();
            view.viewInit(s);
            view.viewShow();
            }
            catch(IOException e){
             System.out.println("~Error: "+ e);
            }
    }
}
