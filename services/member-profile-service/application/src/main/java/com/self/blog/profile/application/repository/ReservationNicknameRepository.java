package com.self.blog.profile.application.repository;

import com.self.blog.profile.domain.ReservationNickname;

public interface ReservationNicknameRepository {
    ReservationNickname save(ReservationNickname reservationNickname);
    boolean existsById(String nickname);
}
