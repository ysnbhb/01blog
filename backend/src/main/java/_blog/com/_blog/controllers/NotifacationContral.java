package _blog.com._blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.services.NotifacationSer;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class NotifacationContral {
    private final NotifacationSer notifacationSer;

    @GetMapping("notifacition")
    public List<Map<String, Object>> getNotifacation(@RequestAttribute("userId") Long id) {
        return notifacationSer.findAllNotifactions(id);
    }

    @GetMapping("countNotif")
    public int countNotif(@RequestAttribute("userId") Long userid) {
        return notifacationSer.countNotif(userid);
    }

}
