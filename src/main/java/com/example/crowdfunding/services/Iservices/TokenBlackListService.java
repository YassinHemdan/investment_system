package com.example.crowdfunding.services.Iservices;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenBlackListService {
    void addToBlacklist(HttpServletRequest request);
    boolean isBlacklisted(HttpServletRequest request);
}
