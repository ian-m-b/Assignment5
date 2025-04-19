package Problem5;

class PricingContext {
    private PricingStrategy strategy;

    public PricingContext(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculatePrice(RideDetails ride) {
        return strategy.calculatePrice(ride);
    }
}

