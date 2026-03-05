package ex02;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import junit.framework.Assert;
import java.io.IOException;

    /**
     * Виконує тестування розроблених класів.
     * @author Левковська Марія
     * @version 1.0
     */
public class MainTest{
    
    /** Перевірка основної функціональності класу {@linkplain ViewResult} */
    @Test
    public void testCalc(){
        ViewResult view = new ViewResult(3);
        view.init("Name");
        assertEquals(2, view.getItems().get(0).getY());

        view.init("Eye");
        assertEquals(3, view.getItems().get(0).getY());

        view.init("TV");
        assertEquals(0, view.getItems().get(0).getY());
        
    }
    
    /** Перевірка серіалізації. Коректність відновлення даних */
    @Test
    public void testRestore(){
        ViewResult view1 = new ViewResult();
        ViewResult view2 = new ViewResult();
        
        view1.init("Test");
        try{
            view1.viewSave();
        }
        catch(IOException e){
            Assert.fail(e.getMessage());
        }
        try{
            view2.viewRestore();
        }
        catch(Exception e){
            Assert.fail(e.getMessage());
        }
            assertEquals(view1.getItems().size(), view2.getItems().size());
            assertTrue(view1.getItems().containsAll(view2.getItems()));
        }
    }
