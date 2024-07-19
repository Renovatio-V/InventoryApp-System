package renovatio.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import renovatio.inventory.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
