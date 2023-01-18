package com.yeojin.book.config.auth;

import com.yeojin.book.config.auth.dto.OAuthAttributes;
import com.yeojin.book.config.auth.dto.SessionUser;
import com.yeojin.book.domain.user.User;
import com.yeojin.book.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

//https://codediary21.tistory.com/73 내용 파악
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User>
                delegate = new DefaultOAuth2UserService();//0) new DefaultOAuth2UserService()는 OAuth2UserService의 구현체
        OAuth2User oAuth2User = delegate.loadUser(userRequest);//대리로 loadUser 챙김.

        String registrationId = userRequest.
                getClientRegistration().getRegistrationId();
        //1) registrationId 현재 로그인 진행 중인 서비스를 구분하는 코드, 네이버 로그인인지 구글인지 카카오인지 현재 로그인된 것을 구분
        String userNameAttributeName = userRequest.
                getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        //2) userNameAttributeName OAuth2 로그인 진행 시 키가 되는 필드값을 이야기한다. Primary Key와 같은 의미

        OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userNameAttributeName, oAuth2User.getAttributes());//3) OAuth2User의 attribute를 담을 클래스

        User user = saveOrUpdate(attributes);//로그인한 유저정보

        httpSession.setAttribute("user", new SessionUser(user));//4) httpSession의 유저 속성을 설정

        return new DefaultOAuth2User(//로그인한 유저 return
                Collections.singleton(new
                        SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    //User 저장하고 이미 있는 데이터면 Update
    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
