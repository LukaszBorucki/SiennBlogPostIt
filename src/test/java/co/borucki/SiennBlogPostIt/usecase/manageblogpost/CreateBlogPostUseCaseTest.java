package co.borucki.SiennBlogPostIt.usecase.manageblogpost;

import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.repositories.BlogPostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateBlogPostUseCaseTest {
    @Mock
    private BlogPostRepository blogPostRepository;
    @InjectMocks
    private CreateBlogPostUseCase createBlogPostUseCase;

    @Test
    public void givenBlogPost_whenCreate_createdBlogPostShouldBeReturned() {
        BlogPost blogPost = new BlogPost();
        when(blogPostRepository.save(blogPost)).thenReturn(blogPost);
        BlogPost actual = createBlogPostUseCase.create(blogPost);
        assertEquals(blogPost, actual);
    }
}