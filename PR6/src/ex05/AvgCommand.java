package ex05;
import java.util.concurrent.TimeUnit;
import ex01.Item2d;
import ex02.ViewResult;
import ex04.Command;
    /**Задача, що використовується обробником потоку;
     * шаблон Worker Thread
     * @author Левковська Марія 
     * @version 1.0
     * @see Command
     * @see CommandQueue
     */
public class AvgCommand implements Command{
    /** Зберігає результат обробки колекції */
    private double result = 0.0;
     /** Прапорець готовності результату */
    private int progress = 0;
    /** Обслуговує колекцію об'єктів {@linkplain ex01.Item2d} */
    private ViewResult viewResult;
     /**Повертає поле {@linkplain AvgCommand#viewResult}
     * @return значення {@linkplain AvgCommand#viewResult}*/
    public ViewResult getViewResult(){
        return viewResult;
    }
    /**Встановлює поле {@linkplain AvgCommand#viewResult}
     * @param viewResult значення для {@linkplain AvgCommand#viewResult}
     * @return нове значення {@linkplain AvgCommand#viewResult}
     * */
    public ViewResult setViewResult(ViewResult viewResult){
        return this.viewResult = viewResult;
    }

     /**Ініціалізує поле {@linkplain AvgCommand#viewResult}
     * @param viewResult об'єкт класу {@linkplain ViewResult}
     */
    public AvgCommand(ViewResult viewResult){
        this.viewResult = viewResult;
    }
    /**Повертає результат
     * @return поле {@linkplain AvgCommand#result}
     */
    public double getResult(){
        return result;
    }
    /**Перевіряє готовність результату
     * @return false – якщо результат отримано, інакше – true
     * @see AvgCommand#result
     */
    public boolean running(){
        return progress <100;
    }
    /** Використовується обробником потоку {@linkplain CommandQueue};
     * шаблон Worker Thread*/
    @Override
    public void execute(){
        progress = 0;
        System.out.println("Average execute...");
        result = 0.0;
        int idx = 1, size = viewResult.getItems().size();
        for(Item2d item : viewResult.getItems()){
            result += item.getY();
            progress = idx *100 / size;
            if(size >=2 && idx++ % (size / 2) == 0){
                System.out.println("Average " + progress + "%");
            }
            try{
                TimeUnit.MILLISECONDS.sleep(2000/size);
            }
            catch (InterruptedException e){
                System.err.println(e);
            }
        }
        result /= size;
        System.out.println(" ~Average done. Result = " + String.format("%.2f",result));
        progress = 100;
    }
}
