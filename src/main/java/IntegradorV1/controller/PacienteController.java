package IntegradorV1.controller;

import IntegradorV1.entity.Paciente;
import IntegradorV1.exception.BadRequestException;
import IntegradorV1.exception.ResourceNotFoundException;
import IntegradorV1.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin("*")
public class PacienteController {

    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Paciente> optionalPaciente = pacienteService.buscarPaciente(id);
        if (optionalPaciente.isPresent()){
            return ResponseEntity.ok(optionalPaciente.get());
        } else {
            throw new ResourceNotFoundException("No se pudo encontrar el paciente");
        }
    }

    @GetMapping("/mail/{mail}")
    public ResponseEntity<Paciente> buscarPacientePorMail(@PathVariable String mail) throws ResourceNotFoundException {
        Optional<Paciente> optionalPaciente = pacienteService.buscarPacienteXEmail(mail);
        if (optionalPaciente.isPresent()){
            return ResponseEntity.ok(optionalPaciente.get());
        } else {
            throw new ResourceNotFoundException("No se pudo encontrar el paciente");
        }
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodosPacientes(){
        return ResponseEntity.ok(pacienteService.buscarTodosPacientes());
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        if (pacienteService.buscarPacienteXEmail(paciente.getEmail()).isPresent()){
            throw  new BadRequestException("El email ya esta en uso");
        } else {
            return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> optionalPaciente = pacienteService.buscarPaciente(paciente.getId());
        ResponseEntity<String> responseEntity;
        if (optionalPaciente.isPresent()){
            pacienteService.guardarPaciente(paciente);
            return ResponseEntity.ok("Se actualizo el paciente con id "+paciente.getId());
        } else {
            throw new ResourceNotFoundException("No se pudo actualizar el paciente con id "+paciente.getId()+" porque no existe");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarOdontologo(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteAEliminar = pacienteService.buscarPaciente(id);
        if (pacienteAEliminar.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Se elimino el paciente con id: "+id);
        } else {
            throw new ResourceNotFoundException("No se pudo borrar el paciente con id "+id+" porque no se encontro en la base de datos");
        }
    }
}
