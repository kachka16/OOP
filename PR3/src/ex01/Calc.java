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

