package _blog.com._blog.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.services.NotifacationSer;
import _blog.com._blog.utils.NotificationRes;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class NotifacationContral {
    private final NotifacationSer notifacationSer;

    @GetMapping("notifacition")
    public List<NotificationRes> getNotifacation(@RequestAttribute("userId") Long id) {
        return notifacationSer.findAllNotifactions(id);
    }

    @GetMapping("countNotif")
    public int countNotif(@RequestAttribute("userId") Long userid) {
        return notifacationSer.countNotif(userid);
    }

    @PutMapping("raedNotif/{notifId}")
    public void read(@RequestAttribute("userId") Long userid, @PathVariable Long notifId) throws Exception {
        notifacationSer.raedNotif(notifId, userid);
    }

    @PutMapping("raedNotifAll")
    public void readAll(@RequestAttribute("userId") Long userid) throws Exception {
        System.out.println(userid);
        notifacationSer.raedNotifAll(userid);
    }

}
