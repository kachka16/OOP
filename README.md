# Консольна програма для виводу аргументів командного рядка

## Постановка задачі

**Завдання:**

1. Підготувати сховище до розміщення проекту
2. Написати просту консольну програму (наприклад вивід на екран аргументів командної строки)
3. Прикріпити посилання на GIT та архівований проект

### Опис проекту

Цей проєкт демонструє простий консольний калькулятор на Java, який виконує базові арифметичні операції: додавання, віднімання, множення, ділення. Програма приймає введені дані з командного рядка, обробляє їх і виводить результат обчислення.

#### Program.java
```java
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
```

##### Приклад виконання

![Приклад виконання 1](https://github.com/kachka16/OOP/blob/task-1/PR1/img/result.png?raw=true)
)
