package Dh.IntegradorHIbernate.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private IUserRepository iAppUserRepository;

    @Autowired
    public DataLoader(IUserRepository iAppUserRepository) {
        this.iAppUserRepository = iAppUserRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("password");
        String password2 = passwordEncoder.encode("password2");

        iAppUserRepository.save(new AppUser("Charley","charley","charley@digitalhouse.com",password,AppUsuarioRoles.ADMIN));
        iAppUserRepository.save(new AppUser("Charley2","charley2","charley@digitalhouse.com2",password,AppUsuarioRoles.USER));
    }
}
