package Problem5;

import java.time.LocalDateTime;
import java.time.LocalTime;

class PeakHourPricingStrategy implements PricingStrategy {
    private static final double BASE_FARE = 2.00;
    private static final double PER_MILE_RATE = 1.50;
    private static final double PEAK_MULTIPLIER = 1.3;

    @Override
    public double calculatePrice(RideDetails ride) {
        double basePrice = BASE_FARE + (ride.getDistance() * PER_MILE_RATE);
        return isPeakHour(ride.getStartTime()) ?
                basePrice * PEAK_MULTIPLIER : basePrice;
    }

    public boolean isPeakHour(LocalDateTime time) {
        LocalTime t = time.toLocalTime();
        return (t.isAfter(LocalTime.of(7, 0)) && t.isBefore(LocalTime.of(10, 0)) ||
                (t.isAfter(LocalTime.of(17, 0)) && t.isBefore(LocalTime.of(20, 0))));
    }
}

