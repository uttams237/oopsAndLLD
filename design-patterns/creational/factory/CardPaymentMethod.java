package creational.factory;

/**
 * CONCRETE PRODUCT - Card Payment
 * 
 * This class implements the PaymentMethod interface for Credit/Debit Card payments.
 * The Factory will create instances of this class when Card payment is requested.
 */
public class CardPaymentMethod implements PaymentMethod {
    
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    
    /**
     * Constructor to initialize Card payment method
     * 
     * @param cardNumber Card number
     * @param cardHolderName Name on the card
     * @param expiryDate Expiry date (MM/YY)
     * @param cvv CVV code
     */
    public CardPaymentMethod(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
    
    /**
     * Validate card details
     * 
     * @return true if card details are valid
     */
    @Override
    public boolean validate() {
        // Basic validation
        if (cardNumber == null || cardNumber.length() != 16) {
            System.out.println("❌ Invalid card number");
            return false;
        }
        if (cvv == null || cvv.length() != 3) {
            System.out.println("❌ Invalid CVV");
            return false;
        }
        System.out.println("✓ Card details validated");
        return true;
    }
    
    /**
     * Process Card payment
     * 
     * @param amount Amount to be paid
     * @return true if payment successful
     */
    @Override
    public boolean processPayment(double amount) {
        if (!validate()) {
            return false;
        }
        
        // Mask card number for security
        String maskedCard = "**** **** **** " + cardNumber.substring(12);
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       CARD PAYMENT PROCESSING          ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Card: " + maskedCard);
        System.out.println("║ Holder: " + cardHolderName);
        System.out.println("║ Expiry: " + expiryDate);
        System.out.println("║ Amount: ₹" + amount);
        System.out.println("║ Fee: ₹" + getTransactionFee(amount));
        System.out.println("║ Total: ₹" + (amount + getTransactionFee(amount)));
        System.out.println("║ Status: Payment Successful ✓");
        System.out.println("╚════════════════════════════════════════╝");
        
        return true;
    }
    
    /**
     * Get payment type
     * 
     * @return "CARD"
     */
    @Override
    public String getPaymentType() {
        return "CARD";
    }
    
    /**
     * Card has 2% transaction fee
     * 
     * @param amount Transaction amount
     * @return 2% of amount
     */
    @Override
    public double getTransactionFee(double amount) {
        return amount * 0.02; // 2% fee for cards
    }
}
