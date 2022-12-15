package ru.gb.SpringOne.api;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.SpringOne.dto.AppUserDto;
import ru.gb.SpringOne.services.AppUserService;
import ru.gb.SpringOne.utils.Converter;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService userService;
    private final Converter converter;


    @GetMapping
    public Page<AppUserDto> getUsers(@RequestParam(name = "rows", required = false, defaultValue = "5") int rows,
                                     @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        if (rows < 0) rows = 5;
        if (page < 0) page = 1;
        return userService.getUsers(rows, page).map(converter::appUserToDto);
    }

    @GetMapping("/{id}")
    public AppUserDto getUser(@PathVariable Long id){
        return converter.appUserToDto(userService.getUserById(id));
    }
}
