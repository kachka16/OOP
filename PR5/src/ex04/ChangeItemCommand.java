package ex04;
import ex01.Item2d;
import ex01.Calc;
    /** Команда обробки елемента;
     * шаблон Command
     * використовується для обчислення кількості голосних у рядку
     * @author Левковська Марія
     * @version 1.0
     */
public class ChangeItemCommand implements Command{
    /** Оброблюваний об'єкт;
     * шаблон Command*/
    private Item2d item;
    /** Параметр команди*/
    private double offset;
    /** Встановлює поле {@linkplain ChangeItemCommand#item}
     * @param item значення для {@linkplain ChangeItemCommand#item}
     * @return нове значення {@linkplain ChangeItemCommand#item}*/
    public Item2d setItem(Item2d item){
        return this.item = item;
    }
    /** Повертає поле {@linkplain ChangeItemCommand#item}
     * @return значення {@linkplain ChangeItemCommand#item}*/
    public Item2d getItem(){
        return item;
    }
    public double setOffset(double offset){
        return this.offset = offset;
    }
    public double getOffset(){
        return offset;
    }
    /** Виконує команду;
     * обчислює кількість голосних у рядку*/
    public void execute(){
        String rows = item.getX();
        item.setY(Calc.calc(rows));
    }
}
