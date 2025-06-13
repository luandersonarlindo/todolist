package br.com.luanderson.todolist.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luanderson.todolist.user.entity.UserModel;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, UUID> {

	Optional<UserModel> findByUsername(String username);

	Optional<UserModel> findByName(String name);

}
