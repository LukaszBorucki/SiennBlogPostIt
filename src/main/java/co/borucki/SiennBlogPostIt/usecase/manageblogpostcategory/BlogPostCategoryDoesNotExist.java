package co.borucki.SiennBlogPostIt.usecase.manageblogpostcategory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlogPostCategoryDoesNotExist extends RuntimeException {
    public BlogPostCategoryDoesNotExist(long id) {
        super("Post category with id: " + id + " does not exist");
    }
}
