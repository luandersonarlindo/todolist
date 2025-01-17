package br.com.luanderson.todolist.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.luanderson.todolist.user.entity.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
	
	UserModel findByUsername(String username);
	
}
