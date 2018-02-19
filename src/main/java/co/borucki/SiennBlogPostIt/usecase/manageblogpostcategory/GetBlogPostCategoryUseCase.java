package co.borucki.SiennBlogPostIt.usecase.manageblogpostcategory;

import co.borucki.SiennBlogPostIt.domain.BlogPostCategory;
import co.borucki.SiennBlogPostIt.repositories.BlogPostCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetBlogPostCategoryUseCase {
    private BlogPostCategoryRepository blogPostCategoryRepository;
    private final CheckIfBlogPostCategoryExistUseCase checkIfBlogPostCategoryExistUseCase;

    public GetBlogPostCategoryUseCase(BlogPostCategoryRepository blogPostCategoryRepository, CheckIfBlogPostCategoryExistUseCase checkIfBlogPostCategoryExistUseCase) {
        this.blogPostCategoryRepository = blogPostCategoryRepository;
        this.checkIfBlogPostCategoryExistUseCase = checkIfBlogPostCategoryExistUseCase;
    }

    public Optional<BlogPostCategory> getById(int id) {
        if (checkIfBlogPostCategoryExistUseCase.doesExist(id)) {
            return blogPostCategoryRepository.getById(id);
        } else {
            throw new BlogPostCategoryDoesNotExist(id);
        }
    }
}
