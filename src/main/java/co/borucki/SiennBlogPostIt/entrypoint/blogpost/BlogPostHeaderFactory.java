package co.borucki.SiennBlogPostIt.entrypoint.blogpost;

import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.domain.BlogPostHeader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogPostHeaderFactory {
    public BlogPostHeader createFromBlogPost(BlogPost blogPost) {
        BlogPostHeader blogPostHeader = new BlogPostHeader();
        blogPostHeader.setId(blogPost.getId());
        blogPostHeader.
                setContents(blogPost.
                        getContents().
                        substring(0, blogPost.getContents().length() < 30 ? blogPost.getContents().length() : 30));
        blogPostHeader.setTitle(blogPost.getTitle());
        return blogPostHeader;
    }

    public List<BlogPostHeader> createFromBlogPost(List<BlogPost> blogPostList) {
        List<BlogPostHeader> blogPostHeaders = new ArrayList<>();
        blogPostList.forEach(entry -> blogPostHeaders.add(createFromBlogPost(entry)));
        return blogPostHeaders;
    }
}
