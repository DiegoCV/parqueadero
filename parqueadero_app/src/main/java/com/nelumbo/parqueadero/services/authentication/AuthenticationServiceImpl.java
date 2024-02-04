package com.nelumbo.parqueadero.services.authentication;

import com.nelumbo.parqueadero.controllers.authentication.model.AuthenticationRequest;
import com.nelumbo.parqueadero.controllers.authentication.model.AuthenticationResponse;
import com.nelumbo.parqueadero.controllers.authentication.model.CreateUserRequest;
import com.nelumbo.parqueadero.services.authentication.model.Usuario;
import com.nelumbo.parqueadero.services.authentication.model.gateway.UsuarioRepository;
import com.nelumbo.parqueadero.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authRequest.getUser(),
                authRequest.getPass());
        authenticationManager.authenticate(authToken);
        Usuario user =  usuarioRepository.findByEmail(authRequest.getUser()).get();
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new AuthenticationResponse(jwt);
    }

    @Override
    public Usuario createUser(CreateUserRequest createUserRequest) {
        Usuario usuario = Usuario.builder().role(Role.SOCIO).email(createUserRequest.getEmail()).pass(
                passwordEncoder.encode(createUserRequest.getPass())).build();
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findUsuarioById(UUID uuid) {
        return usuarioRepository.findById(uuid);
    }

    private Map<String, Object> generateExtraClaims(Usuario user) {

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("permissions", user.getAuthorities());

        return extraClaims;
    }
}
