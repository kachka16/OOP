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
        cmd.getItem().setX("Hello");
        cmd.getItem().setY(2);
           cmd.setOffset(3);
            cmd.execute();
            assertEquals("Hello", cmd.getItem().getX());
          assertEquals(6, cmd.getItem().getY());
    }
    @Test
    public void testCahngeConsoleCOmmand (){
        ChangeConsoleCommand cmd = new ChangeConsoleCommand(new ViewResult());
        cmd.getView().viewInit("");
        cmd.execute();
        assertEquals("'c'hange", cmd.toString());
        assertEquals('c', cmd.getKey());
    }
}
