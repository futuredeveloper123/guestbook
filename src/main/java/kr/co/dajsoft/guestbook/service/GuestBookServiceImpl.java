package kr.co.dajsoft.guestbook.service;

import kr.co.dajsoft.guestbook.dto.GuestBookDTO;
import kr.co.dajsoft.guestbook.dto.PageRequestDTO;
import kr.co.dajsoft.guestbook.dto.PageResponseDTO;
import kr.co.dajsoft.guestbook.entity.GuestBook;
import kr.co.dajsoft.guestbook.repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입
public class GuestBookServiceImpl implements GuestBookService{
    //@RequiredArgsConstructor 랑  private final GuestBookRepository repository;
    //합쳐지면 autowired 됨 ! 이제 autowired 붙일 필요없음 !
    private final GuestBookRepository repository;

    @Override
    public Long register(GuestBookDTO dto) {
        log.info("DTO---------------------------------------");
        log.info(dto);
        //dto를 entity로 변환

        GuestBook entity = dtoToEntity(dto);
        log.info(entity);
        //데이터 삽입
        repository.save(entity);
        //삽입한 데이터의 gno 리턴
        return entity.getGno();
    }
    @Override
    public PageResponseDTO<GuestBookDTO, GuestBook>
    getList(PageRequestDTO requestDTO) {
        //pageable 객체 생성
        Pageable pageable = requestDTO.getPageable(
                Sort.by("gno").descending());
        //결과를 가져오기
        Page<GuestBook> result = repository.findAll(pageable);
        //function생성
        Function<GuestBook, GuestBookDTO> fn =
                (entity -> entityToDTO(entity));
        //목록보기가 젤 어려움
        return new PageResponseDTO<>(result, fn );
    }

    @Override
    public GuestBookDTO read(Long gno) {
        //데이터 하나 찾아ㅏ오기
        Optional<GuestBook> guestBook = repository.findById(gno);
        return guestBook.isPresent()? entityToDTO(guestBook.get()): null;
    }

}
