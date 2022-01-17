package kr.co.dajsoft.guestbook;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import kr.co.dajsoft.guestbook.entity.GuestBook;
import kr.co.dajsoft.guestbook.entity.QGuestBook;
import kr.co.dajsoft.guestbook.repository.GuestBookRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Pageable;
import org.springframework.util.Base64Utils;

@SpringBootTest
public class GuestBookRepositoryTest {

    @Autowired
    private GuestBookRepository guestBookRepository;

    //삽입 테스트
    //@Test
    public void insertGuestBook(){
        for(int i =1; i<=300; i=i+1){
            GuestBook guestBook= GuestBook.builder()
                    .title("Title..." + i)
                    .content("Content..." + i)
                    .writer("User..." + i)
                    .build();
            guestBookRepository.save(guestBook);
        }
    }
    //@Test
//    public void updateGuestBook(){
//
//        GuestBook guestBook= GuestBook.builder()
//                .gno(1L)
//                .title("Title change")
//                .content("Content change" )
//                .writer("User change" )
//                .build();
//        guestBookRepository.save(guestBook);
//    }
   // @Test
    public void updateGuestBook(){

        GuestBook guestBook= GuestBook.builder()
                .gno(2L)
                .title("Title change")
                .content("Content change" )
                .writer("User change" )
                .build();
        guestBookRepository.save(guestBook);
    }
    //@Test
    public void testQuery(){
        //paging설정
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());
        //Querydsl Entity 를 가져오기
        QGuestBook qGuestBook = QGuestBook.guestBook;
        //조건생성
        BooleanBuilder builder = new BooleanBuilder();
        //뭘 검색할건지 .. 결정 내리기! reg 데이트 왜 안나와!!!!!!!!!!! 분노
        //title에 1을 포함한 테이터를 조회
        String keyword = "1";
        BooleanExpression expression = qGuestBook.title.contains(keyword);
        //조건을 만들고
        //조건을 추가
        builder.and(expression);
        //검색수행
        Page<GuestBook> result = guestBookRepository.findAll(builder,pageable);

        //출력
        for(GuestBook guestBook : result ){
            System.out.println(guestBook);
        }
    }
    @Test
    //title이나 content에 1이 포함되어있고 gno의 값이 200보다 작은 데이터 조회하기
    public void testSelectQuery(){
        //페이징 할 때는 pageable
        Pageable pageable = PageRequest.of(0,10,Sort.by("gno").descending());
        //guestbook 가져오기
        QGuestBook qGuestBook = QGuestBook.guestBook;
        //  빌더 가져오기
        BooleanBuilder builder = new BooleanBuilder();
        //1이 포함되어있는 ,,
        String keyword = "1";
        //타이틀에 1을 포함한 , 컨텐트에 1을 포함한
        BooleanExpression exTitle = qGuestBook.title.contains(keyword);
        BooleanExpression exContent = qGuestBook.content.contains(keyword);
        //얘네 둘을 or로 연결해서 추가
        exTitle.or(exContent);
        //뭐다 자동으로 완성이 돼.....
        builder.and(exTitle.or(exContent));
        //이제 200보다 작은거 gno 가 //크다가 gt 작다가 lt (less than)
        BooleanExpression exGno=qGuestBook.gno.lt(200L);
        builder.and(exGno);

        //검색 수행
        Page<GuestBook> result = guestBookRepository.findAll(builder,pageable);

        //출력
        for(GuestBook guestBook : result ){
            System.out.println(guestBook);
        }



    }
}

