package co.borucki.SiennBlogPostIt.usecase.manageblogpost;

import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.repositories.BlogPostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateBlogPostUseCase {
    private final BlogPostRepository blogPostRepository;

    public CreateBlogPostUseCase(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public BlogPost create(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }
}
