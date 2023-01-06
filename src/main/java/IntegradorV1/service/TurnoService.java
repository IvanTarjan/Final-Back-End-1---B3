package IntegradorV1.service;


import IntegradorV1.entity.Turno;
import IntegradorV1.repository.OdontologoRepository;
import IntegradorV1.repository.PacienteRepository;
import IntegradorV1.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private TurnoRepository turnoRepository;
    private OdontologoRepository odontologoRepository;
    private PacienteRepository pacienteRepository;

    private Logger LOGGER = Logger.getLogger(OdontologoService.class);

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, OdontologoRepository odontologoRepository, PacienteRepository pacienteRepository){
        this.turnoRepository = turnoRepository;
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;

    }

    public Turno guardarTurno (Turno turno){
        LOGGER.info("Se guardo un tuno para el dia "+turno.getFecha());
        return  turnoRepository.save(turno);
    }

    public Optional<Turno> buscarTurno (Integer id ){
        LOGGER.info("Se busco un turno con id: "+id);
        return  turnoRepository.findById(id);
    }

    public void eliminarTurno(Integer id){
        turnoRepository.deleteById(id);
        LOGGER.warn("Se elimino un turno con id: "+id);
    }

    public List<Turno> buscarTodosTurnos(){
        LOGGER.info("Se listaron todos los turnos");
        return turnoRepository.findAll();
    }

    public Optional<List<Turno>> buscarTurnoPorOdontologo(Integer odontologo_id){
        LOGGER.info("Se buscaron los turnos con el odontologo con id: "+ odontologo_id);
        return turnoRepository.findAllByOdontologo_id(odontologo_id);
    }

    public Optional<List<Turno>> buscarTurnoPorPaciente(Integer paciente_id){
        LOGGER.info("Se buscaron los turnos con el paciente con id: "+ paciente_id);
        return turnoRepository.findAllByPaciente_id(paciente_id);
    }

}
