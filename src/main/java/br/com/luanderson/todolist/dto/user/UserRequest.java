package br.com.luanderson.todolist.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(@NotBlank @Size(min = 3, max = 50) String username, @NotBlank String name,
        @NotBlank @Size(min = 6, max = 20) String password) {

}
