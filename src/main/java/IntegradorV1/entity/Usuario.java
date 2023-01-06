package IntegradorV1.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter @Setter
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column
    private String nombre;

    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Role role;

    public Usuario(Integer id, String nombre, String email, String password, Role role) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Usuario(String nombre, String email, String password, Role role) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Usuario() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.name());
        return Collections.singleton(grantedAuthority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
