package OOP;
import java.util.Scanner;

public class Program{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("~Введiть перше значення: ");
        int n1 = scan.nextInt();
        System.out.print("~Введiть друге значення: ");
        int n2 = scan.nextInt();
         scan.nextLine();
        System.out.print("~Введiть знак: ");
         String scanChar = scan.nextLine();
        char znak = scanChar.charAt(0);

        switch (znak) {
            case '+':
                System.out.println("Результат: "+ (n1+n2) );
                break;
             case '-':
                System.out.println("Результат: "+ (n1-n2) );
                break;
            case '*':
                System.out.println("Результат: "+ (n1*n2) );
                break;
            case '/':
                System.out.println("Результат: "+ (n1/n2) );
                break;   
            default:
                System.out.println("Невiрний знак");
                break;
        }
        scan.close();
    }
}