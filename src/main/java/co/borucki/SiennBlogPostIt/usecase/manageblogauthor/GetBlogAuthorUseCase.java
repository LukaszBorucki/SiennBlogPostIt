package co.borucki.SiennBlogPostIt.usecase.manageblogauthor;

import co.borucki.SiennBlogPostIt.domain.BlogAuthor;
import co.borucki.SiennBlogPostIt.repositories.BlogAuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetBlogAuthorUseCase {
    private final BlogAuthorRepository blogAuthorRepository;
    private final CheckIfBlogAuthorExistUseCase checkIfBlogAuthorExistUseCase;

    public GetBlogAuthorUseCase(BlogAuthorRepository blogAuthorRepository, CheckIfBlogAuthorExistUseCase checkIfBlogAuthorExistUseCase) {
        this.blogAuthorRepository = blogAuthorRepository;
        this.checkIfBlogAuthorExistUseCase = checkIfBlogAuthorExistUseCase;
    }

    public Optional<BlogAuthor> getById(long id) {
        if (!checkIfBlogAuthorExistUseCase.doesExist(id)) {
            throw new BlogAuthorDoesNotExist(id);
        }
        return blogAuthorRepository.findById(id);
    }
}
