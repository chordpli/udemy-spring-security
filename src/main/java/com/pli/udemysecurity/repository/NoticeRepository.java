package com.pli.udemysecurity.repository;

import com.pli.udemysecurity.model.Notice;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

  @Query(value = "from Notice n where CURDATE() BETWEEN n.noticBegDt AND n.noticEndDt")
  List<Notice> findAllActiveNotices();
}
