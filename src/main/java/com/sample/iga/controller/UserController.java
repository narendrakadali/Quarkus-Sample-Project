package com.sample.iga.controller;

import com.sample.iga.model.User;
import com.sample.iga.service.UserService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)  // Ensure JSON response
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    public Uni<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{username}")
    public Uni<User> getUserByUsername(@PathParam("username") String username) {
        return userService.getUserByUsername(username);
    }

    @POST
    public Uni<User> createUser(User user) {
        return userService.createUser(user);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Boolean> deleteUser(@PathParam("id") Long id) {
        return userService.deleteUser(id);
    }
}
