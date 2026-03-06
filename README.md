# Застосування шаблону проєктування Factory Method в ООП

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

```

## MainTest.java

```java

```
