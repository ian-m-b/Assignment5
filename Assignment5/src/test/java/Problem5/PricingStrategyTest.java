package Problem5;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class PricingStrategyTest {

    @Test
    public void testBasePricingStrategy() {
        PricingStrategy strategy = new BasePricingStrategy();
        RideDetails ride = new RideDetails(5.0, LocalDateTime.now(), 0.0);

        double price = strategy.calculatePrice(ride);
        // Base fare: $2.00 + (5 miles * $1.50/mile) = $9.50
        assertEquals(9.50, price, 0.001);
    }

    @Test
    public void testPeakHourPricingStrategy_PeakTime() {
        PricingStrategy strategy = new PeakHourPricingStrategy();
        LocalDateTime peakTime = LocalDateTime.of(2023, 1, 1, 8, 0); // 8 AM (peak)
        RideDetails ride = new RideDetails(5.0, peakTime, 0.0);

        double price = strategy.calculatePrice(ride);
        // Base price: $9.50 * 1.3 (peak multiplier) = $12.35
        assertEquals(12.35, price, 0.001);
    }

    @Test
    public void testPeakHourPricingStrategy_OffPeakTime() {
        PricingStrategy strategy = new PeakHourPricingStrategy();
        LocalDateTime offPeakTime = LocalDateTime.of(2023, 1, 1, 12, 0); // 12 PM (off-peak)
        RideDetails ride = new RideDetails(5.0, offPeakTime, 0.0);

        double price = strategy.calculatePrice(ride);
        // Base price: $9.50 (no peak multiplier)
        assertEquals(9.50, price, 0.001);
    }

    @Test
    public void testSurgePricingStrategy() {
        PricingStrategy strategy = new SurgePricingStrategy();
        RideDetails ride = new RideDetails(5.0, LocalDateTime.now(), 0.5); // 50% surge

        double price = strategy.calculatePrice(ride);
        // Base price: $9.50 * 1.5 (50% surge) = $14.25
        assertEquals(14.25, price, 0.001);
    }

    @Test
    public void testPricingContext_StrategySwitching() {
        PricingContext context = new PricingContext(new BasePricingStrategy());
        RideDetails ride = new RideDetails(5.0, LocalDateTime.now(), 0.0);

        // Test base strategy
        double basePrice = context.calculatePrice(ride);
        assertEquals(9.50, basePrice, 0.001);

        // Switch to surge pricing
        context.setStrategy(new SurgePricingStrategy());
        double surgePrice = context.calculatePrice(ride);
        assertEquals(9.50 * 1.0, surgePrice, 0.001); // 0% surge (demandFactor = 0)
    }

    @Test
    public void testZeroDistance() {
        PricingStrategy strategy = new BasePricingStrategy();
        RideDetails ride = new RideDetails(0.0, LocalDateTime.now(), 0.0);

        double price = strategy.calculatePrice(ride);
        // Base fare: $2.00 + (0 miles * $1.50/mile) = $2.00
        assertEquals(2.00, price, 0.001);
    }

    @Test
    public void testPeakHourBoundaryConditions() {
        PeakHourPricingStrategy strategy = new PeakHourPricingStrategy();

        // Just before peak (6:59 AM)
        RideDetails beforePeak = new RideDetails(5.0, LocalDateTime.of(2023, 1, 1, 6, 59), 0.0);
        assertFalse(strategy.isPeakHour(beforePeak.getStartTime()));

        // Start of peak (7:00 AM)
        RideDetails startPeak = new RideDetails(5.0, LocalDateTime.of(2023, 1, 1, 7, 0), 0.0);
        assertTrue(strategy.isPeakHour(startPeak.getStartTime()));
    }
}
