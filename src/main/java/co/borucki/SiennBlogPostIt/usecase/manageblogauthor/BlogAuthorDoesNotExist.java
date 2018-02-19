package co.borucki.SiennBlogPostIt.usecase.manageblogauthor;

public class BlogAuthorDoesNotExist extends RuntimeException {
    public BlogAuthorDoesNotExist(long blogAuthorId) {
        super("Author with id: " + blogAuthorId + " does not exist");
    }
}
