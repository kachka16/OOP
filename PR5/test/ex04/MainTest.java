package ex04;
import org.junit.Test;
import static org.junit.Assert.*;
import ex01.Item2d;
import ex02.ViewResult;
import ex01.Calc;
public class MainTest {
    @Test
    public void testExecute(){
        ChangeItemCommand cmd = new ChangeItemCommand();
        cmd.setItem(new Item2d());
        String rows;
        for(int ctr = 0; ctr< 10; ctr++){
            rows="Hello" + ctr;
            cmd.getItem().setX(rows);
             cmd.execute();
          assertEquals(rows, cmd.getItem().getX());
          assertTrue(cmd.getItem().getY() > 0);
        }
    }
    @Test
    public void testCahngeConsoleCOmmand (){
        ChangeConsoleCommand cmd = new ChangeConsoleCommand(new ViewResult());
        cmd.getView().viewInit("");
        cmd.execute();
        assertEquals("'d'elete(last word)", cmd.toString());
        assertEquals('d', cmd.getKey());
    }
}
