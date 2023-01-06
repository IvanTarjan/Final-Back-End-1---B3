package IntegradorV1.service;

import IntegradorV1.entity.Usuario;
import IntegradorV1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> appUserOpt = usuarioRepository.findByEmail(email);
        Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>();
        if (appUserOpt.isPresent()){
            Usuario appUser = appUserOpt.get();
            return (UserDetails) appUser;
        } else {
            throw new UsernameNotFoundException("No se encontro el mail del usuario en la base de datos");
        }
    }
}
