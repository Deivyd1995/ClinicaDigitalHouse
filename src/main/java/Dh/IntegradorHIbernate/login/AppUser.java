package Dh.IntegradorHIbernate.login;


import Dh.IntegradorHIbernate.login.AppUsuarioRoles;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@Entity
public class AppUser implements UserDetails {

    @Id
    @SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_sequence" )

    private Long id;
    private String nombre;
    private String userName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUsuarioRoles appUsuarioRoles;

    public AppUser() {
    }

    public AppUser(String nombre, String userName, String email, String password, AppUsuarioRoles appUsuarioRoles) {
        this.nombre = nombre;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.appUsuarioRoles = appUsuarioRoles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new  SimpleGrantedAuthority(appUsuarioRoles.name());
        return Collections.singletonList(grantedAuthority);
    }

    @Override
    public String getUsername() {
        return userName;
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
