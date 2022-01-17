package kr.co.dajsoft.guestbook.repository;

import kr.co.dajsoft.guestbook.entity.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long>
        ,QuerydslPredicateExecutor<GuestBook>{


}
