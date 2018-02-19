package co.borucki.SiennBlogPostIt.entrypoint.blogpost;

import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.entrypoint.blogauthor.AuthorMapper;
import co.borucki.SiennBlogPostIt.entrypoint.blogpostcategory.PostCategoryMapper;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class BlogPostMapper {
    public static BlogPostDTO toDTO(BlogPost blogPost) {

        BlogPostDTO blogPostDTO = new BlogPostDTO();
        blogPostDTO.setId(blogPost.getId());
        blogPostDTO.setAuthor(AuthorMapper.toAuthorDTO(blogPost.getAuthor()));
        blogPostDTO.setContents(blogPost.getContents());
        blogPostDTO.setLikeItCounter(blogPost.getLikeItCounter());
        blogPostDTO.setPostCategory(PostCategoryMapper.toPostCategoryDTO(blogPost.getPostCategory()));
        blogPostDTO.setTitle(blogPost.getTitle());
        return blogPostDTO;
    }

    public static List<BlogPostDTO> toDTO(List<BlogPost> blogPosts) {
        List<BlogPostDTO> blogPostDTOS = new ArrayList<>();
        for (BlogPost blogPost : blogPosts) {
            blogPostDTOS.add(toDTO(blogPost));
        }
        return blogPostDTOS;
    }
}
