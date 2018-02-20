package co.borucki.SiennBlogPostIt.entrypoint.blogpost;

import co.borucki.SiennBlogPostIt.domain.BlogAuthor;
import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.domain.BlogPostCategory;
import co.borucki.SiennBlogPostIt.usecase.manageblogauthor.GetBlogAuthorUseCase;
import co.borucki.SiennBlogPostIt.usecase.manageblogpostcategory.GetBlogPostCategoryUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BlogPostFactoryTest {
    @Mock
    private GetBlogAuthorUseCase blogAuthorUseCase;
    @Mock
    private GetBlogPostCategoryUseCase blogPostCategoryUseCase;
    @InjectMocks
    private BlogPostFactory blogPostFactory;

    @Test
    public void givenBlogPostCreateDTO_whenCreateFromDTO_blogPostShouldBeReturned() {
        BlogPostCreateDTO blogPostCreateDTO = buildPostCreateDTO();
        BlogAuthor blogAuthor = new BlogAuthor();
        Optional<BlogAuthor> optionalBlogAuthor = Optional.of(blogAuthor);
        when(blogAuthorUseCase.getById(1)).thenReturn(optionalBlogAuthor);
        BlogPostCategory category = new BlogPostCategory();
        Optional<BlogPostCategory> optionalBlogPostCategory = Optional.of(category);
        when(blogPostCategoryUseCase.getById(1)).thenReturn(optionalBlogPostCategory);
        BlogPost expected = buildExpectedBlogPost(blogPostCreateDTO, blogAuthor, category);
        BlogPost actual = blogPostFactory.createFromDTO(blogPostCreateDTO);
        assertEquals(expected, actual);
    }

    private BlogPost buildExpectedBlogPost(BlogPostCreateDTO blogPostCreateDTO, BlogAuthor blogAuthor, BlogPostCategory category) {
        BlogPost expected = new BlogPost();
        expected.setAuthor(blogAuthor);
        expected.setPostCategory(category);
        expected.setTitle(blogPostCreateDTO.getTitle());
        expected.setContents(blogPostCreateDTO.getContents());
        return expected;
    }

    private BlogPostCreateDTO buildPostCreateDTO() {
        BlogPostCreateDTO blogPostCreateDTO = new BlogPostCreateDTO();
        blogPostCreateDTO.setAuthorId(1);
        blogPostCreateDTO.setPostCategoryId(1);
        blogPostCreateDTO.setContents("some contents");
        blogPostCreateDTO.setTitle("some title");
        return blogPostCreateDTO;
    }
}