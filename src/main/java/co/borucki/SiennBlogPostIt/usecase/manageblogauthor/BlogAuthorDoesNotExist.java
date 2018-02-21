package co.borucki.SiennBlogPostIt.usecase.manageblogauthor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlogAuthorDoesNotExist extends RuntimeException {
    public BlogAuthorDoesNotExist(long blogAuthorId) {
        super("Author with id: " + blogAuthorId + " does not exist");
    }
}
