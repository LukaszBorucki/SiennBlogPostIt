package co.borucki.SiennBlogPostIt.usecase.manageblogauthor;

import co.borucki.SiennBlogPostIt.repositories.BlogAuthorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckIfBlogAuthorExistUseCaseTest {
    @Mock
    private BlogAuthorRepository blogAuthorRepository;
    @InjectMocks
    private CheckIfBlogAuthorExistUseCase checkIfBlogAuthorExistUseCase;

    @Test
    public void givenNotExistingId_whenDoesExist_shouldReturnFalse() {
        when(blogAuthorRepository.exists(5L)).thenReturn(false);
        boolean actual = checkIfBlogAuthorExistUseCase.doesExist(5);
        assertEquals(false, actual);
    }

    @Test
    public void givenExistingId_whenDoesExist_shouldReturnTrue() {
        when(blogAuthorRepository.exists(5L)).thenReturn(true);
        boolean actual = checkIfBlogAuthorExistUseCase.doesExist(5);
        assertEquals(true, actual);
    }
}