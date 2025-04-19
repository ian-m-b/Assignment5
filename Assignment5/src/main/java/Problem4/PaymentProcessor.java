package Problem4;
import java.util.Map;

interface PaymentProcessor {
    boolean processPayment(double amount, String currency, Map<String, String> cardDetails);
}

