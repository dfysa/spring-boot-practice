package top.fyl.springboot.quickstart.controller;

import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fyl.springboot.quickstart.entity.Meeting;
import top.fyl.springboot.quickstart.service.MeetingService;

/**
 * @author dfysa
 * @data 2024/9/2 下午4:33
 * @description
 */
@RestController
@RequestMapping("/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping("/check")
    public ResponseEntity<String> check (@RequestBody Meeting meeting){
        // 调⽤服务⽅法检查会议室是否在该时间段内可⽤
        if (meetingService.isRoomAvailable(meeting)) {
            // 如果会议室可⽤，则添加会议并返回成功消息
            meetingService.addMeeting(meeting);
            return ResponseEntity.ok("会议室可⽤，会议已预订！");
        } else {
            // 如果会议室不可⽤，返回冲突状态和不可⽤的提示信息
            return ResponseEntity.status(409).body("会议室不可⽤！");
        }

    }
}

