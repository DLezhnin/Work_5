package inno.lab5.repository;

import inno.lab5.model.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementRepository extends JpaRepository<Agreement,Long> {
}
