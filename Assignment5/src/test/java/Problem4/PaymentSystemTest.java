package Problem4;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Map;

public class PaymentSystemTest {

    @Test
    public void testSuccessfulPayment() {
        // Mock PayPalSDK
        PayPalSDK mockPayPal = mock(PayPalSDK.class);
        PayPalAdapter adapter = new PayPalAdapter(mockPayPal);
        YourSystem system = new YourSystem(adapter);

        // Mock response
        when(mockPayPal.makeCreditCardPayment(any(), any()))
                .thenReturn(new PayPalResponse(true, "Success"));

        // Test data
        Map<String, String> cardDetails = Map.of(
                "cardNumber", "4111111111111111",
                "expiry", "12/25",
                "cvv", "123"
        );

        // Execute
        boolean result = system.makePayment(100.0, "USD", cardDetails);

        // Verify
        assertTrue(result);
        verify(mockPayPal).makeCreditCardPayment(any(), any());
    }

    @Test
    public void testFailedPayment() {
        PayPalSDK mockPayPal = mock(PayPalSDK.class);
        PayPalAdapter adapter = new PayPalAdapter(mockPayPal);
        YourSystem system = new YourSystem(adapter);

        // Mock failure response
        when(mockPayPal.makeCreditCardPayment(any(), any()))
                .thenReturn(new PayPalResponse(false, "Insufficient funds"));

        Map<String, String> cardDetails = Map.of(
                "cardNumber", "4111111111111111",
                "expiry", "12/25",
                "cvv", "123"
        );

        boolean result = system.makePayment(100.0, "USD", cardDetails);
        assertFalse(result);  // Payment should fail
    }

    @Test
    public void testInvalidCardDetails() {
        PayPalSDK mockPayPal = mock(PayPalSDK.class);
        PayPalAdapter adapter = new PayPalAdapter(mockPayPal);
        YourSystem system = new YourSystem(adapter);

        // Missing CVV
        Map<String, String> invalidCard = Map.of(
                "cardNumber", "4111111111111111",
                "expiry", "12/25"
        );

        assertThrows(NullPointerException.class, () -> {
            system.makePayment(100.0, "USD", invalidCard);
        });
    }

    @Test
    public void testUnsupportedCurrency() {
        PayPalSDK mockPayPal = mock(PayPalSDK.class);
        PayPalAdapter adapter = new PayPalAdapter(mockPayPal);
        YourSystem system = new YourSystem(adapter);

        // Mock PayPal rejecting non-USD currencies
        when(mockPayPal.makeCreditCardPayment(
                argThat(payment -> !payment.currency.equals("USD")), any())
        ).thenReturn(new PayPalResponse(false, "Unsupported currency"));

        Map<String, String> cardDetails = Map.of(
                "cardNumber", "4111111111111111",
                "expiry", "12/25",
                "cvv", "123"
        );

        boolean result = system.makePayment(100.0, "EUR", cardDetails);
        assertFalse(result);  // Should fail for EUR
    }

    @Test
    public void testAdapterConvertsCardCorrectly() {
        PayPalSDK mockPayPal = mock(PayPalSDK.class);
        PayPalAdapter adapter = new PayPalAdapter(mockPayPal);

        // Capture the converted PayPalCard object
        ArgumentCaptor<PayPalCard> cardCaptor = ArgumentCaptor.forClass(PayPalCard.class);
        when(mockPayPal.makeCreditCardPayment(any(), cardCaptor.capture()))
                .thenReturn(new PayPalResponse(true, "Success"));

        Map<String, String> cardDetails = Map.of(
                "cardNumber", "4111111111111111",
                "expiry", "12/25",
                "cvv", "123"
        );

        adapter.processPayment(100.0, "USD", cardDetails);

        // Verify the adapter converted the card correctly
        PayPalCard convertedCard = cardCaptor.getValue();
        assertEquals("****-****-****-1111", convertedCard.maskedNumber);
        assertEquals("12/25", convertedCard.expiry);
    }
}
