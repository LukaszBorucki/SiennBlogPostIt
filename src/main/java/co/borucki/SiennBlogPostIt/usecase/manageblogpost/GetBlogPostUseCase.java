package co.borucki.SiennBlogPostIt.usecase.manageblogpost;

import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.domain.BlogPostHeader;
import co.borucki.SiennBlogPostIt.entrypoint.blogpost.BlogPostHeaderFactory;
import co.borucki.SiennBlogPostIt.repositories.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GetBlogPostUseCase {
    private final BlogPostRepository blogPostRepository;
    private final BlogPostHeaderFactory blogPostHeaderFactory;

    public GetBlogPostUseCase(BlogPostRepository blogPostRepository, BlogPostHeaderFactory blogPostHeaderFactory) {
        this.blogPostRepository = blogPostRepository;
        this.blogPostHeaderFactory = blogPostHeaderFactory;
    }

    public List<BlogPost> getAll() {
        return blogPostRepository.findAll();
    }

    public Optional<BlogPost> getById(long id) {
        return blogPostRepository.getById(id);
    }

    public List<BlogPost> getBestAuthorsByCategory() {
        return findBestAuthorPerCategory(createCategoryNameByBlogPostList());
    }

    private List<BlogPost> findBestAuthorPerCategory(Map<String, List<BlogPost>> blogPostGroupedByCategory) {
        List<BlogPost> bestRatedAuthorByCategoryList = new ArrayList<>();
        blogPostGroupedByCategory.entrySet()
                .forEach(entry -> bestRatedAuthorByCategoryList
                        .add(entry
                                .getValue()
                                .stream()
                                .max(Comparator.comparing(BlogPost::getLikeItCounter))
                                .orElseThrow(NoSuchElementException::new)));
        return bestRatedAuthorByCategoryList;
    }

    private Map<String, List<BlogPost>> createCategoryNameByBlogPostList() {
        return getAll().
                stream().
                collect(Collectors.groupingBy(w -> w.getPostCategory().getName()));
    }

    public List<BlogPostHeader> getHeaders() {
        return blogPostHeaderFactory.createFromBlogPost(getAll());
    }
}
