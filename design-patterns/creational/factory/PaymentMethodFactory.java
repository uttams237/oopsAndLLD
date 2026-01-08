package creational.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * FACTORY CLASS - PaymentMethodFactory
 * 
 * This is the CORE of the Factory Pattern!
 * It centralizes the creation logic for all payment methods.
 * 
 * KEY BENEFITS:
 * - Client code doesn't need to know about concrete classes
 * - Creation logic is centralized in one place
 * - Easy to add new payment methods without changing client code
 * - Reduces coupling and improves maintainability
 * 
 * REAL-WORLD ANALOGY:
 * This is like a restaurant kitchen - you (client) order "Pizza" from the menu,
 * and the kitchen (factory) prepares it. You don't need to know the recipe or
 * how to make it yourself!
 */
public class PaymentMethodFactory {
    
    /**
     * Create a payment method based on the type
     * This is the FACTORY METHOD!
     * 
     * @param type Payment type ("UPI", "CARD", "NET_BANKING", "WALLET")
     * @param details Map containing payment-specific details
     * @return PaymentMethod object, or null if type is invalid
     */
    public static PaymentMethod createPaymentMethod(String type, Map<String, String> details) {
        
        // Normalize type to uppercase for case-insensitive matching
        type = type.toUpperCase();
        
        System.out.println("\n🏭 Factory: Creating " + type + " payment method...");
        
        // Factory decides which concrete class to instantiate
        switch (type) {
            case "UPI":
                return createUpiPayment(details);
                
            case "CARD":
                return createCardPayment(details);
                
            case "NET_BANKING":
            case "NETBANKING":
                return createNetBankingPayment(details);
                
            case "WALLET":
                return createWalletPayment(details);
                
            default:
                System.out.println("❌ Factory: Unknown payment type: " + type);
                return null;
        }
    }
    
    /**
     * Helper method to create UPI payment
     * 
     * @param details Map with "upiId"
     * @return UpiPaymentMethod object
     */
    private static PaymentMethod createUpiPayment(Map<String, String> details) {
        String upiId = details.get("upiId");
        return new UpiPaymentMethod(upiId);
    }
    
    /**
     * Helper method to create Card payment
     * 
     * @param details Map with "cardNumber", "cardHolderName", "expiryDate", "cvv"
     * @return CardPaymentMethod object
     */
    private static PaymentMethod createCardPayment(Map<String, String> details) {
        String cardNumber = details.get("cardNumber");
        String cardHolderName = details.get("cardHolderName");
        String expiryDate = details.get("expiryDate");
        String cvv = details.get("cvv");
        
        return new CardPaymentMethod(cardNumber, cardHolderName, expiryDate, cvv);
    }
    
    /**
     * Helper method to create Net Banking payment
     * 
     * @param details Map with "bankName", "accountNumber", "ifscCode"
     * @return NetBankingPaymentMethod object
     */
    private static PaymentMethod createNetBankingPayment(Map<String, String> details) {
        String bankName = details.get("bankName");
        String accountNumber = details.get("accountNumber");
        String ifscCode = details.get("ifscCode");
        
        return new NetBankingPaymentMethod(bankName, accountNumber, ifscCode);
    }
    
    /**
     * Helper method to create Wallet payment
     * 
     * @param details Map with "walletProvider", "phoneNumber", "walletBalance"
     * @return WalletPaymentMethod object
     */
    private static PaymentMethod createWalletPayment(Map<String, String> details) {
        String walletProvider = details.get("walletProvider");
        String phoneNumber = details.get("phoneNumber");
        double walletBalance = Double.parseDouble(details.getOrDefault("walletBalance", "0"));
        
        return new WalletPaymentMethod(walletProvider, phoneNumber, walletBalance);
    }
    
    /**
     * Convenience method: Create UPI payment with just UPI ID
     * 
     * @param upiId UPI ID
     * @return UpiPaymentMethod object
     */
    public static PaymentMethod createUpiPayment(String upiId) {
        Map<String, String> details = new HashMap<>();
        details.put("upiId", upiId);
        return createPaymentMethod("UPI", details);
    }
    
    /**
     * Convenience method: Create Card payment
     * 
     * @param cardNumber Card number
     * @param cardHolderName Cardholder name
     * @param expiryDate Expiry date
     * @param cvv CVV
     * @return CardPaymentMethod object
     */
    public static PaymentMethod createCardPayment(String cardNumber, String cardHolderName, 
                                                   String expiryDate, String cvv) {
        Map<String, String> details = new HashMap<>();
        details.put("cardNumber", cardNumber);
        details.put("cardHolderName", cardHolderName);
        details.put("expiryDate", expiryDate);
        details.put("cvv", cvv);
        return createPaymentMethod("CARD", details);
    }
    
    /**
     * Convenience method: Create Net Banking payment
     * 
     * @param bankName Bank name
     * @param accountNumber Account number
     * @param ifscCode IFSC code
     * @return NetBankingPaymentMethod object
     */
    public static PaymentMethod createNetBankingPayment(String bankName, String accountNumber, 
                                                         String ifscCode) {
        Map<String, String> details = new HashMap<>();
        details.put("bankName", bankName);
        details.put("accountNumber", accountNumber);
        details.put("ifscCode", ifscCode);
        return createPaymentMethod("NET_BANKING", details);
    }
    
    /**
     * Convenience method: Create Wallet payment
     * 
     * @param walletProvider Wallet provider
     * @param phoneNumber Phone number
     * @param walletBalance Wallet balance
     * @return WalletPaymentMethod object
     */
    public static PaymentMethod createWalletPayment(String walletProvider, String phoneNumber, 
                                                     double walletBalance) {
        Map<String, String> details = new HashMap<>();
        details.put("walletProvider", walletProvider);
        details.put("phoneNumber", phoneNumber);
        details.put("walletBalance", String.valueOf(walletBalance));
        return createPaymentMethod("WALLET", details);
    }
}
