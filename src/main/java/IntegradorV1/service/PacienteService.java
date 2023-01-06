package IntegradorV1.service;


import IntegradorV1.entity.Paciente;
import IntegradorV1.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService{

    private PacienteRepository pacienteRepository;

    private Logger LOGGER = Logger.getLogger(OdontologoService.class);

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardarPaciente(Paciente paciente){
        LOGGER.info("Se guardo un paciente con nombre completo"+paciente.getNombre()+" "+paciente.getApellido());
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPaciente(Integer id){
        LOGGER.info("Se busco el paciente con id: "+id);
        return  pacienteRepository.findById(id);
    }

    public void eliminarPaciente(Integer id){
        LOGGER.warn("Se elimino el paciente con id: "+id);
        pacienteRepository.deleteById(id);
    }

    public List<Paciente> buscarTodosPacientes(){
        LOGGER.info("Se listaron todos los pacientes");
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPacienteXEmail(String email){
        LOGGER.info("Se busco el paciente con el email: "+ email);
        return pacienteRepository.findByEmail(email);
    }
}
