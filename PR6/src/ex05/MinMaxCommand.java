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
public class MinMaxCommand implements Command{
    /** Зберігає результат обробки колекції (мінімум) */
    private int resultMin = -1;
    /** Зберігає результат обробки колекції (максимум) */
    private int resultMax = -1;
    /** Прапорець готовності результату */
    private int progress = 0;
    /** Обслуговує колекцію об'єктів {@linkplain ex01.Item2d} */
    private ViewResult viewResult;
    /**Повертає поле {@linkplain MinMaxCommand#viewResult}
     * @return значення {@linkplain MinMaxCommand#viewResult}
     */
    public ViewResult getViewResult(){
        return viewResult;
    }
    /**Встановлює поле {@linkplain MinMaxCommand#viewResult}
     * @param viewResult значення для {@linkplain MinMaxCommand#viewResult}
     * @return нове значення {@linkplain MinMaxCommand#viewResult}
     */
    public ViewResult setViewResult(ViewResult viewResult){
        return this.viewResult = viewResult;
    }
    /**Ініціалізує поле {@linkplain MinMaxCommand#viewResult}
     * @param viewResult об'єкт класу {@linkplain ViewResult}
     */
    public MinMaxCommand(ViewResult viewResult){
        this.viewResult = viewResult;
    }

    public int getResult(){
        return resultMin;
    }
    /**Повертає результат мінімального значення
     * @return поле {@linkplain MinMaxCommand#resultMin}
     */
    public int getResultMin(){
        return resultMin;
    }
    /**Повертає результат максимального значення
     * @return поле {@linkplain MinMaxCommand#resultMax}
     */
    public int getResultMax(){
        return resultMax;
    }
     /**Перевіряє готовність результату
     * @return false – якщо результат отримано, інакше – true
     */
    public boolean running(){
        return progress < 100;
    }
    /**Використовується обробником потоку {@linkplain CommandQueue};
     * шаблон Worker Thread
     */
    public void execute(){
        progress = 0;
        System.out.println("MinMax executed...");
        int idx = 0 , size = viewResult.getItems().size();
        for(Item2d item : viewResult.getItems()){
                if((resultMax == -1) || (viewResult.getItems().get(resultMax).getY()< item.getY())){
                    resultMax = idx;
                }

                if((resultMin == -1) || (viewResult.getItems().get(resultMin).getY()> item.getY())){
                    resultMin = idx;
                }
            idx++;
            progress = idx * 100/size;
            if(size >=5 && idx %(size/5) == 0){
                System.out.println("MinMax "+ progress + "%");
            }
            try{
                TimeUnit.MILLISECONDS.sleep(5000/size);
            }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        System.out.print(" ~MinMax doxe. ");
        if(resultMin > -1){
            System.out.println("Min positive #" + resultMin + " found: " + "\"" + viewResult.getItems().get(resultMin).getX() + "\"" + " golosni = " + viewResult.getItems().get(resultMin).getY());
        }
        else{
            System.out.println(" ~Min positive not found");
        }
        if(resultMax > -1){
            System.out.println("Min positive #" + resultMax + " found: " + "\"" + viewResult.getItems().get(resultMax).getX() + "\"" + " golosni = " + viewResult.getItems().get(resultMax).getY());
        }
        else{
            System.out.println(" ~Max negative not found");
        }
        progress = 100;
    }
}
