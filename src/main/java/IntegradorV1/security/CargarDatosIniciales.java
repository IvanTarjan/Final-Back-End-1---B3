package IntegradorV1.security;

import IntegradorV1.entity.Role;
import IntegradorV1.entity.Usuario;
import IntegradorV1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargarDatosIniciales implements ApplicationRunner {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public CargarDatosIniciales(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String passCifrada1 = cifrador.encode("contrasena");
        Usuario usuario1 = new Usuario("Ivan", "ivanMail@gmail.com", passCifrada1, Role.ROLE_USER  );
        usuarioRepository.save(usuario1);
        String passCifrada2 = cifrador.encode("contrasena");
        Usuario usuario2 = new Usuario("Ivan", "ivanAdmin@gmail.com", passCifrada2, Role.ROLE_ADMIN  );
        usuarioRepository.save(usuario2);
    }
}
