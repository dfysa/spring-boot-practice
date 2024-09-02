package top.fyl.springboot.quickstart.service;

import org.springframework.stereotype.Service;
import top.fyl.springboot.quickstart.entity.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dfysa
 * @data 2024/9/2 下午4:29
 * @description
 */
@Service
public class MeetingService {
    private final List<Meeting> meetings =new ArrayList<>();

    public  boolean isRoomAvilable(Meeting newMeeting){
        return meetings.stream()
                .noneMatch(exitingMeeting->exitingMeeting.isOverlapping(newMeeting));
    }

    public void addMeeting(Meeting meeting){
        meetings.add(meeting);
    }
}
