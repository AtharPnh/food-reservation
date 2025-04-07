package com.athar.food_reservation.menu;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    //todo: add menu by admin or staff
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('MENU_WRITE')")
    public ResponseEntity<Long> saveMenu(
            @Valid @RequestBody MenuRequest menuRequest
    ) {
        return ResponseEntity.ok(menuService.save(menuRequest));
    }

    //todo: update menu by admin or staff
    @PutMapping("/update/{menu-id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<MenuResponse> updateMenu(
            @PathVariable("menu-id") Long menuId,
            @Valid @RequestBody MenuRequest menuRequest
    ) {
        return ResponseEntity.accepted()
                .body(menuService.update(menuId, menuRequest));
    }
    //todo: delete menu by admin or staff
    //todo: view a list of reserved meals for each day by admin or staff


}
