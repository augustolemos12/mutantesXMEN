package mutantes.entidades;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class EntidadBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
