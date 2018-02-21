package co.borucki.SiennBlogPostIt.entrypoint.blogpost;

import co.borucki.SiennBlogPostIt.domain.BlogAuthor;
import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.domain.BlogPostCategory;
import co.borucki.SiennBlogPostIt.domain.BlogPostHeader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BlogPostHeaderFactoryTest {
    @InjectMocks
    private BlogPostHeaderFactory blogPostHeaderFactory;

    @Test
    public void givenBlogPost_whenCreateFromBlogPost_thanBlogPostHeaderShouldBeReturned() {
        BlogPost givenBlogPost = createBlogPosts(10).get(0);
        BlogPostHeader actual = blogPostHeaderFactory.createFromBlogPost(givenBlogPost);
        BlogPostHeader expected = createExpectedBlogPostHeader(givenBlogPost);
        assertBlogPostHeaders(actual, expected);
    }

    @Test
    public void givenBlogPostList_whenCreateFromBlogPost_thanBlogPostHeaderListShouldBeReturned() {
        List<BlogPost> givenList = createBlogPosts(50);
        List<BlogPostHeader> actualList = blogPostHeaderFactory.createFromBlogPost(givenList);
        List<BlogPostHeader> expectedList = createExpectedBlogPostHeaderList(givenList);
        assertBlogPostHeadersList(actualList, expectedList);
    }

    private void assertBlogPostHeadersList(List<BlogPostHeader> actualList, List<BlogPostHeader> expectedList) {
        for (int i = 0; i < actualList.size(); i++) {
            assertBlogPostHeaders(actualList.get(i), expectedList.get(i));
        }
    }

    private List<BlogPostHeader> createExpectedBlogPostHeaderList(List<BlogPost> givenList) {
        List<BlogPostHeader> createdList = new ArrayList<>();
        givenList.forEach(entry -> createdList.add(createExpectedBlogPostHeader(entry)));
        return createdList;
    }

    private BlogPostHeader createExpectedBlogPostHeader(BlogPost givenBlogPost) {
        BlogPostHeader expected = new BlogPostHeader();
        expected.setId(givenBlogPost.getId());
        expected.setTitle(givenBlogPost.getTitle());
        expected.setContents(givenBlogPost.getContents().substring(0, givenBlogPost.
                getContents().length() > 30 ? 30 : givenBlogPost.getContents().length()));
        return expected;
    }

    private void assertBlogPostHeaders(BlogPostHeader actual, BlogPostHeader expected) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getContents(), actual.getContents());
    }

    private List<BlogPost> createBlogPosts(int numberOfBlogPost) {
        List<BlogPost> blogPosts = new ArrayList<>();
        for (int i = 0; i < numberOfBlogPost; i++) {
            BlogPost blogPost = new BlogPost();
            blogPost.setId(i);
            blogPost.setLikeItCounter(i);
            blogPost.setContents("Contents of blog " + i);
            blogPost.setTitle("Title of Blog " + i);
            blogPost.setPostCategory(createBlogCategory(4).get(i % 4));
            blogPost.setAuthor(createBlogAuthor(4).get(i % 4));
            blogPosts.add(blogPost);
        }
        return blogPosts;
    }

    private List<BlogAuthor> createBlogAuthor(int numberOfAuthors) {
        List<BlogAuthor> authors = new ArrayList<>();
        for (int i = 0; i < numberOfAuthors; i++) {
            BlogAuthor blogAuthor = new BlogAuthor();
            blogAuthor.setId(i);
            blogAuthor.setName("AuthorName " + i);
            blogAuthor.setSurname("Surname " + i);
            blogAuthor.setMail("mail address " + i);
            authors.add(blogAuthor);
        }
        return authors;
    }

    private List<BlogPostCategory> createBlogCategory(int numberOfCategory) {
        List<BlogPostCategory> postCategories = new ArrayList<>();
        for (int i = 0; i < numberOfCategory; i++) {
            BlogPostCategory category = new BlogPostCategory();
            category.setId(i);
            category.setName("Categoty " + i);
            postCategories.add(category);
        }
        return postCategories;
    }
}