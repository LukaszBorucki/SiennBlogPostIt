package co.borucki.SiennBlogPostIt.usecase.manageblogpost;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlogPostDoesNotExist extends RuntimeException {
    public BlogPostDoesNotExist(long blogPostId) {
        super("Post with id: " + blogPostId + " does not exist");
    }
}
