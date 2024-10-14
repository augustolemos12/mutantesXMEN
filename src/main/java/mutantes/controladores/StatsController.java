package mutantes.controladores;

import mutantes.servicios.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/stats")

public class StatsController{
    @Autowired
    PersonaService personaService;

    @GetMapping("")
    public ResponseEntity<?> contar(){
        try{
            long contadorMutantes = personaService.countMutants();
            long contadorHumanos = personaService.countHumans();
            double ratio = contadorMutantes/contadorHumanos;
            return ResponseEntity.status(HttpStatus.OK).body("{count_mutant_dna:"+contadorMutantes+", count_human_dna:"+contadorHumanos+", ratio:"+ratio+"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");

        }
    }
}
