package co.borucki.SiennBlogPostIt.repositories;

import co.borucki.SiennBlogPostIt.domain.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogPostRepository extends CrudRepository<BlogPost, Long>, JpaRepository<BlogPost, Long>, JpaSpecificationExecutor<BlogPost> {
    List<BlogPost> findAll();

    Optional<BlogPost> getById(long id);

}
