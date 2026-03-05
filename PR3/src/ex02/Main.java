package ex02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

     /**
     * Виконання обчислень та відображення результатів.
     * Містить реалізацію статичного методу {@link Main#main(String[])}.
     * @author Левковська Марія
     * @version 2.0
     * @see Main#main(String[])
     */
public class Main{

    private View view;
    public Main(View view){
        this.view = view;
    }
   
    /** Відображає меню */
    protected void menu(){
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader (System.in));
        do{
            do{
                System.out.println("Enter command...");
                System.out.print("'q'uit, 'v'iew, 'e'nter, 's'ave, 'r'estore: ");
                try{
                    s = in.readLine();
                }
                catch(IOException e){
                    System.out.println("Error: "+ e);
                    System.exit(0);
                }
            }
            while (s.length() !=1);
            switch (s.charAt(0)) {
                case 'q':
                    System.out.println("Exit.");
                    break;
                case 'v':
                    System.out.println("View current.");
                    view.viewShow();
                    break;
                case 'e':
                    System.out.print("Enter rows: ");
                    try{
                    String x = in.readLine();
                    view.viewInit(x);
                    view.viewShow();
                    }
                     catch (IOException e){
                        System.out.print("Input error: " + e);
                     }
                    break;
                case 's':
                    System.out.println("Save current.");
                    try{
                        view.viewSave();
                    }
                    catch (IOException e){
                        System.out.println("Serializaton error: " + e);
                    }
                    view.viewShow();
                    break;
                case 'r':
                    System.out.println("Restore last saved");
                    try{
                        view.viewRestore();
                    }
                    catch(Exception e){
                     System.out.println("Serialization error: "+ e);
                    }
                    view.viewShow();
                    break;
                    default:
                    System.out.println("Wrong command. ");
            }
        } 
        while(s.charAt(0)!= 'q');
    }
    /**
     * Виконується при запуску програми.
     * Забезпечує роботу діалогового меню для введення рядка,підрахунку кількості голосних літер
     * збереження та відновлення результатів серіалізації.
     * @param args параметри командного рядка
     */
    public static void main(String[] args){
        Main main = new Main(new ViewableResult().getView());
        main.menu();
    }
}