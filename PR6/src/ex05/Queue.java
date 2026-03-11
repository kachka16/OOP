package ex05;
import ex04.Command;
 /**Представляє методи для розміщення та вилучення завдань обробником потоку
  * шаблон Worker Thread
  * @author Левковська Марія
  * @version 1.0
  * @see Command
  */
public interface Queue {
    /**Додає нове завдання в чергу; 
     * шаблон Worker Thread
     * @param cmd завдання*/
    void put(Command cmd);
    /**Видаляє завдання з черги; 
     * шаблон Worker Thread
     * @return завдання, що видаляється*/
    Command take();
    
}
