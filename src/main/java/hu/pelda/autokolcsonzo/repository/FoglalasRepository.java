package hu.pelda.autokolcsonzo.repository;

import hu.pelda.autokolcsonzo.model.Foglalas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FoglalasRepository extends JpaRepository<Foglalas,Long> {

    @Query("""
        SELECT f FROM Foglalas f
        WHERE f.auto.id = :autoId
        AND f.datumtol <= :datumig
        AND f.datumig >= :datumtol
    """)
    List<Foglalas> findConflictingFoglalas(
            @Param("autoId") Long autoId,
            @Param("datumtol")LocalDate datumtol,
            @Param("datumig") LocalDate datumig
            );
}
