package mutantes.controladores;

import mutantes.entidades.Persona;
import mutantes.servicios.PersonaService;
import mutantes.servicios.PersonaServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mutant")

public class PersonaController {
    @Autowired
    PersonaService personaService;
    @Autowired
    PersonaServiceDTO personaServiceDTO;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(personaService.findAll());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(personaService.findById(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }
    
    @GetMapping("/DTO/{id}")
    public ResponseEntity<?> getOneDTO(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(personaServiceDTO.getShortData(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Persona persona){
        try{
            String[] dna = persona.getAdn();
            boolean mutante = persona.isMutant(dna);
            if (mutante){
                return ResponseEntity.status(HttpStatus.OK).body(personaService.save(persona));
            }else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(personaService.save(persona));
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Persona entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(personaService.update(id, entity));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(personaService.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }
}
