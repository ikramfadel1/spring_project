package projet.blog.repositories;

import projet.blog.entities.Post1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<Post1, Long>  {
}
