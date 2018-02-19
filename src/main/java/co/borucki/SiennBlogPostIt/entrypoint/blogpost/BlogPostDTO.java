package co.borucki.SiennBlogPostIt.entrypoint.blogpost;


import co.borucki.SiennBlogPostIt.entrypoint.blogauthor.AuthorDTO;
import co.borucki.SiennBlogPostIt.entrypoint.blogpostcategory.PostCategoryDTO;

public class BlogPostDTO {
    private long id;
    private String title;
    private String contents;
    private AuthorDTO author;
    private int likeItCounter;
    private PostCategoryDTO postCategory;

    public BlogPostDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public int getLikeItCounter() {
        return likeItCounter;
    }

    public void setLikeItCounter(int likeItCounter) {
        this.likeItCounter = likeItCounter;
    }

    public PostCategoryDTO getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(PostCategoryDTO postCategory) {
        this.postCategory = postCategory;
    }
}
