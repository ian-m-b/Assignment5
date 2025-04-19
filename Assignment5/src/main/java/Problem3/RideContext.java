package Problem3;

class RideContext {
    private ServiceStrategy strategy;

    public void setStrategy(ServiceStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeService() {
        strategy.provideService();
    }
}

