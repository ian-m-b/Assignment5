package Problem3;


public class UberSystem {
    public static void main(String[] args) {
        RideContext context = new RideContext();


        context.setStrategy(new BasicRide());
        context.executeService();

        context.setStrategy(new CarGoService());
        context.executeService();


        context.setStrategy(new CarEatsService());
        context.executeService();


        String currentCity = "Mumbai";
        configureServiceForCity(context, currentCity);
    }

    private static void configureServiceForCity(RideContext context, String city) {
        System.out.println("\nConfiguring services for " + city + ":");

        switch(city) {
            case "Mumbai":
                context.setStrategy(new CarGoService());
                break;
            case "New York":
                context.setStrategy(new CarEatsService());
                break;
            default:
                context.setStrategy(new BasicRide());
        }

        context.executeService();
    }
}