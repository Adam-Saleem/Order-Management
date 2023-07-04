package com.example.OrderManagement.Auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    @Operation(
            description = "Login Service",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully Login In!",
                            content = @Content(mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{\"code\" : 200, \"Status\" : \"Ok!\", \"Message\" :\"Successfully Login In!\"}"),}
                            ))}
    )
    public ResponseEntity<?> signIn(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {@ExampleObject(
                            value = "{\"username\" : \"adam\", \"password\" : \"admin\"}"),
                    }
            )) @RequestBody Login login) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Successfully login in!"), HttpStatus.OK);
    }
}