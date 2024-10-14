package mutantes.repositorios;

import mutantes.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Long> {
    @Query(value = "SELECT COUNT(p) FROM Persona p WHERE p.mutant=true")
    long countMutants();

    @Query(value = "SELECT COUNT(p) FROM Persona p WHERE p.mutant=false")
    long countHumans();
}
