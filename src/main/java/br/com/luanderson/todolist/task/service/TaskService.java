package br.com.luanderson.todolist.task.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luanderson.todolist.dto.task.TaskUpdateRequest;
import br.com.luanderson.todolist.task.entity.TaskModel;
import br.com.luanderson.todolist.task.repository.ITaskRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class TaskService {

    @Autowired
    private ITaskRepository taskRepository;

    public TaskModel save(TaskModel taskModel, HttpServletRequest request) {
        var idUser = request.getAttribute("id");
        taskModel.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            throw new IllegalArgumentException(
                    "A data de início / data de término deve ser maior do que a data atual!");
        }

        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            throw new IllegalArgumentException("A data de início deve ser menor que a data de término!");
        }

        return taskRepository.save(taskModel);
    }

    public List<TaskModel> findAll() {
        return taskRepository.findAll();
    }

    public TaskModel findById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
    }

    public TaskModel update(UUID id, TaskUpdateRequest taskRequest) {
        // Busca a tarefa pelo ID
        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

        // Atualiza os campos da tarefa
        task.setTitle(taskRequest.title());
        task.setDescription(taskRequest.description());
        task.setPriority(taskRequest.priority());
        task.setStartAt(taskRequest.startAt());
        task.setEndAt(taskRequest.endAt());

        return taskRepository.save(task);
    }

    public void delete(TaskModel taskModel) {
        taskRepository.delete(taskModel);
    }

}
