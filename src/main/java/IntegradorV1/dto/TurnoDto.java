package IntegradorV1.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class TurnoDto {

    private Integer id;
    private Integer paciente_id;
    private String paciente_nombre;
    private String paciente_apellido;
    private Integer odontologo_id;
    private String odontologo_nombre;
    private String odontologo_apellido;
    private String odontologo_matricula;

    private LocalDate fecha;

    public TurnoDto() {
    }

    public TurnoDto(Integer paciente_id, String paciente_nombre, String paciente_apellido, Integer odontologo_id, String odontologo_nombre, String odontologo_apellido, String odontologo_matricula, LocalDate fecha) {
        this.paciente_id = paciente_id;
        this.paciente_nombre = paciente_nombre;
        this.paciente_apellido = paciente_apellido;
        this.odontologo_id = odontologo_id;
        this.odontologo_nombre = odontologo_nombre;
        this.odontologo_apellido = odontologo_apellido;
        this.odontologo_matricula = odontologo_matricula;
        this.fecha = fecha;
    }

    public TurnoDto(Integer id, Integer paciente_id, String paciente_nombre, String paciente_apellido, Integer odontologo_id, String odontologo_nombre, String odontologo_apellido, String odontologo_matricula, LocalDate fecha) {
        this.id = id;
        this.paciente_id = paciente_id;
        this.paciente_nombre = paciente_nombre;
        this.paciente_apellido = paciente_apellido;
        this.odontologo_id = odontologo_id;
        this.odontologo_nombre = odontologo_nombre;
        this.odontologo_apellido = odontologo_apellido;
        this.odontologo_matricula = odontologo_matricula;
        this.fecha = fecha;
    }
}
