package service;

import entities.Blog;
import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlogService {

    public Blog inpuBlog(AppContext context, User user) {
        Scanner scanner = context.getScanner();
        System.out.print("Nhập title: ");
        String title = scanner.nextLine();

        System.out.print("Nhập content: ");
        String content = scanner.nextLine();

        String user_Id = user.getId();

        return new Blog(title, content, user_Id);
    }

    public void changeBlogStatus(AppContext context) {
        Scanner scanner = context.getScanner();
        List<Blog>blogs = context.getList(Blog.class);
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


    public void deleteBlog(AppContext context) {
        Scanner scanner = context.getScanner();
        List<Blog> blogs = context.getList(Blog.class);

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
    public Blog findById(String id, List<Blog>blogs){
        for (Blog blog: blogs){
            if (blog.getId().equalsIgnoreCase(id)){
                return blog;
            }
        }
        return null;
    }
}
