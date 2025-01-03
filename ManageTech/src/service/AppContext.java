package service;

import entities.Course;
import entities.User;
import data.JsonHandler;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.*;

public class AppContext {
    private static AppContext instance;
    private Scanner scanner;
    private Map<Class<?>, List<?>> dataStore; // Sử dụng Map để quản lý danh sách
    private final List<Object> services; // Danh sách các dịch vụ

    private AppContext() {
        this.scanner = new Scanner(System.in);
        this.dataStore = new HashMap<>();
        this.services = new ArrayList<>();
    }

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    public Scanner getScanner() {
        return scanner;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(Class<T> clazz) {
        return (List<T>) dataStore.computeIfAbsent(clazz, k -> new ArrayList<>());
    }

    public <T> void addToList(Class<T> clazz, T item) {
        if (item == null) {
            throw new IllegalArgumentException("Đối tượng không được null.");
        }
        getList(clazz).add(item);
    }

    public <T> void removeFromList(Class<T> clazz, T item) {
        if (item == null) {
            throw new IllegalArgumentException("Đối tượng không được null.");
        }
        getList(clazz).remove(item);
    }

    public <T> void clearList(Class<T> clazz) {
        dataStore.remove(clazz);
    }

    public void registerService(Object service) {
        if (service == null) {
            throw new IllegalArgumentException("Dịch vụ không được null.");
        }
        services.add(service);
    }

    public <T> T getService(Class<T> serviceClass) {
        return services.stream()
                .filter(serviceClass::isInstance)
                .map(serviceClass::cast)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Dịch vụ không tồn tại: " + serviceClass.getSimpleName()));
    }

    public void setUsers(List<User> users) {
        if (users == null) {
            throw new IllegalArgumentException("Danh sách users không được null.");
        }
        clearList(User.class);
        getList(User.class).addAll(users);
    }

    public List<User> getUsers() {
        return getList(User.class);
    }

    public void setCourses(List<Course> courses) {
        if (courses == null) {
            throw new IllegalArgumentException("Danh sách courses không được null.");
        }
        clearList(Course.class);
        getList(Course.class).addAll(courses);
    }

    public List<Course> getCourses() {
        return getList(Course.class);
    }

    public <T> T findById(Class<T> clazz, String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID không được null hoặc rỗng.");
        }
        return getList(clazz).stream()
                .filter(item -> {
                    if (item instanceof User) {
                        return ((User) item).getId().equals(id);
                    } else if (item instanceof Course) {
                        return ((Course) item).getId().equals(id);
                    }
                    return false;
                })
                .findFirst()
                .orElse(null);
    }

    public <T> void updateItem(Class<T> clazz, T item) {
        if (item == null) {
            throw new IllegalArgumentException("Đối tượng không được null.");
        }
        List<T> list = getList(clazz);
        int index = list.indexOf(item);
        if (index != -1) {
            list.set(index, item);
        } else {
            throw new RuntimeException("Đối tượng không tồn tại trong danh sách.");
        }
    }

    public <T> void removeById(Class<T> clazz, String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID không được null hoặc rỗng.");
        }
        T item = findById(clazz, id);
        if (item != null) {
            removeFromList(clazz, item);
        } else {
            throw new RuntimeException("Đối tượng không tồn tại trong danh sách.");
        }
    }

    public <T> void loadDataFromFile(String filePath, Class<T> clazz) throws IOException {
        if (JsonHandler.isFileReadable(filePath)) {
            // Đọc dữ liệu từ file JSON và ép kiểu thành List<T>
            List<T> data = JsonHandler.readFromFile(filePath, new TypeReference<List<T>>() {});
            if (data != null) {
                // Xóa danh sách hiện tại và thêm dữ liệu mới
                clearList(clazz);
                getList(clazz).addAll(data);
            }
        } else {
            System.out.println("File " + filePath + " không tồn tại hoặc không thể đọc. Tạo tệp mới.");
            JsonHandler.writeToFile(filePath, new ArrayList<>(), true);
        }
    }

    public <T> void saveDataToFile(String filePath, Class<T> clazz) throws IOException {
        // Lấy danh sách dữ liệu từ dataStore
        List<T> data = getList(clazz);
        if (!data.isEmpty()) {
            // Ghi dữ liệu vào file JSON
            JsonHandler.writeToFile(filePath, data, true);
        } else {
            System.out.println("Danh sách " + clazz.getSimpleName() + " trống, không ghi vào tệp.");
        }
    }
}