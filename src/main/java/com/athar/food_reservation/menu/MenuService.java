package com.athar.food_reservation.menu;

import com.athar.food_reservation.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final UserRepository userRepository;
    private final MenuMapper menuMapper;

    public Long save(MenuRequest menuRequest) {
        var menu = menuMapper.toMenu(menuRequest);
        return menuRepository.save(menu).getId();
    }



    
}
