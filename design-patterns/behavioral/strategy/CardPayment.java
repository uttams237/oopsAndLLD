package behavioral.strategy;

/**
 * CONCRETE STRATEGY - Card Payment
 * 
 * This class implements the PaymentStrategy interface for Credit/Debit Card payments.
 * It encapsulates the card payment algorithm/behavior.
 */
public class CardPayment implements PaymentStrategy {
    
    private String cardNumber;      // Card number (masked for security)
    private String cardHolderName;  // Name on the card
    private String cvv;             // CVV code
    private String expiryDate;      // Expiry date (MM/YY)
    
    /**
     * Constructor to initialize card payment details
     * 
     * @param cardNumber Card number
     * @param cardHolderName Name on the card
     * @param cvv CVV code
     * @param expiryDate Expiry date (MM/YY)
     */
    public CardPayment(String cardNumber, String cardHolderName, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }
    
    /**
     * Process payment using Credit/Debit Card
     * 
     * @param amount The amount to be paid
     * @return true if payment is successful
     */
    @Override
    public boolean pay(double amount) {
        // In real-world, this would validate card and connect to payment gateway
        
        // Mask card number for security (show only last 4 digits)
        String maskedCard = "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
        
        // Validate CVV (basic check)
        boolean cvvValid = cvv != null && cvv.length() == 3;
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║       CARD PAYMENT PROCESSING          ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Card: " + maskedCard);
        System.out.println("║ Holder: " + cardHolderName);
        System.out.println("║ Expiry: " + expiryDate);
        System.out.println("║ CVV: " + (cvvValid ? "Verified ✓" : "Invalid ✗"));
        System.out.println("║ Amount: ₹" + amount);
        System.out.println("║ Status: Payment Successful ✓");
        System.out.println("╚════════════════════════════════════════╝");
        return true;
    }
    
    /**
     * Get the payment method name
     * 
     * @return "Credit/Debit Card"
     */
    @Override
    public String getPaymentMethodName() {
        return "Credit/Debit Card";
    }
}
