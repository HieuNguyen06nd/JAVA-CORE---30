package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            throw new IOException("File does not exist or is empty: " + filePath);
        }
        return objectMapper.readValue(file, clazz);
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
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            throw new IOException("File does not exist or is empty: " + filePath);
        }
        return objectMapper.readValue(file, typeReference);
    }

    /**
     * Ghi dữ liệu từ đối tượng Java vào file JSON.
     *
     * @param filePath Đường dẫn đến file JSON.
     * @param data     Đối tượng Java cần ghi.
     * @param prettyPrint Nếu true, định dạng JSON đẹp (indent, xuống dòng).
     * @throws IOException Nếu có lỗi khi ghi file.
     */
    public static void writeToFile(String filePath, Object data, boolean prettyPrint) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            // Kiểm tra và tạo thư mục cha nếu cần
            File parentDir = file.getParentFile();
            if (parentDir != null) {
                parentDir.mkdirs(); // Tạo thư mục cha nếu chưa tồn tại
            }
            file.createNewFile(); // Tạo tệp mới nếu chưa tồn tại
        }
        if (prettyPrint) {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        } else {
            objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
        }
        objectMapper.writeValue(file, data);
    }

    /**
     * Ghi dữ liệu từ đối tượng Java vào file JSON với định dạng đẹp (indent, xuống dòng).
     *
     * @param filePath Đường dẫn đến file JSON.
     * @param data     Đối tượng Java cần ghi.
     * @throws IOException Nếu có lỗi khi ghi file.
     */
    public static void writeToFile(String filePath, Object data) throws IOException {
        writeToFile(filePath, data, true);
    }

    /**
     * Kiểm tra xem tệp có tồn tại và có thể đọc được không.
     *
     * @param filePath Đường dẫn đến file JSON.
     * @return true nếu tệp tồn tại và có thể đọc được, ngược lại trả về false.
     */
    public static boolean isFileReadable(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.canRead();
    }

    /**
     * Kiểm tra xem tệp có tồn tại và có thể ghi được không.
     *
     * @param filePath Đường dẫn đến file JSON.
     * @return true nếu tệp tồn tại và có thể ghi được, ngược lại trả về false.
     */
    public static boolean isFileWritable(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.canWrite();
    }

    /**
     * Tạo file nếu nó không tồn tại.
     *
     * @param filePath Đường dẫn đến file JSON.
     * @throws IOException Nếu có lỗi khi tạo file.
     */
    public static void createFileIfNotExists(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Tạo thư mục cha nếu chưa tồn tại
            file.createNewFile(); // Tạo tệp mới nếu chưa tồn tại
        }
    }

    /**
     * Đọc danh sách đối tượng từ file JSON.
     *
     * @param filePath Đường dẫn đến file JSON.
     * @param clazz    Kiểu dữ liệu của đối tượng trong danh sách.
     * @return Danh sách đối tượng Java được chuyển đổi từ JSON.
     * @throws IOException Nếu có lỗi khi đọc file.
     */
    public static <T> List<T> readListFromFile(String filePath, Class<T> clazz) throws IOException {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            throw new IOException("File does not exist or is empty: " + filePath);
        }
        return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    /**
     * Ghi danh sách đối tượng vào file JSON.
     *
     * @param filePath Đường dẫn đến file JSON.
     * @param data     Danh sách đối tượng cần ghi.
     * @param prettyPrint Nếu true, định dạng JSON đẹp (indent, xuống dòng).
     * @throws IOException Nếu có lỗi khi ghi file.
     */
    public static <T> void writeListToFile(String filePath, List<T> data, boolean prettyPrint) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Tạo thư mục cha nếu chưa tồn tại
            file.createNewFile(); // Tạo tệp mới nếu chưa tồn tại
        }
        if (prettyPrint) {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        } else {
            objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
        }
        objectMapper.writeValue(file, data);
    }

    /**
     * Ghi danh sách đối tượng vào file JSON với định dạng đẹp (indent, xuống dòng).
     *
     * @param filePath Đường dẫn đến file JSON.
     * @param data     Danh sách đối tượng cần ghi.
     * @throws IOException Nếu có lỗi khi ghi file.
     */
    public static <T> void writeListToFile(String filePath, List<T> data) throws IOException {
        writeListToFile(filePath, data, true);
    }
}