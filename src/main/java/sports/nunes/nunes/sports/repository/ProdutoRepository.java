package sports.nunes.nunes.sports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sports.nunes.nunes.sports.entities.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
