package mutantes.servicios;

import jakarta.transaction.Transactional;
import mutantes.entidades.Persona;
import mutantes.repositorios.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PersonaService implements BaseService<Persona> {
    @Autowired
    PersonaRepository personaRepository;

    @Override
    @Transactional
    public List<Persona> findAll() throws Exception {
        try {
            List<Persona> personas = personaRepository.findAll();
            return personas;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona findById(Long id) throws Exception {
        try {
            Optional<Persona> personaOptional = personaRepository.findById(id);
            return personaOptional.get();
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona save(Persona persona) throws Exception {
        try{
            persona = personaRepository.save(persona);
            return persona;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Persona update(Long id, Persona entity) throws Exception {
        try {
            Optional<Persona> personaOptional = personaRepository.findById(id);
            Persona persona = personaOptional.get();
            persona = personaRepository.save(persona);
            return persona;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try {
            if (personaRepository.existsById(id)){
                personaRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Métodos para contar. No implementados en BaseService porque son específicos de Persona.
    @Transactional
    public long countMutants() throws Exception{
        try{
            long cantMutantes = personaRepository.countMutants();
            return cantMutantes;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public long countHumans() throws Exception{
        try {
            long cantHumanos = personaRepository.countHumans();
            return cantHumanos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
