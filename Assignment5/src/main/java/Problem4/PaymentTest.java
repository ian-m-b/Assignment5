package Problem4;

import java.util.Map;

public class PaymentTest {
    public static void main(String[] args) {
        // Create adapter
        PayPalSDK payPalSDK = new PayPalSDK();
        PaymentProcessor paymentProcessor = new PayPalAdapter(payPalSDK);


        YourSystem system = new YourSystem(paymentProcessor);

        // Prepare test payment
        Map<String, String> cardDetails = Map.of(
                "cardNumber", "4111111111111111",
                "expiry", "12/25",
                "cvv", "123"
        );

        // Make payment
        boolean success = system.makePayment(49.99, "USD", cardDetails);
        System.out.println("Payment status: " + (success ? "SUCCESS" : "FAILED"));
    }
}
