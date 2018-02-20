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

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateBlogPostUseCaseTest {
    @Mock
    private BlogPostRepository blogPostRepository;
    @InjectMocks
    private UpdateBlogPostUseCase updateBlogPostUseCase;

    @Test
    public void givenBlogPostId_whenUpdateLike_updatedLikeCounterFieldBlogPostShouldBeReturned() {
        BlogPost blogPost1 = getBlogPost();
        Optional<BlogPost> optionalBlogPost = Optional.ofNullable(blogPost1);
        when(blogPostRepository.getById(1)).thenReturn(optionalBlogPost);
        blogPost1.setLikeItCounter(blogPost1.getLikeItCounter() + 1);
        BlogPost actual = updateBlogPostUseCase.updateLike(1);
        assertEquals(blogPost1, actual);
    }

    private BlogPost getBlogPost() {
        BlogAuthor author1 = new BlogAuthor();
        author1.setId(1);
        author1.setName("John");
        author1.setSurname("Smith");
        author1.setMail("smith@gmail.com");
        BlogPostCategory category1 = new BlogPostCategory();
        category1.setId(1);
        category1.setName("Opinion");
        BlogPost blogPost1 = new BlogPost();
        blogPost1.setId(1);
        blogPost1.setContents("contents 1");
        blogPost1.setTitle("Title 1");
        blogPost1.setLikeItCounter(4);
        blogPost1.setAuthor(author1);
        blogPost1.setPostCategory(category1);
        return blogPost1;
    }
}