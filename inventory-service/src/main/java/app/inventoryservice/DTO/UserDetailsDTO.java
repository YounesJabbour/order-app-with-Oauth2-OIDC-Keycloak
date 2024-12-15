package app.inventoryservice.DTO;

import java.util.List;

public class UserDetailsDTO {
    private String username;
    private List<String> authorities;

    public UserDetailsDTO(String username, List<String> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    // Getters and setters
}