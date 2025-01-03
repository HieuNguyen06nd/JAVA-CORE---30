package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class JsonHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // Đăng ký module JavaTimeModule để hỗ trợ các kiểu dữ liệu thời gian Java 8
        objectMapper.registerModule(new JavaTimeModule());
        // Bật tính năng định dạng đẹp cho JSON (indent, xuống dòng)
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Đọc dữ liệu từ file JSON và chuyển thành đối tượng Java.
     *
     * @param filePath Đường dẫn đến file JSON.
     * @param clazz    Kiểu dữ liệu của đối tượng cần đọc.
     * @return Đối tượng Java được chuyển đổi từ JSON.
     * @throws IOException Nếu có lỗi khi đọc file.
     */
    public static <T> T readFromFile(String filePath, Class<T> clazz) throws IOException {
        return objectMapper.readValue(new File(filePath), clazz);
    }

    /**
     * Đọc dữ liệu từ file JSON và chuyển thành danh sách đối tượng Java.
     *
     * @param filePath Đường dẫn đến file JSON.
     * @param typeReference Kiểu dữ liệu của danh sách cần đọc.
     * @return Danh sách đối tượng Java được chuyển đổi từ JSON.
     * @throws IOException Nếu có lỗi khi đọc file.
     */
    public static <T> T readFromFile(String filePath, TypeReference<T> typeReference) throws IOException {
        return objectMapper.readValue(new File(filePath), typeReference);
    }

    /**
     * Ghi dữ liệu từ đối tượng Java vào file JSON.
     *
     * @param filePath Đường dẫn đến file JSON.
     * @param data     Đối tượng Java cần ghi.
     * @throws IOException Nếu có lỗi khi ghi file.
     */
    public static void writeToFile(String filePath, Object data) throws IOException {
        objectMapper.writeValue(new File(filePath), data);
    }
}