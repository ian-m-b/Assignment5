package Problem4;

import java.util.Map;

class YourSystem {
    private PaymentProcessor paymentProcessor;

    public YourSystem(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public boolean makePayment(double amount, String currency, Map<String, String> cardDetails) {
        System.out.println("Initiating payment...");
        return paymentProcessor.processPayment(amount, currency, cardDetails);
    }
}

