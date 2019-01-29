package cn.hang.neuq.web;

import cn.hang.neuq.common.Response;
import cn.hang.neuq.entity.vo.QueryEmptyRoomConditionVO;
import cn.hang.neuq.entity.vo.QueryEmptyRoomVO;
import cn.hang.neuq.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lihang15
 * @description
 * @create 2019-01-26 01:01
 **/
@RestController
@RequestMapping("/room")
@Slf4j
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/getQueryEmptyRoomInfo")
    public Response<QueryEmptyRoomConditionVO> getQueryEmptyRoomInfo() {
        return roomService.getQueryEmptyRoomInfo();
    }


    @PostMapping("/queryEmptyRoom")
    public Response queryEmptyRoom(@RequestBody QueryEmptyRoomVO queryEmptyRoomVO) {
        return roomService.queryEmptyRoom(queryEmptyRoomVO);
    }


}
