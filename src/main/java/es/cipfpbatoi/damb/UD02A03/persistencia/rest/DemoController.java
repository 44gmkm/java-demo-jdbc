package es.cipfpbatoi.damb.UD02A03.persistencia.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import es.cipfpbatoi.damb.UD02A03.persistencia.model.Demo;
import es.cipfpbatoi.damb.UD02A03.persistencia.repository.demo.DemoJDBCTemplateRepository;

@RestController
@RequestMapping("demo")
public class DemoController {
    @Autowired
    DemoJDBCTemplateRepository demoJDBCTemplateRepository;

    @GetMapping("/count")
    public Integer count(){
        return this.demoJDBCTemplateRepository.count();
    }

    @GetMapping("")
    public List<Demo> findAll(){
        return  this.demoJDBCTemplateRepository.findAll();
    }

    @GetMapping("/{id}")
    public Demo findById(@PathVariable int id) {
        return this.demoJDBCTemplateRepository.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Demo create(@RequestBody Demo demo){
        boolean insertado = this.demoJDBCTemplateRepository.insert(demo);
        if (!insertado){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Entity not created.");
        }
        return demo;
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity updated")
    public boolean update(@PathVariable int id, @RequestBody Demo demo){
        if (id != demo.getId()){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Error in query.");
        }
        boolean borrado = this.demoJDBCTemplateRepository.update(demo);
        if (!borrado){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Entity not updated.");
        }
        return true;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Entity deleted")
    public boolean delete(@PathVariable int id, @RequestBody Demo demo){
        if (id != demo.getId()){
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                    "Error in query.");
        }
        boolean borrado = this.demoJDBCTemplateRepository.delete(demo);
        if (!borrado){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Entity not updated.");
        }
        return true;
    }
}
