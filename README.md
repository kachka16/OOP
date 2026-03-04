# Класи та об'єкти

## Постановка задачі

### **Завдання:**

1. Серіалізація
   * Розробити клас, що серіалізується, для зберігання параметрів і результатів
обчислень. Використовуючи агрегування, розробити клас для знаходження рішення задачі. 
2.  Transient полів
    * Розробити клас для демонстрації в діалоговому режимі збереження та відновлення стану об'єкта, використовуючи серіалізацію. Показати особливості використання transient полів. 
3. Десеріалізації
   * Розробити клас для тестування коректності результатів обчислень та серіалізації/десеріалізації. Використовувати докладні коментарі для автоматичної генерації документації засобами javadoc.
4. індивідуальне завдання
   * Виконати індивідуальне завдання згідно номеру в списку. 12 варіант

## СТруктура проекту
![Структура проекту](https://github.com/kachka16/OOP/blob/task-2/PR2/img/structura.png?raw=true)

## Calc.java
```java
package ex01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
    * Містить реалізацію методів для обчислення кількості голосних та відображення результатів серіалізації
    *
    * @author Левковська Марія
    * @version 1.0
*/

public class Calc{
    /** Ім'я файла, що використовується при серіалізації */
    private static final String FNAME = "Item2d.bin";
   
    /** Зберігає результат обчислень. Об'єкт класу {@linkplain Item2d}*/
    private Item2d result;

    /**
     * Ініціалізує поле {@linkplain Calc#result}
     */
    public Calc(){
        result = new Item2d();
    }

    /**
     * Повертає поточне значення {@linkplain Calc#result}
     * @return посилання на об'єкт {@linkplain Item2d}
     */
    public Item2d getResult(){
        return result;
    }
    
     /**
     * Обчислює кількість голосних літер у рядку.
     * @param rows вхідний рядок
     * @return кількість голосних літер
     */
    public int calc(String rows){
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

     /**
     * Виконує обчислення та зберігає результат
     * у об'єкті {@linkplain Calc#result}
     * @param rows вхідний рядок
     */
   public void init(String rows){
    result.setX(rows);
    result.setY(calc(rows));
     }

     /** Виводить результат обчислень у консоль*/
     public void show(){
        System.out.println(result);
     }

      /**
     * Зберігає {@linkplain Calc#result} у файл {@linkplain Calc#FNAME}
     * @throws IOException якщо сталася помилка запису
     */
     public void save() throws IOException{
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(result);
        os.flush();
        os.close();
      }

      /**
     * Відновлює {@linkplain Calc#result} з файлу {@linkplain Calc#FNAME}
     * 
     * @throws Exception якщо сталася помилка читання або десеріалізації
     */
      public void restore() throws Exception {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        result = (Item2d)is.readObject();
        is.close();
      }
    }


```

## Item2d.java
```java
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
        return "Rows: " + x + "\nk: " + y + "\nLenght: " + length_rows;
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
## Main.java
``` java
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
```

## MainTest.java

``` java
package ex01;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import junit.framework.Assert;

public class MainTest{
    @Test
    public void testCalc(){
        Calc calc = new Calc();
        calc.init("Name");
        assertEquals(2, calc.getResult().getY());

        calc.init("Eye");
        assertEquals(3, calc.getResult().getY());

        calc.init("TV");
        assertEquals(0, calc.getResult().getY());
        
    }
    @Test
    public void testRestore(){
        Calc calc = new Calc();
        String x;
        int y;
        for(int ctr =0; ctr<1000; ctr++){
            x = "FirstRowTest";
            calc.init(x);
            y = calc.getResult().getY();
            try{
                calc.save();
            }
            catch (IOException e){
                Assert.fail(e.getMessage());
            }
            calc.init("SecondRowTest");
            try{
                calc.restore();
            }
            catch (Exception e){
                Assert.fail(e.getMessage());
            }
            assertEquals(y, calc.getResult().getY());
            assertEquals(x, calc.getResult().getX());
        }
    }
}
```
## Скріншот виконання
![Приклад1](https://github.com/kachka16/OOP/blob/task-2/PR2/img/priklad1.png?raw=true)

![Приклад2](https://github.com/kachka16/OOP/blob/task-2/PR2/img/priklad2.png?raw=true)
