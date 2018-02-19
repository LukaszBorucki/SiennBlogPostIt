package co.borucki.SiennBlogPostIt.usecase.manageblogpostcategory;

public class BlogPostCategoryDoesNotExist extends RuntimeException {
    public BlogPostCategoryDoesNotExist(long id) {
        super("Post category with id: " + id + " does not exist");
    }
}
