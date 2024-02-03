package com.nextecommerce.commerce.services.impl;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
       /* Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // todo: add checks if authentication is present and user is not null (i.e. signed in).

        SignedUser user = (SignedUser) authentication.getPrincipal();

        return Optional.of(user.getUserId().toString());*/
        return Optional.of("");
    }
}
