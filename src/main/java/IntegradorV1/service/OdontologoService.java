package IntegradorV1.service;


import IntegradorV1.entity.Odontologo;
import IntegradorV1.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private OdontologoRepository odontologoRepository;

    private Logger LOGGER = Logger.getLogger(OdontologoService.class);

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo guardar(Odontologo odontologo){
        LOGGER.info("Se guardo un odontologo con matricula: "+odontologo.getNroMatricula());
        return odontologoRepository.save(odontologo);
    }

    public List<Odontologo> listarTodos(){
        LOGGER.info("Se listaron todos los odontologos");
        return odontologoRepository.findAll();
    }

    public Optional<Odontologo> buscarOdontologo(Integer id){
        LOGGER.info("Se busco el odontologo de id: "+id);
        return odontologoRepository.findById(id);
    }
    public void eliminar(Integer id){
        LOGGER.warn("Se elimino el odontologo de id: "+id);
        odontologoRepository.deleteById(id);
    }

}
