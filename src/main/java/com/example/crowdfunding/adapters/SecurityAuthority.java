package com.example.crowdfunding.adapters;

import com.example.crowdfunding.entities.Authority;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {
    private final Authority authority;
    @Override
    public String getAuthority() {
        return authority.getAuthorityName();
    }
}