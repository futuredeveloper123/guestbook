package kr.co.dajsoft.guestbook.dto;

import kr.co.dajsoft.guestbook.entity.GuestBook;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//출력할 내용을 가지는 DTO
@Data
//DTO는 출력할 DTO클래스이고 EN은 Entity클래스
public class PageResponseDTO <DTO, EN>{
    //DTO 리스트
    private List<DTO> dtoList;

    //총 페이지 번호
    private int totalPage;

    //현재 페이지 번호
    private int page;
    //목록 사이즈
    private int size;

    //시작 페이지 번호, 끝 페이지 번호
    private int start, end;

    //이전, 다음
    private boolean prev, next;

    //페이지 번호  목록
    private List<Integer> pageList;

    //페이지 번호 목록 만드는 메서드
    private void makePageList(Pageable pageable){
//현재페이지번호와데이터추력개수설정
        this.page = pageable.getPageNumber() + 1;
        // 0부터 시작하므로 1을 추가
        this.size = pageable.getPageSize();

        //시작하는 페이지 번호 계산
        ///10은 출력할 페이지번호개수
        //9는 페이지번호개수 -1
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
        start = tempEnd - 9;
        prev = start > 1;
        end = totalPage > tempEnd ? tempEnd: totalPage;
        next = totalPage > tempEnd;
        //페이지 번호 목록 만들기
        pageList = new ArrayList<>();
        for(int i = start; i<=end; i++){
            pageList.add(i);
        }
        //IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

    }

    //stream은 순회
    //map은 변환
    //collect는 Collection을 생성해주는 메서드
    //page의 내용을 가지고 dtoList로 만들어주는 메서드
    public PageResponseDTO(Page<EN> result, Function<EN,DTO> fn){
        dtoList = result.stream()
                .map(fn)
                .collect(Collectors.toList());

        //전체 페이지 개수 가져오기
        totalPage=result.getTotalPages();
        makePageList(result.getPageable());
    }



}
