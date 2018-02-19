package co.borucki.SiennBlogPostIt.entrypoint.blogpost;

import java.util.Objects;

public class BlogHeaderDTO {
    private long id;
    private String title;
    private String contents;

    public BlogHeaderDTO(long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents.substring(0, contents.length() < 30 ? contents.length() : 30);
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
        this.contents = contents.substring(0, contents.length() < 30 ? contents.length() : 30);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogHeaderDTO that = (BlogHeaderDTO) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, contents);
    }
}
