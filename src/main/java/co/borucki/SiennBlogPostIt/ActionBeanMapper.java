package co.borucki.SiennBlogPostIt;

import co.borucki.SiennBlogPostIt.domain.*;
import co.borucki.SiennBlogPostIt.entrypoint.blogauthor.AuthorDTO;
import co.borucki.SiennBlogPostIt.entrypoint.blogbestauthor.BlogBestAuthorByCategoryDTO;
import co.borucki.SiennBlogPostIt.entrypoint.blogpost.BlogHeaderDTO;
import co.borucki.SiennBlogPostIt.entrypoint.blogpost.BlogPostDTO;
import co.borucki.SiennBlogPostIt.entrypoint.blogpostcategory.PostCategoryDTO;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class ActionBeanMapper extends ConfigurableMapper {

    @Override
    public void configureFactoryBuilder(DefaultMapperFactory.Builder builder) {
        builder.build();
    }

    @Override
    protected final void configure(final MapperFactory factory) {
        factory
                .classMap(BlogAuthor.class, AuthorDTO.class)
                .byDefault()
                .register();
        factory
                .classMap(BlogPost.class, BlogPostDTO.class)
                .byDefault()
                .register();

        factory
                .classMap(BlogPostCategory.class, PostCategoryDTO.class)
                .byDefault()
                .register();

        factory
                .classMap(BlogPostHeader.class, BlogHeaderDTO.class)
                .byDefault()
                .register();
        factory
                .classMap(BlogBestAuthorByCategory.class, BlogBestAuthorByCategoryDTO.class)
                .byDefault()
                .register();
    }
}
