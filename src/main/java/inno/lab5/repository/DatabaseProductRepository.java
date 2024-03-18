package inno.lab5.repository;

import inno.lab5.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseProductRepository extends JpaRepository<Product,Long> {

}
