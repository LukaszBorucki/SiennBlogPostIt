package co.borucki.SiennBlogPostIt.entrypoint.blogpost;

import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.usecase.manageblogauthor.GetBlogAuthorUseCase;
import co.borucki.SiennBlogPostIt.usecase.manageblogpostcategory.GetBlogPostCategoryUseCase;
import org.springframework.stereotype.Service;

@Service
public class BlogPostFactory {
    private final GetBlogAuthorUseCase blogAuthorUseCase;
    private final GetBlogPostCategoryUseCase blogPostCategoryUseCase;

    public BlogPostFactory(GetBlogAuthorUseCase blogAuthorUseCase, GetBlogPostCategoryUseCase blogPostCategoryUseCase) {
        this.blogAuthorUseCase = blogAuthorUseCase;
        this.blogPostCategoryUseCase = blogPostCategoryUseCase;
    }

    BlogPost createFromDTO(BlogPostCreateDTO blogPostCreateDTO) {
        BlogPost blogPost = new BlogPost();
        blogPost.setId(0);
        blogPost.setLikeItCounter(0);
        blogPost.setTitle(blogPostCreateDTO.getTitle());
        blogPost.setContents(blogPostCreateDTO.getContents());
        blogPost.setAuthor(blogAuthorUseCase.getById(blogPostCreateDTO.getAuthorId()).orElse(null));
        blogPost.setPostCategory(blogPostCategoryUseCase.getById(blogPostCreateDTO.getPostCategoryId()).orElse(null));
        return blogPost;
    }
}
