package es.cipfpbatoi.damb.UD02A03.persistencia.rest;

import es.cipfpbatoi.damb.UD02A03.persistencia.model.Barco;
import es.cipfpbatoi.damb.UD02A03.persistencia.repository.barcos.BarcosJDBCTemplateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("barcos")
public class BarcosController {
    @Autowired
    BarcosJDBCTemplateRepository barcosJDBCTemplateRepository;

    @GetMapping("/count")
    public Integer count(){
        return this.barcosJDBCTemplateRepository.count();
    }

    @GetMapping("")
    public List<Barco> findAll(){
        return  this.barcosJDBCTemplateRepository.findAll();
    }

    @GetMapping("/{id}")
    public Barco findById(@PathVariable int id,
                          @RequestParam(required = false, defaultValue = "false")
                          Boolean withTripulacion) {
        if (withTripulacion) 
            return this.barcosJDBCTemplateRepository.findByIdWithTripulacion(id);
        
        return this.barcosJDBCTemplateRepository.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Barco create(@RequestBody Barco barco){
        boolean insertado = this.barcosJDBCTemplateRepository.insert(barco);
        if (!insertado){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Entity not created.");
        }
        return barco;
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity updated")
    public boolean update(@PathVariable int id, @RequestBody Barco barco){
        if (id != barco.getId()){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Error in query.");
        }
        boolean borrado = this.barcosJDBCTemplateRepository.update(barco);
        if (!borrado){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Entity not updated.");
        }
        return true;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity deleted")
    public boolean delete(@PathVariable int id, @RequestBody Barco barco){
        if (id != barco.getId()){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Error in query.");
        }
        boolean borrado = this.barcosJDBCTemplateRepository.delete(barco);
        if (!borrado){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Entity not updated.");
        }
        return true;
    }
}
