package service;

import entities.User;

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
        for (Object service : services) {
            if (serviceClass.isInstance(service)) {
                return serviceClass.cast(service);
            }
        }
        throw new RuntimeException("Dịch vụ không tồn tại: " + serviceClass.getSimpleName());
    }

    public void setUsers(List<User> users) {
        if (users == null) {
            throw new IllegalArgumentException("Danh sách users không được null.");
        }
        // Xóa danh sách users hiện tại và thêm danh sách mới
        clearList(User.class);
        getList(User.class).addAll(users);
    }

    public List<User> getUsers() {
        return getList(User.class);
    }
}