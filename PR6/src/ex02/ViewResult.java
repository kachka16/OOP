package ex02;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ex01.Item2d;
import ex01.Calc;

    /** ConcreteProduct (шаблон проектування Factory Method)
     * Виконує обчислення, збереження та відображення результатів
     * @author Левковська Марія
     * @version 1.0
     * @see View*/

public class ViewResult implements View {
    /** Ім'я файлу для серіалізації */
    private static final String FNAME = "items.bin";
    /** Колекція результатів обчислень */
    private ArrayList<Item2d> items = new ArrayList<Item2d>();
    /** Кількість елементів за замовчуванням */
     /** Конструктор за замовчуванням */
    public ViewResult(){
        items.clear();
    }

    /** Ініціалізує колекцію @param n кількість елементів*/
    public ViewResult(int n){
        for( int i = 0; i<n; i++){
            items.add(new Item2d());
        }
    }

    /** Отримати колекцію результатів
     * @return список {@link Item2d}*/
    public ArrayList<Item2d> getItems(){
         return items;
    }

    /** Ініціалізує результати обчислень
     * Рядок розбивається на слова, для кожного слова обчислюється кількість голосних за допомогою методу {@linkplain Calc#calc(String)} - item.setY(Calc.calc(w));
     * @param rows рядок слів для обробки
     */
    public void init(String rows){
        String[] word = rows.split(" ");
        for(String w : word){
            Item2d item = new Item2d();
            item.setX(w);
            item.setY(Calc.calc(w));
            items.add(item);
            
        }
    }

    /** Реалізація {@link View#viewInit(String)}*/
    @Override
    public void viewInit(String rows){
        init(rows);
    }
    /** Реалізація {@link View#viewSave()} */
    @Override
    public void viewSave() throws IOException{
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(items);
        os.flush();
        os.close();
    }

    /** Реалізація {@link View#viewRestore()}*/
    @SuppressWarnings("unchecked")
    @Override
    public void viewRestore() throws Exception{
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        items = (ArrayList<Item2d>) is.readObject();
        is.close();
    }

    /** Реалізація {@link View#viewHeader()}*/
    @Override
    public void viewHeader(){
        System.out.println("Results: ");
    }

    /** Реалізація {@link View#viewBody()} */
    @Override
    public void viewBody(){

        for(Item2d item : items){
            System.out.println(item);
        }
    }
    /** Реалізація {@link View#viewFooter()} */
    @Override
    public void viewFooter(){
        System.out.println("End.");
    }


     /** Реалізація {@link View#viewShow()}*/
    @Override
    public void viewShow(){
        viewHeader();
        viewBody();
        viewFooter();
    }
}
