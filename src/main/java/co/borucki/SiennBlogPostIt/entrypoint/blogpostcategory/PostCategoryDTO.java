package co.borucki.SiennBlogPostIt.entrypoint.blogpostcategory;


public class PostCategoryDTO {
    private int id;
    private String name;

    public PostCategoryDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
