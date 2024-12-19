package entities;

public class Category {
    private static int autoId;

    private String id;
    private String name;

    public Category( String name) {
        this.id = "CATE" + ++autoId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
