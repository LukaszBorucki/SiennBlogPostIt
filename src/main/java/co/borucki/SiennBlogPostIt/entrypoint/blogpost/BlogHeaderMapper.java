package co.borucki.SiennBlogPostIt.entrypoint.blogpost;

import co.borucki.SiennBlogPostIt.domain.BlogPost;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class BlogHeaderMapper {
    public static BlogHeaderDTO toHeaderDTO(BlogPost blogPost) {
        BlogHeaderDTO blogHeaderDTO = new BlogHeaderDTO();
        blogHeaderDTO.setContents(blogPost.getContents());
        blogHeaderDTO.setTitle(blogPost.getTitle());
        blogHeaderDTO.setId(blogPost.getId());
        return blogHeaderDTO;
    }

    public static List<BlogHeaderDTO> toHeaderDTO(List<BlogPost> blogPosts) {
        List<BlogHeaderDTO> mappedBlogHeaderDTOS = new ArrayList<>();
        for (BlogPost blogPost : blogPosts) {
            mappedBlogHeaderDTOS.add(toHeaderDTO(blogPost));
        }
        return mappedBlogHeaderDTOS;
    }
}
