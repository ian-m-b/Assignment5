package Problem3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RideServiceTest {

    @Test
    public void testBasicRideService() {
        RideContext context = new RideContext();
        context.setStrategy(new BasicRide());

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        context.executeService();

        String expectedOutput = "Providing basic ride service - Sedan/Taxi" + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testCarEatsService() {
        RideContext context = new RideContext();
        context.setStrategy(new CarEatsService());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        context.executeService();

        String expectedOutput = "Providing CarEats service - Food delivery by drivers" + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testCarGoService() {
        RideContext context = new RideContext();
        context.setStrategy(new CarGoService());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        context.executeService();

        String expectedOutput = "Providing CarGo service - Hatchback ride (India special)" + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testDynamicStrategyChange() {
        RideContext context = new RideContext();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // First set to BasicRide
        context.setStrategy(new BasicRide());
        context.executeService();
        String basicRideOutput = "Providing basic ride service - Sedan/Taxi" + System.lineSeparator();
        assertEquals(basicRideOutput, outputStream.toString());

        // Reset output
        outputStream.reset();

        // Switch to CarEatsService
        context.setStrategy(new CarEatsService());
        context.executeService();
        String carEatsOutput = "Providing CarEats service - Food delivery by drivers" + System.lineSeparator();
        assertEquals(carEatsOutput, outputStream.toString());
    }
}