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

### ViewResult
``` java
package ex02;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ex01.Item2d;
import ex01.Calc;

    /** ConcreteProduct (шаблон проектування Factory Method)
     * Виконує обчислення, збереження та відображення результатів
     * @author Левковська Марія
     * @version 1.0
     * @see View*/

public class ViewResult implements View {
    /** Ім'я файлу для серіалізації */
    private static final String FNAME = "items.bin";
    /** Колекція результатів обчислень */
    private ArrayList<Item2d> items = new ArrayList<Item2d>();
    /** Кількість елементів за замовчуванням */
    private static final int DEFAULT_NUM = 1;
     /** Конструктор за замовчуванням */
    public ViewResult(){
        this(DEFAULT_NUM);
    }

    /** Ініціалізує колекцію @param n кількість елементів*/
    public ViewResult(int n){
        for( int i = 0; i<n; i++){
            items.add(new Item2d());
        }
    }

    /** Отримати колекцію результатів
     * @return список {@link Item2d}*/
    public ArrayList<Item2d> getItems(){
         return items;
    }

    /** Ініціалізує результати обчислень
     * Рядок розбивається на слова, для кожного слова обчислюється кількість голосних за допомогою методу {@linkplain Calc#calc(String)} - item.setY(Calc.calc(w));
     * @param rows рядок слів для обробки
     */
    public void init(String rows){
        items.clear();
        String[] word = rows.split(" ");
        for(String w : word){
            Item2d item = new Item2d();
            item.setX(w);
            item.setY(Calc.calc(w));
            items.add(item);
            
        }
    }

    /** Реалізація {@link View#viewInit(String)}*/
    @Override
    public void viewInit(String rows){
        init(rows);
        
    }
    /** Реалізація {@link View#viewSave()} */
    @Override
    public void viewSave() throws IOException{
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(items);
        os.flush();
        os.close();
    }

    /** Реалізація {@link View#viewRestore()}*/
    @SuppressWarnings("unchecked")
    @Override
    public void viewRestore() throws Exception{
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        items = (ArrayList<Item2d>) is.readObject();
        is.close();
    }

    /** Реалізація {@link View#viewHeader()}*/
    @Override
    public void viewHeader(){
        System.out.println("\t \tResults: ");
    }

    /** Реалізація {@link View#viewBody()} */
    @Override
    public void viewBody(){
        for(Item2d item : items){
            System.out.println(item);
        }
    }
    /** Реалізація {@link View#viewFooter()} */
    @Override
    public void viewFooter(){
        System.out.println("----------------------------------------------------\n \t\tEND");
    }


     /** Реалізація {@link View#viewShow()}*/
    @Override
    public void viewShow(){
        viewHeader();
        viewBody();
        viewFooter();
    }
}
```

### Item2d.java(зміни -  оформлення виводу)
``` java
    public String toString(){
        return "----------------------------------------------------\nRows: " + x + "\nk: " + y + "\nLenght: " + length_rows ;

```
### Calc.java(зміни - перетворення методу на статичний)
``` java
 public static int calc(String rows){
    char[] litters_golosni = {'a','A','U','u','E','e','O','o','Y','y','i','I'};
    int k = 0;
     for(char r : rows.toCharArray()){
        for( char l : litters_golosni)
            if(r == l){
                k++;
            }
      }
     return k;
    }
```

## 📷Скріншот виконання
![Приклад1](https://github.com/kachka16/OOP/blob/task-3/PR3/img/priklad1.png?raw=true)

![Приклад2](https://github.com/kachka16/OOP/blob/task-3/PR3/img/priklad2.png?raw=true)

![Приклад3](https://github.com/kachka16/OOP/blob/task-3/PR3/img/priklad3.png?raw=true)
