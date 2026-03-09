package ex04;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ex02.View;
import ex03.ViewTable;
    /** Консольна команда Enter new row;
     *  дозволяє вводити новий рядок, щоб підрахувати кількість голосних
     * шаблон Command
     * @author Левковська Марія
     * @version 1.0
     */

public class GenerateConsoleCommand implements ConsoleCommand{
    /** Об'єкт, що реалізує інтерфейс {@linkplain View};
     * обслуговує колекцію {@linkplain ex01.Item2d}*/
    private View view;
     /** Ініціалізує поле {@linkplain GenerateConsoleCommand#view}
     * @param view об'єкт {@linkplain View} */
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
