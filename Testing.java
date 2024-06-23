import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class Testing {

    @Test
    @DisplayName("STUDENT TEST - Case #1")
    public void firstCaseTest() {
        double budget = 500;
        Allocation test1 = new Allocation();
        List<Region> testReg = new ArrayList<>();
        testReg.add(new Region("Region #1", 100, 400));
        testReg.add(new Region("Region #2", 150, 600));

        Allocation test2 = Client.allocateRelief(budget, testReg);
        test1 = test1.withRegion(new Region("Region #1", 100, 400)); 
        assertEquals(test1, test2);
    }

    @Test
    @DisplayName("STUDENT TEST - Case #2")
    public void secondCaseTest() {
        double budget = 500;
        Allocation test1 = new Allocation();
        List<Region> testReg = new ArrayList<>();
        testReg.add(new Region("Region #1", 150, 400));
        testReg.add(new Region("Region #2", 100, 450 ));

        Allocation test2 = Client.allocateRelief(budget, testReg);
        test1 = test1.withRegion(new Region("Region #1", 150, 400)); 
        assertEquals(test2, test1);
        
    }

    @Test
    @DisplayName("STUDENT TEST - Case #3")
    public void thirdCaseTest() {
        double budget = 500;
        Allocation test1 = new Allocation();
        List<Region> testReg = new ArrayList<>();
        testReg.add(new Region("Region #1", 150, 450));
        testReg.add(new Region("Region #2", 150, 400));

        Allocation test2 = Client.allocateRelief(budget, testReg);
        test1 = test1.withRegion(new Region("Region #2", 150, 400)); 
        assertEquals(test2, test1);
        
    }
}