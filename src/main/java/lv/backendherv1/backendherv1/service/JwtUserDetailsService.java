package lv.backendherv1.backendherv1.service;

import lv.backendherv1.backendherv1.dao.UserDAO;
import lv.backendherv1.backendherv1.model.DAOUser;
import lv.backendherv1.backendherv1.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DAOUser user = userDAO.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
    public DAOUser save(UserDTO user){
        DAOUser newUser = new DAOUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDAO.save(newUser);
    }
}
