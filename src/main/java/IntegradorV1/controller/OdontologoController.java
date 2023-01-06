package IntegradorV1.controller;


import IntegradorV1.entity.Odontologo;
import IntegradorV1.exception.ResourceNotFoundException;
import IntegradorV1.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/odontologos")
@CrossOrigin("*")
public class OdontologoController {

    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> traerOdontologo(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> optionalOdontologo = odontologoService.buscarOdontologo(id);
        ResponseEntity response;
        if (optionalOdontologo.isPresent()){
            response = new ResponseEntity<>(optionalOdontologo.get(), HttpStatus.ACCEPTED);
        } else {
            throw new ResourceNotFoundException("No se pudo encontrar el odontologo");
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodosPacientes(){
        return ResponseEntity.ok(odontologoService.listarTodos());
    }


    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> optionalOdontologo = odontologoService.buscarOdontologo(odontologo.getId());
        ResponseEntity<String> responseEntity;
        if (optionalOdontologo.isPresent()){
            odontologoService.guardar(odontologo);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body("Se actualizo el odontolgo con id: "+odontologo.getId());
        }else {
            throw new ResourceNotFoundException("No se pudo actualizar el odontologo con id "+odontologo.getId()+" porque no existe");
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarOdontologo(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontoAEliminar = odontologoService.buscarOdontologo(id);
        ResponseEntity<String> responseEntity;
        if (odontoAEliminar.isPresent()){
            odontologoService.eliminar(id);
            responseEntity = ResponseEntity.ok("Se elimino el odontologo con id: "+id);
        } else {
            throw new ResourceNotFoundException("No se pudo borrar el odontologo con id "+id+" porque no se encontro en la base de datos");
        }
        return responseEntity;
    }


}
