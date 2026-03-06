package ex03;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import junit.framework.Assert;
import java.io.IOException;
import ex01.Item2d;
import ex02.ViewResult;
    /**
     * Виконує тестування розроблених класів.
     * @author Левковська Марія
     * @version 3.0
     */
public class MainTest{
    
    /** Перевірка основної функціональності класу {@linkplain ViewResult} */
    @Test
    public void testCalc(){
        ViewTable tbl = new ViewTable(50,3);
        assertEquals(50, tbl.getWidth());
        assertEquals(3, tbl.getItems().size());

        tbl.init(50, "Name");
        Item2d item = new Item2d();
        int ctr =0;
        item.setXY("Name", 2);
        assertTrue("expected:<"+ item + ">but was:<"+ tbl.getItems().get(ctr)+ ">", tbl.getItems().get(ctr).equals(item));
        ctr++;
        item.setXY("Eye", 3);
        assertTrue("expected:<"+ item + ">but was:<"+ tbl.getItems().get(ctr)+ ">", tbl.getItems().get(ctr).equals(item));
        item.setXY("Key", 2);
        assertTrue("expected:<"+ item + ">but was:<"+ tbl.getItems().get(ctr)+ ">", tbl.getItems().get(ctr).equals(item));
        
    }
    
    /** Перевірка серіалізації. Коректність відновлення даних */
    @Test
    public void testRestore(){
        ViewTable tbl1 = new ViewTable(50,100);
        ViewTable tbl2 = new ViewTable();

        tbl1.init(50, "Serialitatoin test");

        try{
            tbl1.viewSave();
        }
        catch(IOException e){
            Assert.fail(e.getMessage());
        }
        
        try{
            tbl2.viewRestore();
        }
        catch(Exception e){
            Assert.fail(e.getMessage());
        }
            assertEquals(tbl1.getItems().size(), tbl2.getItems().size());
            assertTrue("containsAll()", tbl1.getItems().containsAll((tbl2.getItems())));
        }
    }
