package com.kakao.clone.kakao.security;

import com.kakao.clone.kakao.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final User user;


    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {

        return user;
    }

    public String getNickname() {
        return user.getNickname();
    }

    public String getProfileImage() {
        return user.getProfileImage();
    }

    public String getProfileBgImage() {
        return user.getProfileBgImage();
    }


    @Override
    public String getPassword() {

        return user.getPassword();
    }

    @Override
    public String getUsername() {

        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }


}
