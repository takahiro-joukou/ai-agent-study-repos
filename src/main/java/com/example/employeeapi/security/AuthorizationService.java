package com.example.employeeapi.security;

import com.example.employeeapi.exception.ForbiddenException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.Optional;

@Service
public class AuthorizationService {

    private static final Set<String> ALLOWED_ROLES = Set.of("administrator", "hr");

    public void validateAuthorizationHeader(String authorizationHeader) {
        String role = parseRole(authorizationHeader)
                .orElseThrow(() -> new ForbiddenException("従業員一覧を取得する権限がありません。"));

        if (!ALLOWED_ROLES.contains(role)) {
            throw new ForbiddenException("従業員一覧を取得する権限がありません。");
        }
    }

    private Optional<String> parseRole(String authorizationHeader) {
        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            return Optional.empty();
        }
        String tokenPrefix = "Bearer ";
        if (!authorizationHeader.startsWith(tokenPrefix)) {
            return Optional.empty();
        }
        String role = authorizationHeader.substring(tokenPrefix.length()).trim();
        return role.isEmpty() ? Optional.empty() : Optional.of(role);
    }
}
