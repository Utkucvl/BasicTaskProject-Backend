package utku.basictask.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utku.basictask.dto.AuthDto;
import utku.basictask.dto.RefreshTokenDto;
import utku.basictask.dto.UserDto;
import utku.basictask.entity.RefreshToken;
import utku.basictask.entity.User;
import utku.basictask.mapper.AuthMapper;
import utku.basictask.mapper.UserMapper;
import utku.basictask.security.JwtTokenProvider;
import utku.basictask.services.RefreshTokenService;
import utku.basictask.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private UserService userService;
    private AuthMapper authMapper;

    private UserMapper userMapper;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private RefreshTokenService refreshTokenService;
    private PasswordEncoder passwordEncoder;

     AuthController(UserService userService,AuthMapper authMapper , UserMapper userMapper,AuthenticationManager authenticationManager,
                    PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider ,RefreshTokenService refreshTokenService ) {
        this.userService = userService;
        this.authMapper = authMapper;
        this.userMapper=userMapper;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider=jwtTokenProvider;
        this.refreshTokenService=refreshTokenService;
    }
    @PostMapping("/login")
    public ResponseEntity<AuthDto> login (@RequestBody UserDto userRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userRequest.getUserName(),userRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        User user = userService.getOneUserByUserName(userRequest.getUserName());
        String refreshToken = refreshTokenService.create(user);
        String accessToken = "Bearer " + jwtToken;
        String message = "You have logged in" ;
        AuthDto authResponse = authMapper.loadBlanksForLogin(user.getId(),accessToken,message,refreshToken);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<AuthDto> register (@RequestBody UserDto userRequest){
        String message ;
        if(userService.getOneUserByUserName(userRequest.getUserName()) != null){
            message = "Username is alredy in use";
            return new ResponseEntity<>(authMapper.loadBlanksForNonSuccessfullRegister(message), HttpStatus.BAD_REQUEST);
        }

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User user = userMapper.dtoToEntity(userRequest);
        userService.createOneUser(user);
        message = "User successfully has been created";
        AuthDto authResponse = authMapper.loadBlanksForRegister(user.getId(),message);
        return new ResponseEntity<>( authResponse, HttpStatus.CREATED);

    }
    @PostMapping("/refresh")
    public ResponseEntity<AuthDto> refresh(@RequestBody RefreshTokenDto refreshTokenDto){
        RefreshToken token = refreshTokenService.getByUser(refreshTokenDto.getUserId());
        if(token.getToken().equals(refreshTokenDto.getRefreshToken()) && !refreshTokenService.isRefreshExpired(token)){
            User user = token.getUser();
            String jwtToken= jwtTokenProvider.generateJwtTokenByUserName(user.getId());
            String message = "Token successfully refreshed";
            String accessToken = "Bearer " + jwtToken;
            int userId= user.getId();
            return  new ResponseEntity<>(authMapper.loadBlanksForRefresh(message,accessToken,userId,refreshTokenDto.getRefreshToken()),HttpStatus.OK);
        }
        else {
            String message = "Refresh Token Is Not Valid";
            return new ResponseEntity<>(authMapper.loadBlanksForNonRefresh(message),HttpStatus.UNAUTHORIZED);

        }

    }
}
