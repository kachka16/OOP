package ex01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main{
    private Calc calc = new Calc();
    private void menu(){
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
                    calc.show();
                    break;
                case 'e':
                    System.out.print("Enter rows: ");
                    try{
                    String x = in.readLine();
                    calc.init(x);
                    calc.show();
                    }
                     catch (IOException e){
                        System.out.print("Input error: " + e);
                     }
                    break;
                case 's':
                    System.out.println("Save current.");
                    try{
                        calc.save();
                    }
                    catch (IOException e){
                        System.out.println("Serializaton error: " + e);
                    }
                    calc.show();
                    break;
                case 'r':
                    System.out.println("Restore last saved");
                    try{
                        calc.restore();
                    }
                    catch(Exception e){
                     System.out.println("Serialization error: "+ e);
                    }
                    calc.show();
                    break;
                    default:
                    System.out.print("Wrong command. ");
            }
        } 
        while(s.charAt(0)!= 'q');
    }
    public static void main(String[] args){
        Main main = new Main();
        main.menu();
    }
}