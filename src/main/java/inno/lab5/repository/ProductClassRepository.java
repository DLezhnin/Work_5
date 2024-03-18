package inno.lab5.repository;

import inno.lab5.model.ProductClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductClassRepository extends JpaRepository<ProductClass, Long> {
Optional<ProductClass> findByValue(String value);
}
