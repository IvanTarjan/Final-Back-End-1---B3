package IntegradorV1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "domicilios")
@Getter @Setter
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String calle;
    @Column
    private Integer numero;
    @Column
    private String localidad;
    @Column
    private String provincia;

    public Domicilio(Integer id, String calle, Integer numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Domicilio(String calle, Integer numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Domicilio(){}

    @Override
    public String toString() {
        return "Domicilio{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Domicilio domicilio = (Domicilio) o;
        return id.equals(domicilio.id) && calle.equals(domicilio.calle) && numero.equals(domicilio.numero) && localidad.equals(domicilio.localidad) && provincia.equals(domicilio.provincia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calle, numero, localidad, provincia);
    }
}
