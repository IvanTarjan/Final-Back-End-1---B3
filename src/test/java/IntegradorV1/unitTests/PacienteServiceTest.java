package IntegradorV1.unitTests;

import IntegradorV1.entity.Domicilio;
import IntegradorV1.entity.Paciente;
import IntegradorV1.service.PacienteService;
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
public class PacienteServiceTest {

    private PacienteService pacienteService;

    @Autowired
    public PacienteServiceTest(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Test
    @Order(1)
    public void pacienteInsertTest(){
        Paciente pacienteAGuardar = new Paciente("Ivan","Tarjan", "45072214", LocalDate.of(2022, 11,22), new Domicilio("Calle1", 403,"Tigre", "Buenos Aires"), "ivan@gmail.com");
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
        assertEquals(1, pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void pacienteGetByIdTest(){
        Integer idABuscar = 1;
        Paciente pacienteAGuardar = new Paciente(1,"Ivan","Tarjan", "45072214", LocalDate.of(2022, 11,22), new Domicilio(1,"Calle1", 403,"Tigre", "Buenos Aires"), "ivan@gmail.com");
        Paciente pacienteBuscado = pacienteService.buscarPaciente(idABuscar).get();
        assertEquals(pacienteAGuardar, pacienteBuscado);
    }

    @Test
    @Order(3)
    public void pacienteGetAllTest(){
        List<Paciente> pacienteListExpec = new ArrayList<>();
        Paciente pacienteAGuardar = new Paciente(1,"Ivan","Tarjan", "45072214", LocalDate.of(2022, 11,22), new Domicilio(1,"Calle1", 403,"Tigre", "Buenos Aires"), "ivan@gmail.com");
        pacienteListExpec.add(pacienteAGuardar);
        List<Paciente> pacienteListAct = pacienteService.buscarTodosPacientes();
        assertEquals(pacienteListExpec, pacienteListAct);
    }

    @Test
    @Order(4)
    public void pacienteGetByEmailTest(){
        String EmailABuscar = "ivan@gmail.com";
        Paciente pacienteAGuardar = new Paciente(1,"Ivan","Tarjan", "45072214", LocalDate.of(2022, 11,22), new Domicilio(1,"Calle1", 403,"Tigre", "Buenos Aires"), "ivan@gmail.com");
        Paciente pacienteBuscado = pacienteService.buscarPacienteXEmail(EmailABuscar).get();
        assertEquals(pacienteAGuardar, pacienteBuscado);
    }

    @Test
    @Order(5)
    public void pacienteModifyTest(){
        Paciente pacienteExpec = new Paciente(1,"Ivan","Dimitri", "45072214", LocalDate.of(2022, 11,22), new Domicilio(1,"Calle1", 403,"Tigre", "Buenos Aires"), "ivan@gmail.com");
        pacienteService.guardarPaciente(pacienteExpec);
        Paciente pacienteRes = pacienteService.buscarPaciente(1).get();
        assertEquals(pacienteExpec, pacienteRes);
    }

    @Test
    @Order(6)
    public void pacienteDeleteByIdTest(){
        Integer pacienteAEliminar = 1;
        pacienteService.eliminarPaciente(pacienteAEliminar);
        Optional<Paciente> pacienteElimExpected = pacienteService.buscarPaciente(pacienteAEliminar);
        assertFalse(pacienteElimExpected.isPresent());
    }
}
