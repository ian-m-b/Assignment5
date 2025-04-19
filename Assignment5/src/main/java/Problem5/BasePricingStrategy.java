package Problem5;

class BasePricingStrategy implements PricingStrategy {
    private static final double BASE_FARE = 2.00;
    private static final double PER_MILE_RATE = 1.50;

    @Override
    public double calculatePrice(RideDetails ride) {
        return BASE_FARE + (ride.getDistance() * PER_MILE_RATE);
    }
}

