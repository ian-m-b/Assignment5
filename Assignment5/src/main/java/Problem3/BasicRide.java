package Problem3;

class BasicRide implements ServiceStrategy {
    @Override
    public void provideService() {
        System.out.println("Providing basic ride service - Sedan/Taxi");
    }
}