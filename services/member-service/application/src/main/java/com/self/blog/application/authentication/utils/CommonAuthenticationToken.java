package com.self.music.member.application.authentication.utils;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public abstract class CommonAuthenticationToken extends AbstractAuthenticationToken {
    public CommonAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public static Authentication unauthenticated (Class<? extends CommonAuthenticationToken> clazz, Object principal, Object credentials) {
        try {
            Constructor<? extends CommonAuthenticationToken> ctor = clazz.getConstructor(Object.class, Object.class);
            return ctor.newInstance(principal, credentials);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new AuthenticationServiceException("occurred a problem while creating token");
        }
    }

    public static Authentication authenticated
            (Class<? extends CommonAuthenticationToken> clazz,
             Object principal,
             Object credentials,
             Collection<? extends GrantedAuthority> authorities){
        try {
            Constructor<? extends CommonAuthenticationToken> ctor =
                    clazz.getConstructor(Object.class, Object.class, Collection.class);
            return ctor.newInstance(principal, credentials, authorities);
        } catch (Exception e){
            throw new AuthenticationServiceException("occurred a problem while creating token");
        }
    }
}
