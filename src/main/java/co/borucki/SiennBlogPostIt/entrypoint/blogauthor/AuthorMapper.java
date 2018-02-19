package co.borucki.SiennBlogPostIt.entrypoint.blogauthor;

import co.borucki.SiennBlogPostIt.domain.BlogAuthor;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class AuthorMapper {
    public static AuthorDTO toAuthorDTO(BlogAuthor author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setSurname(author.getSurname());
        return authorDTO;
    }

    public static List<AuthorDTO> toAuthorDTO(List<BlogAuthor> author) {
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        for (BlogAuthor blogAuthor : author) {
            authorDTOS.add(toAuthorDTO(blogAuthor));
        }
        return authorDTOS;
    }
}
