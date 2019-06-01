package lv.backendherv1.backendherv1.controller;


import lv.backendherv1.backendherv1.config.JwtTokenUtil;
import lv.backendherv1.backendherv1.model.JwtRequest;
import lv.backendherv1.backendherv1.model.JwtResponce;
import lv.backendherv1.backendherv1.model.UserDTO;
import lv.backendherv1.backendherv1.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO)throws Exception{
        return ResponseEntity.ok(userDetailsService.save(userDTO));
    }
    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception{
        authenticate(authenticationRequest.getUsername(),authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponce(token));
    }
    private void authenticate(String username, String password)throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS",e);
        }
    }
}
