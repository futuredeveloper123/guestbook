package kr.co.dajsoft.guestbook.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//PageController를 만들기 위한 어노테이션
@Log4j2
//로그 출력을 위한 어노테이션
@Controller
public class GuestBookController {
    @GetMapping("/")
    public String main(){
        log.info("시작 요청");
        //templates에 있는 guestbook 디렉토리의 list.html을 출력
        return "/guestbook/list";

    }

}
