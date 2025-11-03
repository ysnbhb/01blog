package _blog.com._blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import _blog.com._blog.Entity.Notifacation;
import _blog.com._blog.utils.NotificationRes;

@Repository
public interface NotifacationRepo extends JpaRepository<Notifacation, Long> {
        @Modifying
        @Transactional
        @Query(value = "DELETE FROM notifacation WHERE (user_id=:userId AND from_id= :fromId AND type='follow') OR (user_id=:fromId AND from_id= :userId AND type='post')", nativeQuery = true)
        void deleteNotifaction(@Param("userId") Long userid, @Param("fromId") Long from);


        @Query(value = """
                        SELECT
                            n.id AS id,
                            n.type AS type,
                            n.content AS content,
                            COALESCE(n.post_id, NULL) AS postId,
                            u1.username AS toUsername,
                            u1.uuid AS toUuid,
                            u2.username AS fromUsername,
                            u2.uuid AS fromUuid,
                            u2.url_photo AS url,
                            n.created_at AS createdAt,
                            n.read AS isRead
                        FROM notifacation n
                        JOIN users u1 ON n.user_id = u1.id
                        JOIN users u2 ON n.from_id = u2.id
                        WHERE n.user_id = :userid
                        ORDER BY n.created_at DESC
                        """, nativeQuery = true)
        List<NotificationRes> findAllNotifactions(@Param("userid") Long userid);

        @Modifying
        @Transactional
        @Query(value = """
                        UPDATE notifacation SET read = true WHERE id = :id
                        """, nativeQuery = true)
        void updateRead(@Param("id") Long notifid);

        @Modifying
        @Transactional
        @Query(value = """
                        UPDATE notifacation SET read = true WHERE user_id = :userId
                        """, nativeQuery = true)
        void updateAll(@Param("userId") Long id);

        @Query(value = """
                        SELECT COUNT(*) FROM notifacation WHERE  read = false AND user_id = :userid
                        """, nativeQuery = true)
        int count(@Param("userid") Long userid);

}
