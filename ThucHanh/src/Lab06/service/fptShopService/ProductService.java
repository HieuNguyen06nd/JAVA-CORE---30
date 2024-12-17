package Lab06.service.fptShopService;

import Lab06.entities.fptShop.Product;

import java.util.Scanner;

public class ProductService {
    public Product inputProduct(Scanner scanner) {

        System.out.print("Tên sản phẩm: ");
        String name = scanner.nextLine();

        System.out.print("Trạng thái: ");
        String status = scanner.nextLine();

        System.out.print("Số lượng: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        System.out.print("Giá sản phẩm: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Mô tả sản phẩm: ");
        String description = scanner.nextLine();

        return new Product( name, status, quantity, price, description);
    }
}
