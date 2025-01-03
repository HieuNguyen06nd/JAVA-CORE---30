package data;
import data.JsonHandler;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    /**
     * Tải dữ liệu từ file JSON vào danh sách.
     *
     * @param filePath Đường dẫn đến file JSON.
     * @param typeReference    Kiểu dữ liệu cần tải.
     * @return Danh sách dữ liệu đã tải.
     * @throws IOException Nếu có lỗi khi đọc tệp.
     */
    public <T> List<T> loadDataFromFile(String filePath, TypeReference<List<T>> typeReference) throws IOException {
        if (JsonHandler.isFileReadable(filePath)) {
            return JsonHandler.readFromFile(filePath, typeReference);
        } else {
            System.out.println("File " + filePath + " không tồn tại hoặc không thể đọc. Tạo tệp mới.");
            JsonHandler.writeToFile(filePath, new ArrayList<>(), true);
            return new ArrayList<>();
        }
    }

    /**
     * Lưu dữ liệu vào file JSON.
     *
     * @param filePath Đường dẫn đến file JSON.
     * @param data     Danh sách dữ liệu cần ghi.
     * @throws IOException Nếu có lỗi khi ghi tệp.
     */
    public <T> void saveDataToFile(String filePath, List<T> data) throws IOException {
        if (!data.isEmpty()) {
            JsonHandler.writeToFile(filePath, data, true);
        } else {
            System.out.println("Danh sách " + data.getClass().getSimpleName() + " trống, không ghi vào tệp.");
        }
    }
}