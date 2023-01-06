package IntegradorV1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pacientes")
@Getter @Setter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String dni;
    @Column
    private LocalDate fechaIngreso;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy="paciente", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Turno> turnoList = new ArrayList<Turno>();

    public Paciente(Integer id, String nombre, String apellido, String dni, LocalDate fechaIngreso, Domicilio domicilio, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;

    }

    public Paciente(String nombre, String apellido, String dni, LocalDate fechaIngreso, Domicilio domicilio, String email) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }

    public Paciente(){}

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", domicilio=" + domicilio +
                ", email='" + email + '\'' +
                ", turnoList=" + turnoList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return id.equals(paciente.id) && nombre.equals(paciente.nombre) && apellido.equals(paciente.apellido) && dni.equals(paciente.dni) && fechaIngreso.equals(paciente.fechaIngreso) && domicilio.equals(paciente.domicilio) && email.equals(paciente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, dni, fechaIngreso, domicilio, email, turnoList);
    }
}
