package kr.co.dajsoft.guestbook.service;

import kr.co.dajsoft.guestbook.dto.GuestBookDTO;
import kr.co.dajsoft.guestbook.dto.PageRequestDTO;
import kr.co.dajsoft.guestbook.dto.PageResponseDTO;
import kr.co.dajsoft.guestbook.entity.GuestBook;

public interface GuestBookService {
    //dto 클래스의 인스턴스를 entity인스턴스로 변환해주는 메서드 !
    default GuestBook dtoToEntity(GuestBookDTO dto) {
        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())

                .build();
        return entity;
    }
    //데이터베이스에서 가져온 entity클래스의 인스턴스를 dto클래스의 인스턴스로 변환해주는 메서드
    default GuestBookDTO entityToDTO(GuestBook guestBook) {
        GuestBookDTO dto = GuestBookDTO.builder()
                .gno(guestBook.getGno())
                .title(guestBook.getTitle())
                .content(guestBook.getContent())
                .writer(guestBook.getWriter())
                .regDate(guestBook.getRegDate())
                .modDate(guestBook.getModDate())
                .build();
        return dto;
    }
    //데이터 삽입을 위한 메서드
    public Long register(GuestBookDTO dto);


    //데이터 목록보기를 위한 메서드
    public PageResponseDTO<GuestBookDTO , GuestBook> getList(
            PageRequestDTO requestDTO);

    //상세보기를 위한 메서드 선언
    public GuestBookDTO read(Long gno);
    //데이터 한개 찾아올 때는 primary key


}
