package utku.basictask.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utku.basictask.dto.UserDto;
import utku.basictask.entity.User;
import utku.basictask.mapper.UserMapper;
import utku.basictask.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    public UserController(UserService userService,UserMapper userMapper) {
        this.userService = userService;
        this.userMapper=userMapper;
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userMapper.entitiesToDtos(userService.getAllUsers()), HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getOneUser(@PathVariable Long userId){
        User user = userService.findById(userId);
        return new ResponseEntity<>(userMapper.entityToDto(user), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User> createOneUser(@RequestBody UserDto userRequest){
        if(!isUserValid(userRequest)){
            //Exception
        }
        return new ResponseEntity<>(userService.createOneUser(userMapper.dtoToEntity(userRequest)),HttpStatus.CREATED);
    }
    public boolean isUserValid(UserDto userRequest){
        if(userRequest.getUserName() == "" || userRequest.getPassword() == "")
            return false;
        return true ;
    }
}
