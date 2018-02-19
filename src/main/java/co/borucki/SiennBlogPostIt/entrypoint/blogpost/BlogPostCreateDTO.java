package co.borucki.SiennBlogPostIt.entrypoint.blogpost;

public class BlogPostCreateDTO {
    private String title;
    private String contents;
    private long authorId;
    private int postCategoryId;


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

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public int getPostCategoryId() {
        return postCategoryId;
    }

    public void setPostCategoryId(int postCategoryId) {
        this.postCategoryId = postCategoryId;
    }
}
