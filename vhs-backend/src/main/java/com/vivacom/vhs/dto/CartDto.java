package com.vivacom.vhs.dto;

import com.vivacom.vhs.model.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CartDto extends Cart {
    private String username;
}
