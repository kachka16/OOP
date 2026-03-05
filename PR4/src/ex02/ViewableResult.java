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
