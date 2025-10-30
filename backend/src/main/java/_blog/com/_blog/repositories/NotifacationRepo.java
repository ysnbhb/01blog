package _blog.com._blog.repositories;

import java.util.List;
import java.util.Map;

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

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM notifacation WHERE post_id=:post_id ", nativeQuery = true)
        void deleteNotifactionByPostid(@Param("post_id") Long post_id);

        @Query(value = """
                        SELECT
                            n.id AS notif_id,
                            n.type,
                            n.content,
                            n.post_id,
                            u1.name AS to_name,
                            u1.uuid AS to_uuid,
                            u2.name AS from_name,
                            u2.uuid AS from_uuid,
                            n.created_at ,
                            n.read;
                        FROM notifacation n
                        JOIN users u1 ON n.user_id = u1.id
                        JOIN users u2 ON n.from_id = u2.id
                        WHERE u.user_id = :userid
                        ORDER BY n.created_at DESC
                        """, nativeQuery = true)
        List<Map<String, Object>> findAllNotifactions(@Param("userid") Long userid);

        @Query(value = """
                        UPDATE notifacation SET read = true WHERE id = :id
                        """, nativeQuery = true)
        void updateRead(@Param("id") Long notifid);

        @Query(value = """
                        SELECT COUNT(*) FROM notifacation WHERE  read = false AND user_id = :userid
                        """, nativeQuery = true)
        int count(@Param("userid") Long userid);

}
