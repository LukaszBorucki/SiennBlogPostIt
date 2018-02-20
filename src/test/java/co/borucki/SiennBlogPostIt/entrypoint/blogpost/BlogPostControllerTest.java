package co.borucki.SiennBlogPostIt.entrypoint.blogpost;

import co.borucki.SiennBlogPostIt.domain.BlogAuthor;
import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.domain.BlogPostCategory;
import co.borucki.SiennBlogPostIt.entrypoint.blogbestauthor.BlogBestAuthorByCategoryDTO;
import co.borucki.SiennBlogPostIt.entrypoint.blogbestauthor.BlogBestAuthorByCategoryMapper;
import co.borucki.SiennBlogPostIt.repositories.BlogPostRepository;
import co.borucki.SiennBlogPostIt.usecase.manageblogauthor.BlogAuthorDoesNotExist;
import co.borucki.SiennBlogPostIt.usecase.manageblogpost.BlogPostDoesNotExist;
import co.borucki.SiennBlogPostIt.usecase.manageblogpost.CreateBlogPostUseCase;
import co.borucki.SiennBlogPostIt.usecase.manageblogpost.GetBlogPostUseCase;
import co.borucki.SiennBlogPostIt.usecase.manageblogpost.UpdateBlogPostUseCase;
import co.borucki.SiennBlogPostIt.usecase.manageblogpostcategory.BlogPostCategoryDoesNotExist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BlogPostControllerTest {
    @Mock
    private GetBlogPostUseCase getBlogPostUseCase;
    @Mock
    private UpdateBlogPostUseCase updateBlogPostUseCase;
    @Mock
    private CreateBlogPostUseCase createBlogPostUseCase;
    @Mock
    private BlogPostFactory blogPostFactory;
    @Mock
    private BlogPostRepository blogPostRepository;
    @InjectMocks
    private BlogPostController blogPostController;


    @Test
    public void givenBlogPostList_whenGetAll_thenBlogPostListMappedToDtoShouldBeReturned() {
        List<BlogPost> blogPosts = createBlogPost(10);
        List<BlogPostDTO> expected = BlogPostMapper.toDTO(blogPosts);
        when(getBlogPostUseCase.getAll()).thenReturn(blogPosts);
        ResponseEntity<List<BlogPostDTO>> response = blogPostController.getAll();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertBlogPostList(expected, response.getBody());
    }

    private void assertBlogPostList(List<BlogPostDTO> expected, List<BlogPostDTO> body) {
        for (int i = 0; i < expected.size(); i++) {
            assertBlogPost(expected.get(i), body.get(i));
        }
    }

    @Test
    public void givenExistingPostId_whenLikeIt_blogPostMappedToDtoShouldBeReturned() {
        BlogPost blogPost = createBlogPost(1).get(0);
        BlogPostDTO expect = BlogPostMapper.toDTO(blogPost);
        when(updateBlogPostUseCase.updateLike(1)).thenReturn(blogPost);
        ResponseEntity<BlogPostDTO> response = blogPostController.likeIt(1);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertBlogPost(expect, response.getBody());
    }

    private void assertBlogPost(BlogPostDTO expect, BlogPostDTO body) {
        assertEquals(expect.getTitle(), body.getTitle());
        assertEquals(expect.getPostCategory().getName(), body.getPostCategory().getName());
        assertEquals(expect.getPostCategory().getId(), body.getPostCategory().getId());
        assertEquals(expect.getLikeItCounter(), body.getLikeItCounter());
        assertEquals(expect.getContents(), body.getContents());
        assertEquals(expect.getId(), body.getId());
        assertEquals(expect.getAuthor().getSurname(), body.getAuthor().getSurname());
        assertEquals(expect.getAuthor().getId(), body.getAuthor().getId());
        assertEquals(expect.getAuthor().getName(), body.getAuthor().getName());
    }

    @Test(expected = BlogPostDoesNotExist.class)
    public void givenNotExistingPostId_whenLikeIt_badRequestStatusCodeShouldBeReturned() {
        when(updateBlogPostUseCase.updateLike(1)).thenThrow(new BlogPostDoesNotExist(1));
        blogPostController.likeIt(1);
    }

    @Test
    public void givenExistingAuthorIdAndExistingBlogPostCategoryId_whenCreatePost_blogPostMappedToDtoShouldBeReturned() {
        BlogPostCreateDTO givenBlogPostCreateDTO = buildPostCreateDTO();
        BlogPost blogPost = buildBlogPost(givenBlogPostCreateDTO);

        when(blogPostFactory.createFromDTO(givenBlogPostCreateDTO)).thenReturn(blogPost);
        when(createBlogPostUseCase.create(blogPost)).thenReturn(blogPost);
        ResponseEntity<BlogPostDTO> response = blogPostController.createPost(givenBlogPostCreateDTO);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertBlogPost(BlogPostMapper.toDTO(blogPost), response.getBody());
    }

    private BlogPost buildBlogPost(BlogPostCreateDTO givenBlogPostCreateDTO) {
        BlogPost blogPost = createBlogPost(1).get(0);
        blogPost.setTitle(givenBlogPostCreateDTO.getTitle());
        blogPost.setContents(givenBlogPostCreateDTO.getContents());
        blogPost.setId(0);
        blogPost.setLikeItCounter(0);
        return blogPost;
    }

    private BlogPostCreateDTO buildPostCreateDTO() {
        BlogPostCreateDTO givenBlogPostCreateDTO = new BlogPostCreateDTO();
        givenBlogPostCreateDTO.setTitle("Created post title");
        givenBlogPostCreateDTO.setContents("Created Blog Post contents");
        givenBlogPostCreateDTO.setPostCategoryId(1);
        givenBlogPostCreateDTO.setAuthorId(1);
        return givenBlogPostCreateDTO;
    }

    @Test(expected = BlogAuthorDoesNotExist.class)
    public void givenNotExistingAuthorAndExistingBlogPostCategory_whenCreatePost_exceptionShouldBeThrow() {
        BlogPostCreateDTO blogPostCreateDTO = new BlogPostCreateDTO();
        when(blogPostFactory.createFromDTO(blogPostCreateDTO)).thenThrow(new BlogAuthorDoesNotExist(1));
        blogPostController.createPost(blogPostCreateDTO);
    }

    @Test(expected = BlogPostCategoryDoesNotExist.class)
    public void givenExistingAuthorAndNotExistingBlogPostCategory_whenCreatePost_exceptionShouldBeThrow() {
        BlogPostCreateDTO blogPostCreateDTO = new BlogPostCreateDTO();
        when(blogPostFactory.createFromDTO(blogPostCreateDTO)).thenThrow(new BlogPostCategoryDoesNotExist(1));
        blogPostController.createPost(blogPostCreateDTO);
    }

    @Test
    public void whenGetAllHeaders_thanBlogHeaderListMappedToDtoShouldBeReturned() {
        List<BlogPost> blogPosts = createBlogPost(10);
        when(getBlogPostUseCase.getAll()).thenReturn(blogPosts);
        ResponseEntity<List<BlogHeaderDTO>> response = blogPostController.getAllHeaders();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertEquals(BlogHeaderMapper.toHeaderDTO(blogPosts), response.getBody());
    }

    @Test
    public void givenBlogPostList_whenGetBestRatedAuthorByCategory_thanBlogBestAuthorByCategoryListShouldReturn() {
        List<BlogPost> blogPosts = createBlogPost(10);
        List<BlogPost> listOfBestAuthorByCategory = new ArrayList<>();
        listOfBestAuthorByCategory.add(blogPosts.get(6));
        listOfBestAuthorByCategory.add(blogPosts.get(7));
        listOfBestAuthorByCategory.add(blogPosts.get(8));
        listOfBestAuthorByCategory.add(blogPosts.get(9));

        when(getBlogPostUseCase.getAll()).thenReturn(blogPosts);
        when(blogPostRepository.findAll()).thenReturn(blogPosts);
        when(getBlogPostUseCase.getBestAuthorsByCategory()).thenReturn(listOfBestAuthorByCategory);
        ResponseEntity<List<BlogBestAuthorByCategoryDTO>> response = blogPostController.getBestRatedAuthorByCategory();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertBestRatedAuthorByCategory(BlogBestAuthorByCategoryMapper.toDTO(listOfBestAuthorByCategory), response.getBody());
    }

    private void assertBestRatedAuthorByCategory(List<BlogBestAuthorByCategoryDTO> blogBestAuthorByCategoryDTOS, List<BlogBestAuthorByCategoryDTO> body) {
        for (int i = 0; i < blogBestAuthorByCategoryDTOS.size(); i++) {
            assertEquals(blogBestAuthorByCategoryDTOS.get(i).getAuthor().getId(), body.get(i).getAuthor().getId());
            assertEquals(blogBestAuthorByCategoryDTOS.get(i).getAuthor().getName(), body.get(i).getAuthor().getName());
            assertEquals(blogBestAuthorByCategoryDTOS.get(i).getAuthor().getSurname(), body.get(i).getAuthor().getSurname());
            assertEquals(blogBestAuthorByCategoryDTOS.get(i).getPostCategory().getId(), body.get(i).getPostCategory().getId());
            assertEquals(blogBestAuthorByCategoryDTOS.get(i).getPostCategory().getName(), body.get(i).getPostCategory().getName());
        }
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