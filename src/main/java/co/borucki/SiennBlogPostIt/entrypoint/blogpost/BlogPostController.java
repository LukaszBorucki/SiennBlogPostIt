package co.borucki.SiennBlogPostIt.entrypoint.blogpost;

import co.borucki.SiennBlogPostIt.domain.BlogPost;
import co.borucki.SiennBlogPostIt.entrypoint.blogbestauthor.BlogBestAuthorByCategoryDTO;
import co.borucki.SiennBlogPostIt.entrypoint.blogbestauthor.BlogBestAuthorByCategoryMapper;
import co.borucki.SiennBlogPostIt.usecase.manageblogpost.CreateBlogPostUseCase;
import co.borucki.SiennBlogPostIt.usecase.manageblogpost.GetBlogPostUseCase;
import co.borucki.SiennBlogPostIt.usecase.manageblogpost.UpdateBlogPostUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(BlogPostMapper.toDTO(getBlogPostUseCase.getAll()));
    }

    @ApiOperation(value = "Update likes field in BlogPost")
    @PutMapping(value = "/likeIt/{blogPostId}")
    public ResponseEntity<BlogPostDTO> likeIt(@ApiParam(value = "blogPostId", required = true) @PathVariable long blogPostId) {
        BlogPost blogPost = updateBlogPostUseCase.updateLike(blogPostId);
        return ResponseEntity.ok(BlogPostMapper.toDTO(blogPost));
    }

    @ApiOperation(value = "Create new Blog Post")
    @PostMapping(value = "/createPost")
    public ResponseEntity<BlogPostDTO> createPost(@RequestBody BlogPostCreateDTO postCreateDTO) {

        System.out.println(createBlogPostUseCase.create(blogPostFactory.createFromDTO(postCreateDTO)).getId());
        return ResponseEntity.
                ok(BlogPostMapper.toDTO(createBlogPostUseCase.create(blogPostFactory.createFromDTO(postCreateDTO))));
    }

    @ApiOperation(value = "Get BlogPost headers")
    @GetMapping(value = "/getHeaders")
    public ResponseEntity<List<BlogHeaderDTO>> getAllHeaders() {
        return ResponseEntity.ok(BlogHeaderMapper.toHeaderDTO(getBlogPostUseCase.getAll()));
    }


    @ApiOperation(value = "Show the best rated Author in each category")
    @GetMapping(value = "/bestRatedAuthorByCategory")
    public ResponseEntity<List<BlogBestAuthorByCategoryDTO>> getBestRatedAuthorByCategory() {

        return ResponseEntity
                .ok(BlogBestAuthorByCategoryMapper.
                        toDTO(getBlogPostUseCase.
                                getBestAuthorsByCategory()));
    }


}
