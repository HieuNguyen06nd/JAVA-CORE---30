import data.DataManager;
import data.JsonHandler;
import entities.*;
import enums.Role;
import service.AppContext;
import view.Menu;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {


    private static final AppContext appContext = AppContext.getInstance();
    public static void main(String[] args) {
        // Đăng ký Shutdown Hook để lưu dữ liệu trước khi thoát
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                DataManager.saveDataBeforeExit();
            } catch (IOException e) {
                System.err.println("Lỗi khi lưu dữ liệu trước khi thoát: " + e.getMessage());
            }
        }));

        try {
            // Khởi tạo dữ liệu từ các tệp JSON
            DataManager.initializeData(appContext);
        } catch (IOException e) {
            System.out.println("Lỗi khi khởi tạo dữ liệu: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Hiển thị menu chính
        while (true){
            new Menu().displayMenu();
        }

    }

}