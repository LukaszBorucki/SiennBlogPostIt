package co.borucki.SiennBlogPostIt.repositories;

import co.borucki.SiennBlogPostIt.domain.BlogAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogAuthorRepository extends JpaRepository<BlogAuthor, Long>, JpaSpecificationExecutor<BlogAuthor> {
    Optional<BlogAuthor> findById(long id);
}
