package co.borucki.SiennBlogPostIt.usecase.manageblogpostcategory;

import co.borucki.SiennBlogPostIt.repositories.BlogPostCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckIfBlogPostCategoryExistUseCaseTest {
    @Mock
    private BlogPostCategoryRepository blogPostCategoryRepository;
    @InjectMocks
    private CheckIfBlogPostCategoryExistUseCase checkIfBlogPostCategoryExistUseCase;

    @Test
    public void givenNotExistingId_whenDoesExist_shouldReturnFalse() {
        when(blogPostCategoryRepository.exists(3)).thenReturn(false);
        boolean actual = blogPostCategoryRepository.exists(3);
        assertEquals(false, actual);
    }

    @Test
    public void givenExistingId_whenDoesExist_shouldReturnTrue() {
        when(blogPostCategoryRepository.exists(1)).thenReturn(true);
        boolean actual = blogPostCategoryRepository.exists(1);
        assertEquals(true, actual);
    }

}