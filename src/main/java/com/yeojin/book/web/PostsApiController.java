package com.yeojin.book.web;

import com.yeojin.book.service.posts.PostsService;
import com.yeojin.book.web.dto.PostsResponseDto;
import com.yeojin.book.web.dto.PostsSaveRequestDto;

import com.yeojin.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController//RestController를 붙이면, 컨트롤러 클래스 하위 메서드에 @ResponseBody 어노테이션을 붙이지 않아도 문자열과 JSON 등을 전송할 수 있습니다.
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }
    //@RequestBody json이 java 객체로 conversioon되며 그 역도 가능

    @PutMapping("/api/v1/posts/{id}")//
    public Long update (@PathVariable("id") Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable("id") Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("api/v1/posts/{id}")
    public Long delete(@PathVariable("id") Long id){
        postsService.delete(id);
        return id;
    }
}
