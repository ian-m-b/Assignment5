package Problem4;

import java.util.Map;

class PayPalAdapter implements PaymentProcessor {
    private PayPalSDK payPalService;

    public PayPalAdapter(PayPalSDK payPalService) {
        this.payPalService = payPalService;
    }

    @Override
    public boolean processPayment(double amount, String currency, Map<String, String> cardDetails) {
        // Convert to PayPal's expected format
        PayPalPayment payment = new PayPalPayment(amount, currency);
        PayPalCard card = convertToPayPalFormat(cardDetails);

        // Call PayPal service
        PayPalResponse response = payPalService.makeCreditCardPayment(payment, card);

        // Convert response to our system's format
        return convertResponse(response);
    }

    private PayPalCard convertToPayPalFormat(Map<String, String> cardDetails) {
        return new PayPalCard(
                cardDetails.get("cardNumber"),
                cardDetails.get("expiry"),
                cardDetails.get("cvv")
        );
    }

    private boolean convertResponse(PayPalResponse response) {
        if (!response.success) {
            System.err.println("Payment failed: " + response.message);
        }
        return response.success;
    }
}

