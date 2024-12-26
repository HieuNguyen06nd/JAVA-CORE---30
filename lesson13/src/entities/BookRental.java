package entities;

import java.time.LocalDate;
import java.util.List;

public class BookRental {
    private String user_id;
    private List<String> book_id;
    private LocalDate created_at;
    private LocalDate paymentDate;

    public BookRental(String user_id, List<String> book_id, LocalDate paymentDate) {
        this.user_id = user_id;
        this.book_id = book_id;
        this.created_at = LocalDate.now();
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "BookRental{" +
                "user_id='" + user_id + '\'' +
                ", book_id=" + book_id +
                ", created_at=" + created_at +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
