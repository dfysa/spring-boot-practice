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
        if (meetingService.isRoomAvilable(meeting)){
            meetingService.addMeeting(meeting);
            return ResponseEntity.ok("会议室可用");
        }else {
            return ResponseEntity.status(409).body("会议室不可用");
        }
        }
    }

