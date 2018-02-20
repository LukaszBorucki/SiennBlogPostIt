package co.borucki.SiennBlogPostIt.usecase.manageblogpostcategory;

import co.borucki.SiennBlogPostIt.domain.BlogPostCategory;
import co.borucki.SiennBlogPostIt.repositories.BlogPostCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetBlogPostCategoryUseCaseTest {
    @Mock
    private BlogPostCategoryRepository blogPostCategoryRepository;
    @Mock
    private CheckIfBlogPostCategoryExistUseCase checkIfBlogPostCategoryExistUseCase;
    @InjectMocks
    private GetBlogPostCategoryUseCase getBlogPostCategoryUseCase;

    @Test(expected = BlogPostCategoryDoesNotExist.class)
    public void givenNotExistingId_whenGetById_exceptionShouldBeThrow() {
        when(checkIfBlogPostCategoryExistUseCase.doesExist(1)).thenReturn(false);
        getBlogPostCategoryUseCase.getById(1);
    }

    @Test
    public void givenExistingId_whenGetById_blogPostShouldBeReturned() {
        Optional<BlogPostCategory> expectedBlogPostCategory = Optional.of(new BlogPostCategory());
        when(checkIfBlogPostCategoryExistUseCase.doesExist(3)).thenReturn(true);
        when(blogPostCategoryRepository.getById(3)).thenReturn(expectedBlogPostCategory);
        Optional<BlogPostCategory> actual = blogPostCategoryRepository.getById(3);
        assertEquals(expectedBlogPostCategory, actual);

    }

}