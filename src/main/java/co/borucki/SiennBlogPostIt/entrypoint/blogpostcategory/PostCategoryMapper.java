package co.borucki.SiennBlogPostIt.entrypoint.blogpostcategory;

import co.borucki.SiennBlogPostIt.domain.BlogPostCategory;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class PostCategoryMapper {
    public static PostCategoryDTO toPostCategoryDTO(BlogPostCategory postCategory) {
        PostCategoryDTO postCategoryDTO = new PostCategoryDTO();
        postCategoryDTO.setId(postCategory.getId());
        postCategoryDTO.setName(postCategory.getName());
        return postCategoryDTO;
    }

    public static List<PostCategoryDTO> toPostCategoryDTO(List<BlogPostCategory> postCategory) {
        List<PostCategoryDTO> postCategoryDTOS = new ArrayList<>();
        for (BlogPostCategory blogPostCategory : postCategory) {
            postCategoryDTOS.add(toPostCategoryDTO(blogPostCategory));
        }
        return postCategoryDTOS;
    }
}
