package com.athar.food_reservation.menu;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    //todo: add menu by admin or staff
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('MENU_WRITE')")
    public ResponseEntity<Long> saveMenu(
            @Valid @RequestBody MenuRequest menuRequest
    ){
        return ResponseEntity.ok(menuService.save(menuRequest));
    }




    //todo: update menu by admin or staff
    //todo: delete menu by admin or staff
    //todo: view a list of reserved meals for each day by admin or staff



}
