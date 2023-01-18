package com.yeojin.book.web;

import com.yeojin.book.config.auth.LoginUser;
import com.yeojin.book.config.auth.dto.SessionUser;
import com.yeojin.book.web.dto.PostsResponseDto;
import org.springframework.ui.Model;
import com.yeojin.book.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");//1)
        //로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있음.

        if(user!=null) {//2 세션에 저장된 값이 있을 때만, model에 userName으로 등록, 없다면 model에 없으니 login만 보임
            model.addAttribute("userName",user.getName());
        }


        return "index";
        //mustache starter 덕분에, 문자열 반환 시 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.
        /**여기서는 index가 "index"로 반환되는게 아닌 index.mustache를 호출한다*/
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable("id") Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";/**posts-save.mustache를 호출한다 (index.mustache 자리와 같은 위치)*/
    }
}
