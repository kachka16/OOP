package ex03;
import ex02.ViewableResult;
import ex02.View;

    /**ConcreteCreator (шаблон проектування Factory Method)
     * Оголошує метод, який створює об'єкти відображення
     * @author Левковська Марія
     * @version 3.0
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
