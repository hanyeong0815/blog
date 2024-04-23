package com.self.blog.web.controller;

import com.self.blog.read_model.ProfileLogReadModels.ProfileLogDetailViewReadModel;
import com.self.blog.web.service.ProfileLogFindAllProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("profile-log")
@RequiredArgsConstructor
public class ProfileLogQueryApi {
    private final ProfileLogFindAllProxyService profileLogFindAllProxyService;

    @GetMapping("")
    public List<ProfileLogDetailViewReadModel> findAllProfileLog(@PageableDefault Pageable pageable) {
        pageable = pageable.previousOrFirst();

        System.out.println(STR."pageable = \{pageable}");

        return profileLogFindAllProxyService.findAll(pageable);
    }
}
