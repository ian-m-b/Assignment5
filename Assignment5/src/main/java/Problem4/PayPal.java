package Problem4;

class PayPalSDK {
    public PayPalResponse makeCreditCardPayment(PayPalPayment payment, PayPalCard card) {
        System.out.printf("Processing PayPal payment: %.2f %s with card %s\n",
                payment.amount, payment.currency, card.maskedNumber);
        // Actual PayPal API call would happen here
        return new PayPalResponse(true, "Payment processed");
    }
}

class PayPalPayment {
    double amount;
    String currency;

    public PayPalPayment(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
}


class PayPalCard {
    String number;
    String expiry;
    String cvv;
    String maskedNumber;

    public PayPalCard(String number, String expiry, String cvv) {
        this.number = number;
        this.expiry = expiry;
        this.cvv = cvv;
        this.maskedNumber = "****-****-****-" + number.substring(number.length() - 4);
    }
}


class PayPalResponse {
    boolean success;
    String message;

    public PayPalResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
