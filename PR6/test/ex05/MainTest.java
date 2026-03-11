package ex05;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ex02.ViewResult;

public class MainTest {
    private final static int N = 1000;
    private static ViewResult view = new ViewResult(N);
    private static MaxCommand max1 = new MaxCommand(view);
    private static MaxCommand max2 = new MaxCommand(view);
    private static AvgCommand avg1 = new AvgCommand(view);
    private static AvgCommand avg2 = new AvgCommand(view);
    private static MinMaxCommand min1 = new MinMaxCommand(view);
    private static MinMaxCommand min2 = new MinMaxCommand(view);
    private CommandQueue queue = new CommandQueue();

    private static void setUpBeforeClass(){
        view.viewInit("text for testing my MainTest");
        assertEquals(N, view.getItems().size());
        max1 = new MaxCommand(view);
        max2 = new MaxCommand(view);
        avg1 = new AvgCommand(view);
        avg2 = new AvgCommand(view);
        min1 = new MinMaxCommand(view);
        min2 = new MinMaxCommand(view);
    }
}
