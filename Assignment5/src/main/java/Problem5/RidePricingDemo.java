package Problem5;

import java.time.LocalDateTime;

public class RidePricingDemo {
    public static void main(String[] args) {
        // Create sample ride
        RideDetails ride = new RideDetails(
                5.0, // distance in miles
                LocalDateTime.now(), // current time
                0.7 // demand factor (0.0 to 1.0)
        );

        // Create strategies
        PricingStrategy baseStrategy = new BasePricingStrategy();
        PricingStrategy surgeStrategy = new SurgePricingStrategy();
        PricingStrategy peakStrategy = new PeakHourPricingStrategy();

        // Create context with base strategy
        PricingContext context = new PricingContext(baseStrategy);

        // Test different strategies
        System.out.println("Base price: $" + context.calculatePrice(ride));

        context.setStrategy(surgeStrategy);
        System.out.println("Surge price: $" + context.calculatePrice(ride));

        context.setStrategy(peakStrategy);
        System.out.println("Peak hour price: $" + context.calculatePrice(ride));

        // Test with different time
        RideDetails peakRide = new RideDetails(
                5.0,
                LocalDateTime.of(2023, 6, 15, 8, 30), // 8:30 AM (peak hour)
                0.7
        );

        context.setStrategy(peakStrategy);
        System.out.println("\nPeak hour test (8:30 AM): $" + context.calculatePrice(peakRide));
    }
}
