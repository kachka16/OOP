package ex02;

import java.io.IOException;

    /**Product (шаблон проектування Factory Method)
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
