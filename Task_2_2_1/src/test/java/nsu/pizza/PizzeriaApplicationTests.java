package nsu.pizza;

import nsu.pizza.additions.BoolCooker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PizzeriaApplicationTests {
    @Test
    public void endOfPizzaTest() {
        BoolCooker cookerEnd = new BoolCooker();
        PizzeriaApplication.endOfPizzeria(100L, (new ArrayList<Thread>()), (new ArrayList<Thread>()), cookerEnd);
        assertTrue(cookerEnd.value);
    }
}
