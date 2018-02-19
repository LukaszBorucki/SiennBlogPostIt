package co.borucki.SiennBlogPostIt.repositories;

import co.borucki.SiennBlogPostIt.domain.BlogPostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogPostCategoryRepository extends JpaRepository<BlogPostCategory, Integer> {
    Optional<BlogPostCategory> getById(int id);
}
