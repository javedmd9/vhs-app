package com.vivacom.vhs.dto;

import com.vivacom.vhs.model.MemberAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MemberAddressDto extends MemberAddress {
    private Integer memberId;
    private String username;
}
