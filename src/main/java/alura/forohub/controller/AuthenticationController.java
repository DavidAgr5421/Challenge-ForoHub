package alura.forohub.controller;


import alura.forohub.domain.users.User;
import alura.forohub.domain.users.UserDataAuthentication;
import alura.forohub.infra.security.TokenService;
import alura.forohub.infra.security.dataJWTtoken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity userAuthentication(@RequestBody @Valid UserDataAuthentication userDataAuthentication){
        Authentication autToken = new UsernamePasswordAuthenticationToken(userDataAuthentication.login(),userDataAuthentication.password());
        var authenticatedUser = authenticationManager.authenticate(autToken);
        var jwtToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new dataJWTtoken(jwtToken,"Bearer"));
    }
}
