package kr.co.dajsoft.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class GuestBookDTO {

    private Long gno;
    private String title;
    private String content;
    private String writer;

    //상속 받은거 2개까지 총 6개 항목
    private LocalDateTime regDate, modDate;

}
