package ru.gb.SpringOne.api.admin;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.SpringOne.services.AppRoleService;
import ru.gb.SpringOne.services.AppUserService;

@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final AppUserService userService;
    private final AppRoleService roleService;



}
