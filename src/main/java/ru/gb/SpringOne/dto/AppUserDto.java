package ru.gb.SpringOne.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppUserDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private Boolean enabled;
}
