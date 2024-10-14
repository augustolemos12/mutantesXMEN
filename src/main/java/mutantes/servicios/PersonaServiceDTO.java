package mutantes.servicios;

import mutantes.dto.PersonaDTO;
import mutantes.entidades.Persona;
import mutantes.mapper.PersonaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonaServiceDTO {
    @Autowired
    PersonaService personaService;
    @Autowired
    PersonaMapper personaMapper;

    public PersonaDTO getShortData(Long id) throws Exception {
        //Obtenemos la persona
        Persona persona = personaService.findById(id);
        return personaMapper.personaToPersonaDTO(persona);
    }
}
