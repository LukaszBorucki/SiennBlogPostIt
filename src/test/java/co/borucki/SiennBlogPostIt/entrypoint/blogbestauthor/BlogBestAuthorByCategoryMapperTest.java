package co.borucki.SiennBlogPostIt.entrypoint.blogbestauthor;

import co.borucki.SiennBlogPostIt.domain.BlogAuthor;
import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.domain.BlogPostCategory;
import co.borucki.SiennBlogPostIt.entrypoint.blogauthor.AuthorMapper;
import co.borucki.SiennBlogPostIt.entrypoint.blogpostcategory.PostCategoryMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BlogBestAuthorByCategoryMapperTest {

    @Test
    public void givenListBlogPost_whenToDTO_thenBlogBestAuthorByCategoryDTOLustShouldBeReturned() {
        List<BlogPost> blogPosts = createBlogPost(10);
        List<BlogBestAuthorByCategoryDTO> actual = BlogBestAuthorByCategoryMapper.toDTO(blogPosts);
        List<BlogBestAuthorByCategoryDTO> expected = buildBlogBestAuthorByCategoryDTOList();
        assertBlogBestAuthorByCategoryDTO(expected, actual);
    }

    private void assertBlogBestAuthorByCategoryDTO(List<BlogBestAuthorByCategoryDTO> expected, List<BlogBestAuthorByCategoryDTO> actual) {
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getPostCategory().getName(), actual.get(i).getPostCategory().getName());
            assertEquals(expected.get(i).getPostCategory().getId(), actual.get(i).getPostCategory().getId());
            assertEquals(expected.get(i).getAuthor().getId(), actual.get(i).getAuthor().getId());
            assertEquals(expected.get(i).getAuthor().getName(), actual.get(i).getAuthor().getName());
            assertEquals(expected.get(i).getAuthor().getSurname(), actual.get(i).getAuthor().getSurname());
        }
    }

    private List<BlogBestAuthorByCategoryDTO> buildBlogBestAuthorByCategoryDTOList() {
        List<BlogBestAuthorByCategoryDTO> expected = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BlogBestAuthorByCategoryDTO blogBestAuthorByCategoryDTO = new BlogBestAuthorByCategoryDTO();
            blogBestAuthorByCategoryDTO.setPostCategory(PostCategoryMapper.toPostCategoryDTO(createBlogPostCategory(4).get(i % 4)));
            blogBestAuthorByCategoryDTO.setAuthor(AuthorMapper.toAuthorDTO(createBlogAuthor(4).get(i % 4)));
            expected.add(blogBestAuthorByCategoryDTO);
        }
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