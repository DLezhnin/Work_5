package inno.lab5.repository;

import inno.lab5.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseRegisterRepository extends JpaRepository<Register, Long> {
}
