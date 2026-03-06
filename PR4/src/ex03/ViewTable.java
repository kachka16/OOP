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
