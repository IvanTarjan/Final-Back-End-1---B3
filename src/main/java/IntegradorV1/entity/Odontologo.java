package IntegradorV1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "odontologos")
@Getter @Setter
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String nroMatricula;
    @Column
    private String nombre;
    @Column
    private String apellido;

    @OneToMany( mappedBy="odontologo", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Turno> turnoList = new ArrayList<Turno>();

    public Odontologo(Integer id, String nroMatricula, String nombre, String apellido) {
        this.id = id;
        this.nroMatricula = nroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo(String nroMatricula, String nombre, String apellido) {
        this.nroMatricula = nroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo(){}

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", nroMatricula=" + nroMatricula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Odontologo that = (Odontologo) o;
        return Objects.equals(id, that.id) && nroMatricula.equals(that.nroMatricula) && nombre.equals(that.nombre) && apellido.equals(that.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nroMatricula, nombre, apellido, turnoList);
    }
}
