package IntegradorV1.controller;

import IntegradorV1.dto.TurnoDto;
import IntegradorV1.entity.Odontologo;
import IntegradorV1.entity.Paciente;
import IntegradorV1.entity.Turno;
import IntegradorV1.exception.BadRequestException;
import IntegradorV1.exception.ResourceNotFoundException;
import IntegradorV1.service.OdontologoService;
import IntegradorV1.service.PacienteService;
import IntegradorV1.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/turnos")
@CrossOrigin("*")
public class TurnoController {

    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;



    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService){
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<TurnoDto>> listarLosTurnos(){
        return ResponseEntity.ok(turnoListToDto(turnoService.buscarTodosTurnos()));
    }

    @PostMapping
    public ResponseEntity<TurnoDto> registrarTurno(@RequestBody TurnoDto turnoDto) throws BadRequestException {

        ResponseEntity<TurnoDto> respuesta;
        Optional<Paciente> pacienteTemp= pacienteService.buscarPaciente(turnoDto.getPaciente_id());
        Optional<Odontologo> odontologoTemp = odontologoService.buscarOdontologo(turnoDto.getOdontologo_id());
        if (pacienteTemp.isPresent()&& odontologoTemp.isPresent()){
            Turno turno = toTurno(turnoDto, pacienteTemp.get(), odontologoTemp.get());
            turnoService.guardarTurno(turno);
            respuesta = ResponseEntity.ok(turno.toDto());
        } else {
            throw new BadRequestException("No se pudo insertar el turno. Verifique que el odontologo y el paciente esten registrados.");
        }
        return respuesta;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarTurno(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Turno> turnoAEliminar = turnoService.buscarTurno(id);
        ResponseEntity<String> responseEntity;
        if (turnoAEliminar.isPresent()){
            turnoService.eliminarTurno(id);
            responseEntity = ResponseEntity.ok("Se elimino el turno con id: "+id);
        } else {
            throw new ResourceNotFoundException("No se pudo borrar el turno con id "+id+" porque no se encontro en la base de datos");
        }
        return responseEntity;
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDto turnoDto) throws ResourceNotFoundException {
        Optional<Turno> optionalTurno = turnoService.buscarTurno(turnoDto.getId());
        if (optionalTurno.isPresent()){
            Optional<Paciente> pacienteTemp= pacienteService.buscarPaciente(turnoDto.getPaciente_id());
            Optional<Odontologo> odontologoTemp = odontologoService.buscarOdontologo(turnoDto.getOdontologo_id());
            if (pacienteTemp.isPresent()&& odontologoTemp.isPresent()){
                Turno turno = toTurnoConId(turnoDto, pacienteTemp.get(), odontologoTemp.get());
                turnoService.guardarTurno(turno);
                return ResponseEntity.status(HttpStatus.OK).body("Se actualizo el turno con id: "+turno.getId());
            } else {
                throw new ResourceNotFoundException("No se pudo modificar el turno. Verifique que el odontologo y el paciente esten registrados.");
            }
        } else {
            throw new ResourceNotFoundException("No se pudo actualizar el turno con id " + turnoDto.getId() + " porque no existe");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Turno> optionalTurno = turnoService.buscarTurno(id);
        if (optionalTurno.isPresent()){
            return ResponseEntity.ok(optionalTurno.get().toDto());
        } else {
            throw new ResourceNotFoundException("No se pudo encontrar el turno");
        }

    }

    @GetMapping("/byodo/{id}")
    public ResponseEntity<List<TurnoDto>> buscarTurnoPorOdontologo(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<List<Turno>> optionalTurnoList = turnoService.buscarTurnoPorOdontologo(id);
        if (optionalTurnoList.isPresent()){
            return ResponseEntity.ok(turnoListToDto(optionalTurnoList.get()));
        } else {
            throw new ResourceNotFoundException("No se pudieron encontrar los turnos del odontologo con id "+id);
        }
    }

    @GetMapping("/bypac/{id}")
    public ResponseEntity<List<TurnoDto>> buscarTurnoPorPaciente(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<List<Turno>> optionalTurnoList = turnoService.buscarTurnoPorPaciente(id);
        if (optionalTurnoList.isPresent()){
            return ResponseEntity.ok(turnoListToDto(optionalTurnoList.get()));
        } else {
            throw new ResourceNotFoundException("No se pudieron encontrar los turnos del paciente con id "+id);
        }

    }

    public Turno toTurno(TurnoDto turnoDto, Paciente paciente, Odontologo odontologo){
        return new Turno(paciente, odontologo, turnoDto.getFecha());
    }
    public Turno toTurnoConId(TurnoDto turnoDto, Paciente paciente, Odontologo odontologo){
        return new Turno(turnoDto.getId(),paciente, odontologo, turnoDto.getFecha());
    }

    public List<TurnoDto> turnoListToDto(List<Turno> turnoList){
        List<TurnoDto> turnoDtoList = new ArrayList<>();
        for (Turno turno : turnoList) {
            turnoDtoList.add(turno.toDto());
        }
        return turnoDtoList;
    }
}
