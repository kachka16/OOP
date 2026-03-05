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
        return "----------------------------------------------------\nRows: " + x + "\nk: " + y + "\nLenght: " + length_rows ;
        
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