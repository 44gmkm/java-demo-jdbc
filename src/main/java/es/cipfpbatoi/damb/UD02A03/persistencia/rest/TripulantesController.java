package es.cipfpbatoi.damb.UD02A03.persistencia.rest;

import es.cipfpbatoi.damb.UD02A03.persistencia.model.Tripulante;
import es.cipfpbatoi.damb.UD02A03.persistencia.repository.tripulantes.TripulantesJdbcTemplateRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("tripulantes")
public class TripulantesController {
    @Autowired
    TripulantesJdbcTemplateRepository tripulantesJDBCTemplateRepository;

    @GetMapping("/count")
    public Integer count(){
        return this.tripulantesJDBCTemplateRepository.count();
    }

    @GetMapping("")
    public List<Tripulante> findAll(){
        return  this.tripulantesJDBCTemplateRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tripulante findById(@PathVariable int id) {
        return this.tripulantesJDBCTemplateRepository.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Tripulante create(@RequestBody Tripulante tripulante){
        boolean insertado = this.tripulantesJDBCTemplateRepository.insert(tripulante);
        if (!insertado){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Entity not created.");
        }
        return tripulante;
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity updated")
    public boolean update(@PathVariable int id, @RequestBody Tripulante tripulante){
        if (id != tripulante.getId()){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Error in query.");
        }
        boolean borrado = this.tripulantesJDBCTemplateRepository.update(tripulante);
        if (!borrado){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Entity not updated.");
        }
        return true;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity deleted")
    public boolean delete(@PathVariable int id, @RequestBody Tripulante tripulante){
        if (id != tripulante.getId()){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Error in query.");
        }
        boolean borrado = this.tripulantesJDBCTemplateRepository.delete(tripulante);
        if (!borrado){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Entity not updated.");
        }
        return true;
    }
}
