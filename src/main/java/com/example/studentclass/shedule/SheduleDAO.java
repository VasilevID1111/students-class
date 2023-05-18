package com.example.studentclass.shedule;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SheduleDAO extends JpaRepository<SheduleDTO, Integer> {
    @Query("FROM SheduleDTO a WHERE a.date >= :startDate")
    List<SheduleDTO> findAllAfterDate(Date startDate);

    @Query("FROM SheduleDTO a WHERE a.date = :date AND a.status IN :statuses")
    List<SheduleDTO> findSheduleDTOSByDateAndStatuses(@Param("date") Date date,@Param("statuses") List<String> statuses);

    @Query("FROM SheduleDTO a WHERE a.visit_id = :visit_id")
    SheduleDTO findSheduleDTOByVisit_id(Integer visit_id);
    @Query("FROM SheduleDTO a WHERE a.date >= :startDate AND a.computer.compId = :compId")
    List<SheduleDTO> findByCompAfterDate(@Param("compId") Integer compId,@Param("startDate") Date afterDate);

    @Query("FROM SheduleDTO a WHERE a.date >= :startDate AND a.user.Id = :userId")
    List<SheduleDTO> findByUserIdAfterDate (@Param("userId") Integer userId,@Param("startDate") Date afterDate);
    @Query("FROM SheduleDTO a WHERE a.user.Id = :userId")
    List<SheduleDTO> findByUser(Integer userId);

    @Query("FROM SheduleDTO a WHERE a.date BETWEEN :twoWeeksEarlier AND :twoWeeksLater")
    List<SheduleDTO> findAllBetweenTwoDate(Date twoWeeksEarlier, Date twoWeeksLater);

    @Query("FROM SheduleDTO a WHERE 1=1 " +
            "AND (a.user.login LIKE :login OR :login IS NULL OR :login = '') " +
            "AND (a.computer.compId = :compId OR :compId IS NULL OR :compId = 0) " +
            "AND (a.date >= :dateFrom OR DATE(:dateFrom) IS NULL) " +
            "AND (a.date <= :dateTo OR DATE(:dateTo) IS NULL)")
    List<SheduleDTO> findSheduleDTOSBySettings(String login, Integer compId, Date dateFrom, Date dateTo);
}
