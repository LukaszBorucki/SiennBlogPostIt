package co.borucki.SiennBlogPostIt.entrypoint.blogbestauthor;

import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.entrypoint.blogauthor.AuthorMapper;
import co.borucki.SiennBlogPostIt.entrypoint.blogpostcategory.PostCategoryMapper;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class BlogBestAuthorByCategoryMapper {
    public static List<BlogBestAuthorByCategoryDTO> toDTO(List<BlogPost> blogPosts) {
        List<BlogBestAuthorByCategoryDTO> blogBestAuthorByCategoryDTOS = new ArrayList<>();
        for (BlogPost blogPost : blogPosts) {
            BlogBestAuthorByCategoryDTO blogBestAuthorByCategoryDTO = new BlogBestAuthorByCategoryDTO();
            blogBestAuthorByCategoryDTO.setAuthor(AuthorMapper.toAuthorDTO(blogPost.getAuthor()));
            blogBestAuthorByCategoryDTO.setPostCategory(PostCategoryMapper.toPostCategoryDTO(blogPost.getPostCategory()));
            blogBestAuthorByCategoryDTOS.add(blogBestAuthorByCategoryDTO);
        }
        return blogBestAuthorByCategoryDTOS;
    }
}
