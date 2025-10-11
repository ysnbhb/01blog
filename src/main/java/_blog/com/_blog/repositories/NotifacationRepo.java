package _blog.com._blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import _blog.com._blog.Entity.Notifacation;

@Repository
public interface NotifacationRepo extends JpaRepository<Notifacation, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM notifacation WHERE user_id=:userId AND from_id= :fromId", nativeQuery = true)
    void deleteNotifaction(@Param("userId") Long userid, @Param("fromId") Long from);

}
