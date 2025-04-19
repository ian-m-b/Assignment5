package Problem3;

class CarGoService implements ServiceStrategy {
    @Override
    public void provideService() {
        System.out.println("Providing CarGo service - Hatchback ride (India special)");
    }
}
