package com.example.demo.controllers;

import com.example.demo.models.Persona;
import com.example.demo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private Repository repo;

    @GetMapping()
    public String index(){
        return "CONECTADO";
    }

    @GetMapping("personas")
    public List<Persona> getPersonas(){
        return repo.findAll();
    }

    @PostMapping("grabar")
    public String post(@RequestBody Persona persona){
        repo.save(persona);
        return "Grabado";
    }

    @PutMapping("editar/{id}")
    public String update(@PathVariable Long id, @RequestBody Persona persona){
        Persona updatedPersona = repo.findById(id).get();
        updatedPersona.setNombre(persona.getNombre());
        updatedPersona.setTelefono(persona.getTelefono());
        repo.save(updatedPersona);
        return "Editado";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id){
        Persona deletePersona = repo.findById(id).get();
        repo.delete(deletePersona);
        return "Eliminado";
    }

}
