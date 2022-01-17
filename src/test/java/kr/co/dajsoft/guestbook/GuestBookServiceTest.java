package kr.co.dajsoft.guestbook;

import kr.co.dajsoft.guestbook.dto.GuestBookDTO;
import kr.co.dajsoft.guestbook.dto.PageRequestDTO;
import kr.co.dajsoft.guestbook.dto.PageResponseDTO;
import kr.co.dajsoft.guestbook.entity.GuestBook;
import kr.co.dajsoft.guestbook.service.GuestBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestBookServiceTest {
    @Autowired //주입받음
    private GuestBookService service;

    // @Test
    public void registerTest() {
        GuestBookDTO dto = GuestBookDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("userO")
                .build();

        Long gno = service.register(dto);
        System.out.println(gno);
    }
    @Test
    public void listTest(){
        PageRequestDTO pageRequestDTO =
                PageRequestDTO
                        .builder()
                        .page(1)
                        .size(10)
                        .build();
        PageResponseDTO<GuestBookDTO, GuestBook> pageResponseDTO =
                service. getList(pageRequestDTO);
        for (GuestBookDTO dto : pageResponseDTO.getDtoList()) {
            System.out.println(dto);
        }
        //이전과 다음 링크여부와 전체 페이지 개수 확인
        System.out.println("========================================");
        System.out.println("PREV: "+pageResponseDTO.isPrev());
        System.out.println ("NEXT: "+pageResponseDTO.isNext());
        System.out.println("TOTAL: " + pageResponseDTO.getTotalPage());
//페이지 번호 목록출력
        System. out. println ("------------------------------------------------------------");
        for (GuestBookDTO i : pageResponseDTO.getDtoList()) {
            System.out.println(i+"\t");
        }

    }


}
