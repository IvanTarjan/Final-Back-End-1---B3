package IntegradorV1.repository;

import IntegradorV1.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TurnoRepository extends JpaRepository<Turno, Integer> {

    Optional<List<Turno>> findAllByOdontologo_id(Integer odontologo_id);
    Optional<List<Turno>> findAllByPaciente_id(Integer paciente_id);

    @Override
    void deleteById(Integer integer);
}
