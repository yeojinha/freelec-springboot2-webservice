package com.yeojin.book.service.posts;

import com.yeojin.book.domain.posts.Posts;
import com.yeojin.book.domain.posts.PostsRepository;
import com.yeojin.book.web.dto.PostsListResponseDto;
import com.yeojin.book.web.dto.PostsResponseDto;
import com.yeojin.book.web.dto.PostsSaveRequestDto;
import com.yeojin.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("No Post in Repository. id = "+ id));

        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("No Post in Repository. id = "+ id));
        return new PostsResponseDto(entity);
    }
    @Transactional(readOnly = true)//readOnly를 주면 트랜잭션 범위는 유지하면서 조회 기능만(등록,수정,삭제는 제외) 남겨두어 조회속도 개선
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)//posts -> new PostsListResponseDto(posts)
                .collect(Collectors.toList());
    }
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()->//orElseThrow로 엔티티 존재유무 조회하고 그대로 삭제 
                new IllegalArgumentException("해당 게시글이 없습니다. id= "+id));
        postsRepository.delete(posts);//JpaRepository에서 이미 delete 메소드를 지원하고 있으니 이를 활용
        //엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제할 수 도 있다.
        
        
    }
}
