package com.athar.food_reservation.menu;

import com.athar.food_reservation.common.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Long> saveMenu(
            @Valid @RequestBody MenuRequest menuRequest
    ) {
        return ResponseEntity.ok(menuService.save(menuRequest));
    }

    @PutMapping("/update/{menu-id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<MenuResponse> updateMenu(
            @PathVariable("menu-id") Long menuId,
            @Valid @RequestBody MenuRequest menuRequest
    ) {
        return ResponseEntity.accepted()
                .body(menuService.update(menuId, menuRequest));
    }

    @DeleteMapping("/{menu-id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Void> deleteMenu(
            @PathVariable("menu-id") Long menuId) {
        menuService.delete(menuId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'EMPLOYEE')")
    public ResponseEntity<PageResponse<MenuResponse>> findAllMenusForEachDay(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(menuService.findAllMenusForEachDay(date, page, size));
    }
}
