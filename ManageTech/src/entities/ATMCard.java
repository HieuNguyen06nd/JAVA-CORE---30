package entities;

public class ATMCard {
    private String cardNumber; // Số thẻ ATM
    private String expirationDate; // Ngày hết hạn (MM/YY)
    private String pin; // Mã PIN
    private String cardHolderName; // Tên chủ thẻ
    private String userId;
    private double balance;  // Số dư trong thẻ

    public ATMCard(String cardNumber, String expirationDate, String pin, String cardHolderName, String userId) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.pin = pin;
        this.cardHolderName = cardHolderName;
        this.userId = userId;
        this.balance =11000000;
    }
}
