package com.self.blog.application.repository;

import com.self.blog.domain.SignInTry;

import java.util.Optional;

public interface SignInTryRepository {
    SignInTry save(SignInTry signInTry);
    Optional<SignInTry> findById(String username);
    SignInTry countUpSignTry(String username);
    void deleteById(String username);
}
