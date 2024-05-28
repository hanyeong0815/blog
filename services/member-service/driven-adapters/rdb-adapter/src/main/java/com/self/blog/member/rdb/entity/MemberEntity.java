package com.self.blog.member.rdb.entity;

import com.self.blog.member.domain.type.MemberStatus;
import com.self.blog.jpa.UuidBaseEntity;
import com.self.blog.member.domain.type.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.self.blog.member.rdb.support.MemberSchemaConstants.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        schema = SCHEMA,
        catalog = SCHEMA,
        name = TB_MEMBER
)
public class MemberEntity extends UuidBaseEntity implements UserDetails {
    public String username;
    public String password;
    @Enumerated(EnumType.STRING)
    public MemberStatus status;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            schema = SCHEMA,
            name = TB_MEMBER_ROLES,
            joinColumns = @JoinColumn(name = "memberId")
    )
    @Builder.Default
    @Column(name = "role")
    public List<RoleType> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> role.value)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status.canSignIn;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
