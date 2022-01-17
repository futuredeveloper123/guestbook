package kr.co.dajsoft.guestbook.controller;

import kr.co.dajsoft.guestbook.dto.GuestBookDTO;
import kr.co.dajsoft.guestbook.dto.PageRequestDTO;
import kr.co.dajsoft.guestbook.dto.PageResponseDTO;
import kr.co.dajsoft.guestbook.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
//로그 출력을 위한 어노테이션

//PageController를 만들기 위한 어노테이션
@Controller
@RequiredArgsConstructor
public class GuestBookController {
    //service 주입
    private final GuestBookService service;

    @GetMapping("/")
    public String main() {
        log.info("시작 요청");
        //templates에 있는 guestbook 디렉토리의 list.html을 출력
        return "redirect:/guestbook/list";

    }

    @GetMapping("/guestbook/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list.............");
        PageResponseDTO result = service.getList(pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    @GetMapping("/guestbook/register")
    public void register() {
        log.info("move to registerrequest");
    }

    @PostMapping("/guestbook/register")
    public String registerPost(GuestBookDTO dto, RedirectAttributes redirectAttributes){
        log.info("register");
        //삽입처리
        Long gno = service.register(dto);
        //리다이렉트 할 대 한 번만 사용하는 데이터 생성
        redirectAttributes.addFlashAttribute
                ("msg",gno+"insert");
        //작업후 목록보기로 리다이렉트
        return"redirect:/guestbook/list";
    }
    @GetMapping("/guestbook/read")
    //파라미터 중에서 gno는 gno에 대입이되고 나머지는
    //requestDTO에 대입됩니다 .
    //다음 결과 페이지에 전송됩니다.
    public void read(long gno,
                     @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model ){
        log.info("gno: " + gno);
        GuestBookDTO dto = service.read(gno);
        model.addAttribute("dto", dto);
    }

}



