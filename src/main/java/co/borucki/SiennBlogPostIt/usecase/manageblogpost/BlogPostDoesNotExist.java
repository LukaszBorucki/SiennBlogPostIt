package co.borucki.SiennBlogPostIt.usecase.manageblogpost;

public class BlogPostDoesNotExist extends RuntimeException {
    public BlogPostDoesNotExist(long blogPostId) {
        super("Post with id: " + blogPostId + " does not exist");
    }
}
