package co.borucki.SiennBlogPostIt.usecase.manageblogauthor;

import co.borucki.SiennBlogPostIt.repositories.BlogAuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class CheckIfBlogAuthorExistUseCase {
    private final BlogAuthorRepository blogAuthorRepository;

    public CheckIfBlogAuthorExistUseCase(BlogAuthorRepository blogAuthorRepository) {
        this.blogAuthorRepository = blogAuthorRepository;
    }

    public boolean doesExist(long id) {
        return blogAuthorRepository.exists(id);
    }

}
