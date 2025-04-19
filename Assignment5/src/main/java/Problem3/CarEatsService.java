package Problem3;

class CarEatsService implements ServiceStrategy {
    @Override
    public void provideService() {
        System.out.println("Providing CarEats service - Food delivery by drivers");
    }
}