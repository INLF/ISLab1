package com.eldar.ISlab1.util;

import com.eldar.ISlab1.domain.auth.ClientPrincipal;
import com.eldar.ISlab1.domain.model.ClientEntity;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

@UtilityClass
public class SecurityUtils {

    public ClientEntity getCurrentClientAuthentication() {
        return ((ClientPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClient();
    }
}
