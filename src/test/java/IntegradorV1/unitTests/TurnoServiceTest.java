package IntegradorV1.unitTests;

import IntegradorV1.entity.Domicilio;
import IntegradorV1.entity.Odontologo;
import IntegradorV1.entity.Paciente;
import IntegradorV1.entity.Turno;
import IntegradorV1.service.OdontologoService;
import IntegradorV1.service.PacienteService;
import IntegradorV1.service.TurnoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {

    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    @Autowired
    public TurnoServiceTest(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Test
    @Order(1)
    public void turnoInsertTest(){
        Paciente pacienteTemp = new Paciente("Ivan","Tarjan", "45072214", LocalDate.of(2022, 11,22), new Domicilio("Calle1", 403,"Tigre", "Buenos Aires"), "ivan@gmail.com");
        Odontologo odontologoTemp = new Odontologo("4352345", "Ivan", "Tarjan");
        pacienteService.guardarPaciente(pacienteTemp);
        odontologoService.guardar(odontologoTemp);
        Turno turno = new Turno(pacienteTemp, odontologoTemp, LocalDate.of(2022, 12, 1));
        Turno turnoExpec = new Turno(1, pacienteTemp, odontologoTemp, LocalDate.of(2022, 12, 1));
        Turno turnoRes = turnoService.guardarTurno(turno);
        assertEquals(turnoExpec, turnoRes);
    }

    @Test
    @Order(2)
    public void turnoGetByIdTest(){
        Paciente pacienteTemp = new Paciente(1,"Ivan","Tarjan", "45072214", LocalDate.of(2022, 11,22), new Domicilio(1,"Calle1", 403,"Tigre", "Buenos Aires"), "ivan@gmail.com");
        Odontologo odontologoTemp = new Odontologo(1,"4352345", "Ivan", "Tarjan");
        Turno turnoExpec = new Turno(1, pacienteTemp, odontologoTemp, LocalDate.of(2022, 12, 1));
        Integer idABuscar = 1;
        Turno turnoRes = turnoService.buscarTurno(idABuscar).get();
        assertEquals(turnoExpec, turnoRes);

    }

    @Test
    @Order(3)
    public void turnoGetAllTest(){
        Paciente pacienteTemp = new Paciente(1,"Ivan","Tarjan", "45072214", LocalDate.of(2022, 11,22), new Domicilio(1,"Calle1", 403,"Tigre", "Buenos Aires"), "ivan@gmail.com");
        Odontologo odontologoTemp = new Odontologo(1,"4352345", "Ivan", "Tarjan");
        Turno turnoExpec = new Turno(1,pacienteTemp, odontologoTemp, LocalDate.of(2022, 12, 1));
        List<Turno> turnoListExpec = new ArrayList<>();
        turnoListExpec.add(turnoExpec);
        List<Turno> turnoListRes = turnoService.buscarTodosTurnos();
        assertEquals(turnoListExpec, turnoListRes);
    }

    @Test
    @Order(4)
    public void turnoModifyTest(){
        Paciente pacienteTemp = new Paciente(1,"Ivan","Tarjan", "45072214", LocalDate.of(2022, 11,22), new Domicilio(1,"Calle1", 403,"Tigre", "Buenos Aires"), "ivan@gmail.com");
        Odontologo odontologoTemp = new Odontologo(1,"4352345", "Ivan", "Tarjan");
        Turno turnoExpec = new Turno(1,pacienteTemp, odontologoTemp, LocalDate.of(3033, 2, 6));
        turnoService.guardarTurno(turnoExpec);
        Turno turnoRes = turnoService.buscarTurno(1).get();
        assertEquals(turnoExpec, turnoRes);
    }
    @Test
    @Order(5)
    public void turnoDeleteByIdTest(){
        Integer idToDelete = 1;
        turnoService.eliminarTurno(idToDelete);

        Optional<Turno> turnoRes = turnoService.buscarTurno(idToDelete);
        assertFalse(turnoRes.isPresent());
    }
}
