package entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ATMCard {
    private String cardNumber; // Số thẻ
    private String cardHolderName; // Tên chủ thẻ
    private String expiryDate; // Ngày hết hạn
    private int pin;
    private BigDecimal balance; // Số dư trong tài khoản
    private String user_id;

    public ATMCard(String cardNumber, String cardHolderName, String expiryDate, int pin, String user_id) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.pin = pin;
        this.balance = new BigDecimal(50000);
        this.user_id = user_id;
    }

    public ATMCard() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin; // So sánh mã PIN nhập vào với mã PIN của thẻ
    }
}
