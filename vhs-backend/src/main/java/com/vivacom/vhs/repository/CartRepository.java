package com.vivacom.vhs.repository;

import com.vivacom.vhs.model.Cart;
import com.vivacom.vhs.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUser(Users user);

    Cart findByItemId(int itemId);
}
