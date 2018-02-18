package co.borucki.SiennBlogPostIt.domain;

public class BlogBestAuthorByCategory {
    private BlogAuthor author;
    private BlogPostCategory category;

    public BlogAuthor getAuthor() {
        return author;
    }

    public void setAuthor(BlogAuthor author) {
        this.author = author;
    }

    public BlogPostCategory getCategory() {
        return category;
    }

    public void setCategory(BlogPostCategory category) {
        this.category = category;
    }
}
