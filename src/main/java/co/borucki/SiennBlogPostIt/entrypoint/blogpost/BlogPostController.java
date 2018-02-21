package co.borucki.SiennBlogPostIt.entrypoint.blogpost;

import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.entrypoint.blogbestauthor.BlogBestAuthorByCategoryDTO;
import co.borucki.SiennBlogPostIt.usecase.manageblogpost.CreateBlogPostUseCase;
import co.borucki.SiennBlogPostIt.usecase.manageblogpost.GetBlogPostUseCase;
import co.borucki.SiennBlogPostIt.usecase.manageblogpost.UpdateBlogPostUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/SiennPostIt/blog")
public class BlogPostController {
    private final GetBlogPostUseCase getBlogPostUseCase;
    private UpdateBlogPostUseCase updateBlogPostUseCase;
    private CreateBlogPostUseCase createBlogPostUseCase;
    private final BlogPostFactory blogPostFactory;

    @Qualifier("actionBeanMapper")
    @Autowired
    private MapperFacade mapper;

    @Autowired
    public BlogPostController(GetBlogPostUseCase getBlogPostUseCase,
                              UpdateBlogPostUseCase updateBlogPostUseCase,
                              CreateBlogPostUseCase createBlogPostUseCase,
                              BlogPostFactory blogPostFactory) {
        this.getBlogPostUseCase = getBlogPostUseCase;
        this.updateBlogPostUseCase = updateBlogPostUseCase;
        this.createBlogPostUseCase = createBlogPostUseCase;
        this.blogPostFactory = blogPostFactory;
    }

    @ApiOperation(value = "Get all post")
    @GetMapping(value = "/all")
    public ResponseEntity<List<BlogPostDTO>> getAll() {
        return ResponseEntity.ok(
                mapper.mapAsList(getBlogPostUseCase.getAll(), BlogPostDTO.class));
    }

    @ApiOperation(value = "Update likes field in BlogPost")
    @PutMapping(value = "/likeIt/{blogPostId}")
    public ResponseEntity<BlogPostDTO> likeIt(@ApiParam(value = "blogPostId", required = true) @PathVariable long blogPostId) {
        BlogPost blogPost = updateBlogPostUseCase.updateLike(blogPostId);
        return ResponseEntity.ok(
                mapper.map(blogPost, BlogPostDTO.class));
    }

    @ApiOperation(value = "Create new Blog Post")
    @PostMapping(value = "/createPost")
    public ResponseEntity<BlogPostDTO> createPost(@RequestBody BlogPostCreateDTO postCreateDTO) {
        return ResponseEntity.
                ok(mapper.map(createBlogPostUseCase.create(blogPostFactory.createFromDTO(postCreateDTO)), BlogPostDTO.class));
    }

    @ApiOperation(value = "Get BlogPost headers")
    @GetMapping(value = "/getHeaders")
    public ResponseEntity<List<BlogHeaderDTO>> getAllHeaders() {
        return ResponseEntity.ok(mapper.mapAsList(getBlogPostUseCase.getHeaders(), BlogHeaderDTO.class));
    }

    @ApiOperation(value = "Show the best rated Author in each category")
    @GetMapping(value = "/bestRatedAuthorByCategory")
    public ResponseEntity<List<BlogBestAuthorByCategoryDTO>> getBestRatedAuthorByCategory() {

        return ResponseEntity
                .ok(mapper.mapAsList(getBlogPostUseCase.
                        getBestAuthorsByCategory(), BlogBestAuthorByCategoryDTO.class));
    }
}
