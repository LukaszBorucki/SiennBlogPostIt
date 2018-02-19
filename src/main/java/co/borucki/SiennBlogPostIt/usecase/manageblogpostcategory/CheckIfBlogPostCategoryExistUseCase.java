package co.borucki.SiennBlogPostIt.usecase.manageblogpostcategory;

import co.borucki.SiennBlogPostIt.repositories.BlogPostCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CheckIfBlogPostCategoryExistUseCase {
    private final BlogPostCategoryRepository blogPostCategoryRepository;

    public CheckIfBlogPostCategoryExistUseCase(BlogPostCategoryRepository blogPostCategoryRepository) {
        this.blogPostCategoryRepository = blogPostCategoryRepository;
    }

    public boolean doesExist(int id) {
        return blogPostCategoryRepository.exists(id);
    }

}
