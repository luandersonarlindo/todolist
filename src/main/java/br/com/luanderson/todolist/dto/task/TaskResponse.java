package br.com.luanderson.todolist.dto.task;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponse(UUID id, String description, String title, String priority,
                LocalDateTime startAt, LocalDateTime endAt, UUID idUser) {

}
