package co.borucki.SiennBlogPostIt.usecase.manageblogauthor;

import co.borucki.SiennBlogPostIt.domain.BlogAuthor;
import co.borucki.SiennBlogPostIt.repositories.BlogAuthorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetBlogAuthorUseCaseTest {
    @Mock
    private BlogAuthorRepository blogAuthorRepository;
    @Mock
    private CheckIfBlogAuthorExistUseCase checkIfBlogAuthorExistUseCase;
    @InjectMocks

    private GetBlogAuthorUseCase getBlogAuthorUseCase;

    @Test(expected = BlogAuthorDoesNotExist.class)
    public void givenNotExistingAuthorId_whenGetById_exceptionShouldBeThrow() {
        when(checkIfBlogAuthorExistUseCase.doesExist(5)).thenReturn(false);
        getBlogAuthorUseCase.getById(5);
    }

    @Test
    public void givenExistingAuthorId_whenGetById_BlogAuthorShouldBeReturned() {
        Optional<BlogAuthor> expectedBlogAuthor = Optional.of(new BlogAuthor());
        when(checkIfBlogAuthorExistUseCase.doesExist(3)).thenReturn(true);
        when(blogAuthorRepository.findById(3)).thenReturn(expectedBlogAuthor);
        Optional<BlogAuthor> actual = getBlogAuthorUseCase.getById(3);
        assertEquals(expectedBlogAuthor, actual);
    }
}