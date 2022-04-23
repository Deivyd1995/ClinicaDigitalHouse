package Dh.IntegradorHIbernate.login;


import Dh.IntegradorHIbernate.login.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final IUserRepository iUserRepository;

    @Autowired
    public UserService(IUserRepository iUserRepository){
        this.iUserRepository = iUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return iUserRepository.findByEmail(email).orElseThrow((() -> new  UsernameNotFoundException("User email not found")));
    }
}
