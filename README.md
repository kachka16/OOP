# Система відображення табличних даних

## Постановка задачі

### **Завдання:**

1. Розширення ієрархії
   * За основу використовувати вихідний текст проекту попередньої лабораторної роботи Використовуючи шаблон проектування Factory Method
(Virtual Constructor), розширити ієрархію похідними класами, реалізують методи для подання результатів у вигляді текстової таблиці. Параметри відображення таблиці мають визначатися користувачем.
2. Демонстрація призначення методів
   * Продемонструвати заміщення (перевизначення, overriding), поєднання (перевантаження, overloading), динамічне призначення методів (Пізнє зв'язування, поліморфізм, dynamic method dispatch).
3. Діалогова взаємодія
   * Забезпечити діалоговий інтерфейс із користувачем.
4. Тестування програми
   * Розробити клас для тестування основної функціональності.
5. Документування коду javadoc
   * Використати коментарі для автоматичної генерації документації засобами javadoc.

### Структура проекту

![Структура проекту](https://github.com/kachka16/OOP/blob/task-4/PR4/img/structura.png?raw=true)

### Тестування проекту

![Структура проекту](https://github.com/kachka16/OOP/blob/task-4/PR4/img/test.png?raw=true)

## Main.java

``` java
package ex03;
import ex02.View;

    /**Обчислення та відображення результатів.
     *Містить реалізацію статичного методу main() 
     *@author Левковська Марія
     *@version 3.0
     *@see Main#main
     */
public class Main extends ex02.Main{

    /**Ініціалізує поле {@linkplain ex02.Main#view view}*/
    public Main(View view){
        super(view);
    }

    /**Виконується при запуску програми.
     *Викликає метод {@linkplain ex02.Main#menu menu()}
     @param args параметри командного рядка*/
    public static void main(String[] args){
        Main main = new Main(new ViewableTable().getView());
        main.menu();
    }
}

```

## ViewableTable.java

```java
package ex03;
import ex02.ViewableResult;
import ex02.View;

    /**ConcreteCreator (шаблон проектування Factory Method)
     * Оголошує метод, який створює об'єкти відображення
     * @author Левковська Марія
     * @version 1.0
     * @see ViewableResult
     * @see ViewableTable#getView()*/

public class ViewableTable extends ViewableResult {
    /**Створює об'єкт відображення {@linkplain ViewTable}
     * @return новий об'єкт класу ViewTable*/
    @Override
    public View getView(){
        return new ViewTable();
    }
}

```


## ViewTable.java

```java
package ex03;
import java.util.Formatter;
import ex01.Item2d;
import ex02.ViewResult;

    /**ConcreteProduct (шаблон проектування Factory Method)
     * Забезпечує виведення результатів у вигляді таблиці
     * @author Левковська Марія
     * @version 3.0
     * @see ViewResult*/

public class ViewTable extends ViewResult {
     /** Ширина таблиці за замовчуванням */
    private static final int DEFAULT_WIDTH = 45;
     /** Поточна ширина таблиці */
    private int width;

    /**Конструктор за замовчуванням.
     * Встановлює ширину таблиці {@link #DEFAULT_WIDTH}*/
    public ViewTable(){
        width = DEFAULT_WIDTH;
    }

    /**Конструктор з параметром ширини таблиці
     * @param width ширина таблиці*/
    public ViewTable(int width){
        this.width = width;
    }

    /**Конструктор з шириною таблиці та кількістю елементів
     * @param width ширина таблиці
     * @param n кількість елементів*/
    public ViewTable(int width, int n){
        super(n);
        this.width = width;
    }

    /**Встановлює ширину таблиці
     * @param width нова ширина
     * @return встановлене значення ширини*/
    public int setWidth(int width){
        return this.width = width;
    }

    /**Повертає поточну ширину таблиці
     * @return ширина таблиці*/
    public int getWidth(){
        return width;
    }

    /**Виводить горизонтальну лінію таблиці*/
    private void outLine(){
        for(int i = width; i> 0; i--){
            System.out.print('-');
        }
    }

    /**Виводить лінію таблиці та перенос рядка*/
    private void outLineLn(){
        outLine();
        System.out.println();
    }

    /**Виводить заголовок таблиці*/
    private void outHeader(){
        Formatter fmt = new Formatter();
        outLineLn();
        fmt.format("%s%d%s%2$d%s", "%-", (width-4)/2, "s | %-", "s\n");
        System.out.printf(fmt.toString(), "Ryadok", "Golosni");
    }
    /**Виводить тіло таблиці*/
    private void outBody(){
        Formatter fmt = new Formatter();
        fmt.format("%s%d%s%2$d%s", "%-", (width-4)/2, "s | %-", "d\n");
        for(Item2d item : getItems()){
            System.out.printf(fmt.toString(), item.getX(), item.getY());
        }
    }

    /**Перевантаження методу init()
     * @param width ширина таблиці*/
    public final void init(int width){
        this.width = width;
        viewInit("");
    }
    /** Перевантаження методу init()
     * @param width ширина таблиці
     * @param stepX параметр ініціалізації*/
    public final void init(int width, String rows){
        this.width = width;
        init(rows);
    }
     /**Перевизначення методу init()
     * @param stepX параметр ініціалізації*/
    @Override
    public void init(String rows){
        System.out.print("~Initialization: ");
        super.init(rows);
        System.out.println("done");
    }
    /**Виводить заголовок таблиці*/
    @Override
    public void viewHeader(){
        outHeader();
        outLineLn();
    }
    /**Виводить тіло таблиці*/
    @Override
    public void viewBody(){
        outBody();
    }
    /**Виводить нижню межу таблиці*/
    @Override
    public void viewFooter(){
        outLineLn();
    }
}

```

## MainTest.java

```java
package ex03;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import junit.framework.Assert;
import java.io.IOException;
import ex01.Item2d;
import ex02.ViewResult;
    /**
     * Виконує тестування розроблених класів.
     * @author Левковська Марія
     * @version 3.0
     */
public class MainTest{
    
    /** Перевірка основної функціональності класу {@linkplain ViewResult} */
    @Test
    public void testCalc(){
        ViewTable tbl = new ViewTable(50,3);
        assertEquals(50, tbl.getWidth());

        tbl.init(50, "Name");
        assertEquals(1, tbl.getItems().size());
        Item2d item = new Item2d();
        int ctr =0;
        item.setXY("Name", 2);
        assertTrue(tbl.getItems().get(ctr).equals(item));
        assertTrue(tbl.getItems().get(ctr).equals(item));
        
    }
    
    /** Перевірка серіалізації. Коректність відновлення даних */
    @Test
    public void testRestore(){
        ViewTable tbl1 = new ViewTable(50,100);
        ViewTable tbl2 = new ViewTable();

        tbl1.init(50, "Serialitatoin test");

        try{
            tbl1.viewSave();
        }
        catch(IOException e){
            Assert.fail(e.getMessage());
        }
        
        try{
            tbl2.viewRestore();
        }
        catch(Exception e){
            Assert.fail(e.getMessage());
        }
            assertEquals(tbl1.getItems().size(), tbl2.getItems().size());
            assertTrue("containsAll()", tbl1.getItems().containsAll((tbl2.getItems())));
        }
    }

```

## 📷Скріншот виконання
![Приклад1](https://github.com/kachka16/OOP/blob/task-4/PR4/img/priklad1.png?raw=true)

![Приклад2](https://github.com/kachka16/OOP/blob/task-4/PR4/img/priklad2.png?raw=true)

![Приклад3](https://github.com/kachka16/OOP/blob/task-4/PR4/img/priklad3.png?raw=true)
