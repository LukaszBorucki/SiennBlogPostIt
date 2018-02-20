package co.borucki.SiennBlogPostIt.usecase.manageblogpost;

import co.borucki.SiennBlogPostIt.domain.BlogAuthor;
import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.domain.BlogPostCategory;
import co.borucki.SiennBlogPostIt.repositories.BlogPostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetBlogPostUseCaseTest {
    @Mock
    private BlogPostRepository blogPostRepository;
    @InjectMocks
    private GetBlogPostUseCase getBlogPostUseCase;

    @Test
    public void whenGetAll_blogPostListShouldBeReturned() {
        List<BlogPost> blogPosts = new ArrayList<>();
        blogPosts.add(new BlogPost());
        when(blogPostRepository.findAll()).thenReturn(blogPosts);
        List<BlogPost> actual = getBlogPostUseCase.getAll();
        assertEquals(blogPosts, actual);
    }

    @Test
    public void givenId_whenGetById_optionalBlogPostShouldBeReturned() {
        Optional<BlogPost> expectedOptionalBlogPost = Optional.of(new BlogPost());
        when(blogPostRepository.getById(1)).thenReturn(expectedOptionalBlogPost);
        Optional<BlogPost> actual = getBlogPostUseCase.getById(1);
        assertEquals(expectedOptionalBlogPost, actual);
    }

    @Test
    public void whenGetBestAuthorByCategory_blogPostListShouldBeReturned() {
        List<BlogPost> blogPosts = createBlogPost(10);
        List<BlogPost> expected = createExpectedBlogPostList(blogPosts);
        when(blogPostRepository.findAll()).thenReturn(blogPosts);
        List<BlogPost> actual = getBlogPostUseCase.getBestAuthorsByCategory();
        assertBestAuthorByCategory(expected, actual);
    }

    private void assertBestAuthorByCategory(List<BlogPost> expected, List<BlogPost> actual) {
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i).getId(), actual.get(i).getId());
            assertEquals(expected.get(i).getLikeItCounter(), actual.get(i).getLikeItCounter());
            assertEquals(expected.get(i).getTitle(), actual.get(i).getTitle());
            assertEquals(expected.get(i).getContents(), actual.get(i).getContents());
            assertEquals(expected.get(i).getAuthor().getId(), actual.get(i).getAuthor().getId());
            assertEquals(expected.get(i).getAuthor().getMail(), actual.get(i).getAuthor().getMail());
            assertEquals(expected.get(i).getAuthor().getName(), actual.get(i).getAuthor().getName());
            assertEquals(expected.get(i).getAuthor().getSurname(), actual.get(i).getAuthor().getSurname());
            assertEquals(expected.get(i).getPostCategory().getName(), actual.get(i).getPostCategory().getName());
            assertEquals(expected.get(i).getPostCategory().getId(), actual.get(i).getPostCategory().getId());
        }
    }

    private List<BlogPost> createExpectedBlogPostList(List<BlogPost> blogPosts) {
        List<BlogPost> expected = new ArrayList<>();
        expected.add(blogPosts.get(7));
        expected.add(blogPosts.get(8));
        expected.add(blogPosts.get(6));
        expected.add(blogPosts.get(9));
        return expected;
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