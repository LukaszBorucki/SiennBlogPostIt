package co.borucki.SiennBlogPostIt.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "post")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title", length = 100, nullable = false)
    private String title;
    @Column(name = "contents", columnDefinition = "TEXT", nullable = false)
    private String contents;
    @ManyToOne()
    @JoinColumn(name = "author_id")
    @NotNull
    private BlogAuthor author;
    @Column(name = "like_it_counter")
    private int likeItCounter;
    @ManyToOne()
    @NotNull
    @JoinColumn(name = "post_category_id", nullable = false)
    private BlogPostCategory postCategory;

    public void likeIt() {
        this.likeItCounter++;
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

    public BlogAuthor getAuthor() {
        return author;
    }

    public void setAuthor(BlogAuthor author) {
        this.author = author;
    }

    public int getLikeItCounter() {
        return likeItCounter;
    }

    public void setLikeItCounter(int likeItCounter) {
        this.likeItCounter = likeItCounter;
    }

    public BlogPostCategory getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(BlogPostCategory postCategory) {
        this.postCategory = postCategory;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", author=" + author.getMail() +
                ", likeItCounter=" + likeItCounter +
                ", postCategory=" + postCategory.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPost blogPost = (BlogPost) o;
        return id == blogPost.id &&
                likeItCounter == blogPost.likeItCounter &&
                Objects.equals(title, blogPost.title) &&
                Objects.equals(contents, blogPost.contents) &&
                Objects.equals(author, blogPost.author) &&
                Objects.equals(postCategory, blogPost.postCategory);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, contents, author, likeItCounter, postCategory);
    }

    public void increaseLikeCounter() {
        this.likeItCounter++;
    }
}
