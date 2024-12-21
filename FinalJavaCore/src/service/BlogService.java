package service;

import entities.Blog;
import entities.User;
import validate.ExistsCheck;

import java.util.ArrayList;
import java.util.Scanner;

public class BlogService {
    ExistsCheck existsCheck =new ExistsCheck();
    public Blog inpuBlog(Scanner scanner, ArrayList<User> users) {
        System.out.print("Nhập title: ");
        String title = scanner.nextLine();

        System.out.print("Nhập content: ");
         String content = scanner.nextLine();

        String user_Id;
        do {
            System.out.print("Nhập user ID: ");
            user_Id = scanner.nextLine();
            if (!existsCheck.isValidUser(user_Id, users)) {
                System.out.println("User ID không tồn tại. Vui lòng nhập lại.");
            }
        } while (!existsCheck.isValidUser(user_Id, users));

        return new Blog(title, content, user_Id);
    }
    public void changeBlogTitle(Scanner scanner, Blog blog) {
        System.out.print("Nhập title mới: ");
        String newTitle = scanner.nextLine();
        blog.setTitle(newTitle);
        System.out.println("Thay đổi title blog thành công.");
    }
    public void changeBlogContent(Scanner scanner, Blog blog) {
        System.out.print("Nhập content mới: ");
        String newContent = scanner.nextLine();
        blog.setTitle(newContent);
        System.out.println("Thay đổi content blog thành công.");
    }

    public void changeBlogStatus(Scanner scanner, ArrayList<Blog> blogs) {
        System.out.print("Nhập ID blog để thay đổi trạng thái: ");
        String blogId = scanner.nextLine();

        Blog blogToUpdate = findById(blogId, blogs);

        if (blogToUpdate != null) {
            boolean currentStatus = blogToUpdate.isStatus();
            blogToUpdate.setStatus(!currentStatus);

            String newStatus = blogToUpdate.isStatus() ? "Đăng" : "Hủy Đăng";
            System.out.println("Trạng thái blog đã thay đổi thành: " + newStatus);
        } else {
            System.out.println("Không tìm thấy blog với ID đã nhập.");
        }
    }
    public void deleteBlog(Scanner scanner, ArrayList<Blog> blogs) {
        System.out.print("Nhập ID blog để xóa: ");
        String blogId = scanner.nextLine();

        Blog blogToDelete = findById(blogId, blogs);
        if (blogToDelete != null) {
            blogs.remove(blogToDelete);
            System.out.println("Blog với ID " + blogId + " đã bị xóa.");
        } else {
            System.out.println("Không tìm thấy blog với ID đã nhập.");
        }
    }

    public Blog findById(String id, ArrayList<Blog>blogs){
        for (Blog blog: blogs){
            if (blog.getId().equalsIgnoreCase(id)){
                return blog;
            }
        }
        return null;
    }

}
