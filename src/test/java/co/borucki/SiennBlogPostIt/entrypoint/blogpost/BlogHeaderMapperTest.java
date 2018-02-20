package co.borucki.SiennBlogPostIt.entrypoint.blogpost;

import co.borucki.SiennBlogPostIt.domain.BlogAuthor;
import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.domain.BlogPostCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BlogHeaderMapperTest {
    @InjectMocks
    private BlogHeaderMapper blogHeaderMapper;

    @Test
    public void givenBlogPost_whenToHeaderDTO_blogHeaderDtoShouldBeReturned() {
        BlogPost givenBlogPost = createBlogPost(1).get(0);
        BlogHeaderDTO actual = BlogHeaderMapper.toHeaderDTO(givenBlogPost);
        BlogHeaderDTO expected = new BlogHeaderDTO(givenBlogPost.getId(),
                givenBlogPost.getTitle(),
                givenBlogPost.getContents());
        assertEquals(expected, actual);
    }


    @Test
    public void givenBlogPostList_whenToHeaderDTO_blogHeaderListDtoShouldBeReturned() {
        List<BlogPost> blogPosts = createBlogPost(10);
        List<BlogHeaderDTO> actual = BlogHeaderMapper.toHeaderDTO(blogPosts);
        List<BlogHeaderDTO> expected = new ArrayList<>();
        for (BlogPost blogPost : blogPosts) {
            expected.add(new BlogHeaderDTO(blogPost.getId(),
                    blogPost.getTitle(),
                    blogPost.getContents()));
        }
        assertEquals(expected, actual);

    }

    private List<BlogPost> createBlogPost(int numberOfBlogPost) {
        List<BlogPost> blogPosts = new ArrayList<>();

        for (int i = 0; i < numberOfBlogPost; i++) {
            BlogPost blogPost = new BlogPost();
            blogPost.setId(i);
            blogPost.setContents("Some content " + i);
            blogPost.setTitle("Blog TITLE - " + i);
            blogPost.setPostCategory(createBlogPostCategory(4).get(i % 4));
            blogPost.setLikeItCounter(i);
            blogPost.setAuthor(createBlogAuthor(4).get(i % 4));
            blogPosts.add(blogPost);
        }
        return blogPosts;
    }

    private List<BlogPostCategory> createBlogPostCategory(int numberOfBlogPostCategory) {
        List<BlogPostCategory> blogPostCategories = new ArrayList<>();
        for (int i = 0; i < numberOfBlogPostCategory; i++) {
            BlogPostCategory blogPostCategory = new BlogPostCategory();
            blogPostCategory.setId(i);
            blogPostCategory.setName("Category name " + i);
            blogPostCategories.add(blogPostCategory);
        }
        return blogPostCategories;
    }

    private List<BlogAuthor> createBlogAuthor(int numberOfBlogAuthor) {
        List<BlogAuthor> blogAuthors = new ArrayList<>();
        for (int i = 0; i < numberOfBlogAuthor; i++) {
            BlogAuthor blogAuthor = new BlogAuthor();
            blogAuthor.setId(i);
            blogAuthor.setName("Name " + i);
            blogAuthor.setSurname("Surname " + i);
            blogAuthor.setMail("mail " + i);
            blogAuthors.add(blogAuthor);
        }
        return blogAuthors;
    }
}