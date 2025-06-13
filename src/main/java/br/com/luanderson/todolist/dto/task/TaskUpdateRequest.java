package br.com.luanderson.todolist.dto.task;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskUpdateRequest(@NotBlank @Size(min = 5, max = 50) String title,
                @NotBlank @Size(min = 5, max = 250) String description,
                @NotBlank @Size(min = 3, max = 15) String priority,
                @NotNull LocalDateTime startAt, @NotNull LocalDateTime endAt) {

}
