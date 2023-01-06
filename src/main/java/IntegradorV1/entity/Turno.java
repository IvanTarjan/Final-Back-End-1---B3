package IntegradorV1.entity;

import IntegradorV1.dto.TurnoDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "turnos")
@Getter @Setter
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;
    @Column
    private LocalDate fecha;


    public Turno(Integer id, Paciente paciente, Odontologo odontologo, LocalDate fecha) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

    public Turno(Paciente paciente, Odontologo odontologo, LocalDate fecha) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

    public Turno() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno turno = (Turno) o;
        return id.equals(turno.id) && paciente.equals(turno.paciente) && odontologo.equals(turno.odontologo) && fecha.equals(turno.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paciente, odontologo, fecha);
    }
    public TurnoDto toDto(){
        TurnoDto turnoDto = new TurnoDto();
        turnoDto.setId(id);
        turnoDto.setPaciente_id(paciente.getId());
        turnoDto.setPaciente_nombre(paciente.getNombre());
        turnoDto.setPaciente_apellido(paciente.getApellido());
        turnoDto.setOdontologo_id(odontologo.getId());
        turnoDto.setOdontologo_nombre(odontologo.getNombre());
        turnoDto.setOdontologo_apellido(odontologo.getApellido());
        turnoDto.setOdontologo_matricula(odontologo.getNroMatricula());
        turnoDto.setFecha(fecha);
        return turnoDto;
    }


}
