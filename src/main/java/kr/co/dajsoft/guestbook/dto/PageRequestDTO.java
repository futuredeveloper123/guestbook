package kr.co.dajsoft.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    //현재 페이지 번호
    private int page;
    //페이지당출력개수
    private int size;

    //매개변수가 없는 생성자 - default constructor

    public PageRequestDTO(){
        this.page = 1;
        this.size = 10;
    }
    //page와 size를 이용해서 pageable객체를 생성해서 리턴하는 메서드
    public Pageable getPageable(Sort sort){
        //페이지 번호는 -1을 해서 생성
        //여기다가 검색조건 정도만 더 추가해주면 됨 !
        //루빙봉~~~아이 졸려 ~~~~ 흐미흐미노래노래래
       return PageRequest.of(page -1, size, sort);
    }
}
//이거는 결과를 요청하는 디티오~~~~~