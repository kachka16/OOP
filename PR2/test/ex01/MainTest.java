package ex01;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import junit.framework.Assert;

/**
 * Виконує тестування розроблених класів.
 * @author Левковська Марія
 * @version 1.0
 */
public class MainTest{
    
    /** Перевірка основної функціональності класу {@linkplain Calc}*/
    @Test
    public void testCalc(){
        Calc calc = new Calc();
        calc.init("Name");
        assertEquals(2, calc.getResult().getY());

        calc.init("Eye");
        assertEquals(3, calc.getResult().getY());

        calc.init("TV");
        assertEquals(0, calc.getResult().getY());
        
    }
    
    /** Перевірка серіалізації. Коректність відновлення даних. */
    @Test
    public void testRestore(){
        Calc calc = new Calc();
        String x;
        int y;
        for(int ctr =0; ctr<1000; ctr++){
            x = "FirstRowTest";
            calc.init(x);
            y = calc.getResult().getY();
            try{
                calc.save();
            }
            catch (IOException e){
                Assert.fail(e.getMessage());
            }
            calc.init("SecondRowTest");
            try{
                calc.restore();
            }
            catch (Exception e){
                Assert.fail(e.getMessage());
            }
            assertEquals(y, calc.getResult().getY());
            assertEquals(x, calc.getResult().getX());
        }
    }
}