package hu.pelda.autokolcsonzo.repository;

import hu.pelda.autokolcsonzo.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Auto, Long> {
}
