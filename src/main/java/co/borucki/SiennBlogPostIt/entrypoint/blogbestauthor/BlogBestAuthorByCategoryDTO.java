package co.borucki.SiennBlogPostIt.entrypoint.blogbestauthor;

import co.borucki.SiennBlogPostIt.entrypoint.blogauthor.AuthorDTO;
import co.borucki.SiennBlogPostIt.entrypoint.blogpostcategory.PostCategoryDTO;

public class BlogBestAuthorByCategoryDTO {
    private AuthorDTO author;
    private PostCategoryDTO postCategory;

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public PostCategoryDTO getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(PostCategoryDTO postCategory) {
        this.postCategory = postCategory;
    }
}
