package ex02;

    /**Creator(шаблон проектування Factory Method)
     * Оголошує метод, що "фабрикує" об'єкти
     * @author Левковська Марія
     * @version 1.0
     * @see Viewable#getView()
     */

public interface Viewable {
    /** Створює об'єкт, що реалізує {@linkplain View} */
    public View getView();
    
}
