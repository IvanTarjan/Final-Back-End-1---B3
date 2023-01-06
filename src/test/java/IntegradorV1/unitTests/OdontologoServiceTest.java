package IntegradorV1.unitTests;

import IntegradorV1.entity.Odontologo;
import IntegradorV1.service.OdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {

    private OdontologoService odontologoService;

    @Autowired
    public OdontologoServiceTest(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    @Test
    @Order(1)
    public void odontologoInsertTest(){
        Odontologo odontologoGuardado = odontologoService.guardar(new Odontologo("4352345", "Ivan", "Tarjan"));
        assertEquals(1, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void odontologoGetByIdTest(){
        Integer idABUscar = 1;
        Odontologo odontologoExpec = new Odontologo(1,"4352345", "Ivan", "Tarjan");
        Odontologo odontologoRes = odontologoService.buscarOdontologo(idABUscar).get();
        assertEquals(odontologoExpec, odontologoRes);
    }

    @Test
    @Order(3)
    public void odontologoGetAllTest(){
        List<Odontologo> odontologoListExpec = new ArrayList<>();
        odontologoListExpec.add(new Odontologo(1,"4352345", "Ivan", "Tarjan"));
        List<Odontologo> odontologoListRes = odontologoService.listarTodos();
        assertEquals(odontologoListExpec, odontologoListRes);
    }

    @Test
    @Order(4)
    public void odontologoModifyTest(){
        Odontologo odontologoExpec = new Odontologo(1,"4352345", "Ivan", "Caldarini");
        odontologoService.guardar(odontologoExpec);
        Odontologo odontologoRes = odontologoService.buscarOdontologo(1).get();
        assertEquals(odontologoExpec, odontologoRes);

    }

    @Test
    @Order(5)
    public void odontologoDeleteByIdTest(){
        Integer idToDelete = 1;
        odontologoService.eliminar(idToDelete);
        Optional<Odontologo> odontologoRes = odontologoService.buscarOdontologo(idToDelete);
        assertFalse(odontologoRes.isPresent());
    }
}
