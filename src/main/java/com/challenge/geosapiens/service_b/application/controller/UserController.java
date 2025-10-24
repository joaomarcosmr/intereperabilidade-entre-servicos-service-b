package com.challenge.geosapiens.service_b.application.controller;

import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.usecase.user.ListUsersUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Users", description = "Endpoints for user management")
public class UserController {

    private final ListUsersUseCase listUsersUseCase;

    @Operation(
            summary = "List all users",
            description = "Returns a list of all users registered in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User list retrieved successfully"
            )
    })
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        log.info("[UserController] Listing all users");
        List<User> users = listUsersUseCase.execute();
        return ResponseEntity.ok(users);
    }
}

