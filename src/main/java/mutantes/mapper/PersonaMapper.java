package mutantes.mapper;

import mutantes.dto.PersonaDTO;
import mutantes.entidades.Persona;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    PersonaDTO personaToPersonaDTO(Persona persona);
}
