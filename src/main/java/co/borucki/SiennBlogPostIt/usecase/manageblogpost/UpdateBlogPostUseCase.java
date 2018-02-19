package co.borucki.SiennBlogPostIt.usecase.manageblogpost;

import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.repositories.BlogPostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UpdateBlogPostUseCase {
    private final BlogPostRepository blogPostRepository;

    public UpdateBlogPostUseCase(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Transactional
    public BlogPost updateLike(long blogPostId) {
        BlogPost updatedBlogPost = this.fetchBlogPost(blogPostId);
        updatedBlogPost.setLikeItCounter(updatedBlogPost.getLikeItCounter() + 1);
        return updatedBlogPost;
    }

    private BlogPost fetchBlogPost(long blogPostId) {
        return blogPostRepository.getById(blogPostId).orElseThrow(() -> new BlogPostDoesNotExist(blogPostId));
    }
}
