package br.com.luanderson.todolist.dto.user;

import java.util.UUID;

public record UserResponse(UUID id,String username, String name) {

}
