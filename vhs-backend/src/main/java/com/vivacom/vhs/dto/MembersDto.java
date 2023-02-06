package com.vivacom.vhs.dto;

import com.vivacom.vhs.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MembersDto extends Member {
    private Integer userId;
    private String username;
}
