package com.example.crowdfunding.services.servicesImpl;

import com.example.crowdfunding.services.Iservices.TokenBlackListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
@Service
public class TokenBlackListServiceImpl implements TokenBlackListService {
    private final Set<String> blacklist = new HashSet<>();
    @Override
    public void addToBlacklist(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        System.out.println("add");
        System.out.println(token);
        blacklist.add(token);
    }

    @Override
    public boolean isBlacklisted(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        System.out.println("boolean");
        System.out.println(token);
        return blacklist.contains(token);
    }
    private String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
