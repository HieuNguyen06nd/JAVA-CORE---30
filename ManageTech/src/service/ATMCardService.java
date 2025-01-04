package service;

import entities.ATMCard;
import entities.Course;
import exist.Exist;

import java.util.List;
import java.util.Scanner;

public class ATMCardService {
    private AppContext context;

    public ATMCardService() {
        this.context = AppContext.getInstance(); // Khởi tạo context
    }

    Exist exist = new Exist();

    public void inputATMCardDetails() {
        Scanner scanner = context.getScanner();
        List<ATMCard> atmCards = context.getList(ATMCard.class);

        System.out.println("Nhập thông tin thẻ ATM:");

        // Lấy user_id của người dùng hiện tại
        String user_id = context.getCurrentUserId();
        if (user_id == null) {
            System.out.println("Vui lòng đăng nhập để tạo thẻ ATM.");
            return;
        }

        // Kiểm tra xem người dùng đã có thẻ ATM chưa
        if (exist.isUserHasCard(user_id)) {
            System.out.println("Người dùng đã có thẻ ATM. Mỗi người dùng chỉ được tạo một thẻ duy nhất.");
            return;
        }

        String cardNumber;
        while (true) {
            System.out.print("Nhập số thẻ (chỉ chứa số, 12-16 ký tự): ");
            cardNumber = scanner.nextLine().trim();

            // Kiểm tra số thẻ chỉ chứa số và có độ dài hợp lệ
            if (exist.isValidCardNumber(cardNumber)) {
                // Kiểm tra số thẻ có duy nhất không
                if (exist.isCardNumberUnique(cardNumber)) {
                    break; // Số thẻ hợp lệ và duy nhất
                } else {
                    System.out.println("Số thẻ đã tồn tại. Vui lòng nhập số thẻ khác.");
                }
            } else {
                System.out.println("Số thẻ không hợp lệ. Vui lòng nhập lại.");
            }
        }

        // Nhập tên chủ thẻ
        System.out.print("Nhập tên chủ thẻ: ");
        String cardHolderName = scanner.nextLine().toUpperCase();

        // Nhập ngày hết hạn (định dạng MM/yy)
        String expiryDate;
        while (true) {
            System.out.print("Nhập ngày hết hạn (MM/yy): ");
            expiryDate = scanner.nextLine().trim();

            // Kiểm tra định dạng và tính hợp lệ của ngày hết hạn
            if (exist.isValidExpiryDate(expiryDate)) {
                break; // Ngày hết hạn hợp lệ
            } else {
                System.out.println("Ngày hết hạn không hợp lệ. Vui lòng nhập lại.");
            }
        }

        // Nhập mã PIN
        int pin;
        while (true) {
            System.out.print("Nhập mã PIN (4 chữ số): ");
            String pinInput = scanner.nextLine().trim();

            // Kiểm tra mã PIN chỉ chứa 4 chữ số
            if (exist.isValidPin(pinInput)) {
                pin = Integer.parseInt(pinInput); // Chuyển đổi sang số nguyên
                break; // Mã PIN hợp lệ
            } else {
                System.out.println("Mã PIN không hợp lệ. Vui lòng nhập lại.");
            }
        }

        // Tạo và trả về đối tượng ATMCard
        atmCards.add(new ATMCard(cardNumber, cardHolderName, expiryDate, pin, user_id));

        System.out.println("add Thẻ thành công");
    }
}
