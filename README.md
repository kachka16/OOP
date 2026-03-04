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

## Calc.java
```java
package ex01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
    @author Левковська Марія
*/
public class Calc{
    private static final String FNAME = "Item2d.bin";
    private Item2d result;
    public Calc(){
        result = new Item2d();
    }

    public Item2d getResult(){
        return result;
    }
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

   public void init(String rows){
    result.setX(rows);
    result.setY(calc(rows));
     }
     public void show(){
        System.out.println(result);
     }
     public void save() throws IOException{
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(result);
        os.flush();
        os.close();
      }

      public void restore() throws Exception {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        result = (Item2d)is.readObject();
        is.close();
      }
    }
```
