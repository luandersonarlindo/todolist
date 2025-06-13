package br.com.luanderson.todolist.user.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luanderson.todolist.dto.user.UserRequest;
import br.com.luanderson.todolist.user.entity.UserModel;
import br.com.luanderson.todolist.user.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody @Valid UserRequest userRequest) {
		var userModel = new UserModel();
		BeanUtils.copyProperties(userRequest, userModel);

		userService.create(userModel);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
