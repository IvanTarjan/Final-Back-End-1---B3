package IntegradorV1.integrationTests;

import IntegradorV1.entity.Domicilio;
import IntegradorV1.entity.Odontologo;
import IntegradorV1.entity.Paciente;
import IntegradorV1.entity.Turno;
import IntegradorV1.service.OdontologoService;
import IntegradorV1.service.PacienteService;
import IntegradorV1.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnoTest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private MockMvc mockMvc;

    private void cargarTurnoInicial(){
        Domicilio domicilio = new Domicilio("Calle1",403, "Tigre", "BsAs");
        Paciente paciente = new Paciente("Ivan", "Tarjan", "dni", LocalDate.of(2022, 12, 7), domicilio,"email@mail.com");
        pacienteService.guardarPaciente(paciente);
        Odontologo odontologo =  new Odontologo("4543532432", "julio", "espert");
        odontologoService.guardar(odontologo);
        Turno turno= new Turno(paciente, odontologo, LocalDate.of(2022, 12,31));
        turnoService.guardarTurno(turno);
    }

    @Test
    public void listadoTurnoTest() throws Exception {
        cargarTurnoInicial();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(result.getResponse().getContentAsString().isEmpty());
    }


}
