package mutantes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonaDTO {
    private String nombre;
    private String apellido;
    private boolean mutant;
}
