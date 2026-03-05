# Застосування шаблону проєктування Factory Method в ООП

## Постановка задачі

### **Завдання:**

1. Збереження результатів
   * Як основа використовувати вихідний текст проекту попередньої лабораторної роботи. Забезпечити розміщення результатів обчислень уколекції з можливістю збереження/відновлення.
2. Використання шаблону проєктування Factory Method
   * Використовуючи шаблон проектування Factory Method (Virtual Constructor), розробити ієрархію, що передбачає розширення рахунок додавання
нових відображуваних класів.
3. Інтерфейс фабрикованих об’єктів
   * Розширити ієрархію інтерфейсом "фабрикованих" об'єктів, що представляє набір методів для відображення результатів обчислень.
4. Виведення результатів
   * Реалізувати ці методи виведення результатів у текстовому виде.
5. Інтерфейс фабрики
   * Розробити тареалізувати інтерфейс для "фабрикуючого" методу.
   
## Структура проекту
![Структура проекту](https://github.com/kachka16/OOP/blob/task-3/PR3/img/structura.png?raw=true)

### Main.java
``` java
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
                System.out.println("====================================================\n \t\tENTER COMMAND...");
                System.out.print("'q'uit, 'v'iew, 'e'nter, 's'ave, 'r'estore: ");
                try{
                    s = in.readLine();
                }
                catch(IOException e){
                    System.out.println("~Error: "+ e);
                    System.exit(0);
                }
            }
            while (s.length() !=1);
            switch (s.charAt(0)) {
                case 'q':
                  case 'Q':
                    System.out.println("\t\tEXIT ");
                    break;
                case 'v':
                    case 'V':
                    System.out.println("~View current.");
                    view.viewShow();
                    break;
                case 'e':
                    case 'E':
                    System.out.print("~Enter rows: ");
                    try{
                    String x = in.readLine();
                    view.viewInit(x);
                    view.viewShow();
                    }
                     catch (IOException e){
                        System.out.print("~Input error: " + e);
                     }
                    break;
                case 's':
                    case 'S':
                    System.out.println("~Save current.");
                    try{
                        view.viewSave();
                    }
                    catch (IOException e){
                        System.out.println("~Serializaton error: " + e);
                    }
                    view.viewShow();
                    break;
                case 'r':
                    case 'R':
                    System.out.println("~Restore last saved");
                    try{
                        view.viewRestore();
                    }
                    catch(Exception e){
                     System.out.println("~Serialization error: "+ e);
                    }
                    view.viewShow();
                    break;
                    default:
                    System.out.println("~Wrong command. ");
            }
        } 
        while(s.charAt(0)!= 'q' || s.charAt(0)!= 'Q' );
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
```
### View.java
``` java
package ex02;

import java.io.IOException;

    /** Product(шаблон проектування Factory Method)
    * Інтерфейс "фабрикованих" об'єктів
    * Оголошує методи відображення результатів обчислень
    * @author Левковська Марія
    * @version 1.0
    */

public interface View {
    /** Відображає заголовок */
    public void viewHeader();

    /** Відображає основну частину */
    public void viewBody();

    /** Відображає завершення */
    public void viewFooter();

     /** Відображає об'єкт повністю */
    public void viewShow();

    /** Виконує ініціалізацію даних */
    public void viewInit(String rows);
    
     /** Зберігає дані для подальшого відновлення */
    public void viewSave() throws IOException;
     /** Відновлює раніше збережені дані */
    public void viewRestore() throws Exception;
}
```

### Viewable.java
``` java
package ex02;

    /**Creator(шаблону проектування Factory Method)
     * Оголошує метод, що "фабрикує" об'єкти
     * @author Левковська Марія
     * @version 1.0
     * @see Viewable#getView()
     */

public interface Viewable {
    /** Створює об'єкт, що реалізує {@linkplain View} */
    public View getView();
    
}
```
### ViewableResult.java
``` java
package ex02;

    /** ConcreteCreator (шаблон проектування Factory Method)
     * Реалізує метод, що "фабрикує" об'єкти
     * @author Левковська Марія
     * @version 1.0
     * @see Viewable
     * @see ViewableResult#getView()
     */

public class ViewableResult implements Viewable {
    
    /** Створює об'єкт відображення {@linkplain ViewResult} */
    @Override
    public View getView(){
        return new ViewResult();
    }
}
```

### Item2d.java(зміни в оформленні)
``` java
package ex01;
import java.io.Serializable;

public class Item2d implements Serializable{
    /**
     * Зберігає вхідний рядок та результат підрахунку голосних літер.
    * Реалізує інтерфейс {@link Serializable} для підтримки серіалізації
    * @author Левковська Марія
    * @version 1.0
    */

    /**
     * Довжина рядка
     * Поле позначене як {@code transient}, тому не серіалізується.
     * Після відновлення об'єкта значення буде 0
     */
    private transient int length_rows;
     /** Вхідний рядок (аргумент обчислення). */
    private String x;
    /** Кількість голосних у рядку. */
    private int y;

    /** Автоматично згенерований спеціальний ідентифікатор версії серіалізації*/
    private static final long serialVersionUID = 1L;
    
    public Item2d(){
        x = "";
        y = 0;
        length_rows = 0;
    } 

    /**
     * Ініціалізує поля значеннями.
     * @param x значення для {@linkplain Item2d#x}
     * @param y значення для {@linkplain Item2d#y}
     */
     public Item2d(String x, int y){
        this.x= x;
        this.y =y;
        this.length_rows = x.length();
    } 

     /**
     * Встановлює значення поля {@linkplain Item2d#x}.
     * Автоматично оновлює довжину рядка.
     *
     * @param x нове значення рядка
     */
    public void setX(String x){
        this.x = x;
        this.length_rows = x.length();
    }

    /**
     * Повертає значення {@linkplain Item2d#x}.
     *
     * @return поточний рядок
     */
    public String getX(){
        return x;
    }

    /**
     * Встановлює значення {@linkplain Item2d#y}.
     *
     * @param y кількість голосних
     */
    public void setY(int y){
       this.y = y;
    }

    /**
     * Повертає значення {@linkplain Item2d#y}.
     *
     * @return кількість голосних
     */
    public int getY(){
        return y;
    }

    /**
     * Встановлює значення {@linkplain Item2d#x}
     * та {@linkplain Item2d#y}.
     *
     * @param x новий рядок
     * @param y кількість голосних
     * @return поточний об'єкт
     */
    public Item2d setXY(String x, int y){
        this.x = x;
        this.y = y;
        this.length_rows = x.length();
        return this;
    }

    /**
     * Повертає текстове представлення об'єкта.
     * {@inheritDoc}
     */
        @Override
    public String toString(){
        return "----------------------------------------------------\nRows: " + x + "\nk: " + y + "\nLenght: " + length_rows ;
        
    }
    
     /**
     * Порівнює об'єкти за значеннями полів
     * {@linkplain Item2d#x} та {@linkplain Item2d#y}.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj){
        if(this ==obj)
            return true;
        if(obj==null)
            return false;
        if(getClass()!=obj.getClass())
            return false;
        Item2d other = (Item2d) obj;
        if(y != other.y)
            return false;
        if(!x.equals(other.x))
            return false;

        return true;
    }
}
```


