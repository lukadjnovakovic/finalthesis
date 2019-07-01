package security;

import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.IUserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    IUserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        UserDTO user = userService.returnUserByUsername(usernameOrEmail);
        if (user == null) {
           throw  new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
        }
        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(int id) throws Exception {
        UserDTO user = userService.returnUserById(id);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with id : " + id);
        }
        return UserPrincipal.create(user);
    }
}
