package br.com.luanderson.todolist.task.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.luanderson.todolist.task.entity.TaskModel;

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
	List<TaskModel> findByIdUser(UUID idUser);
}
