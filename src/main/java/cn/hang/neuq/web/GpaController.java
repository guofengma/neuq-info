package cn.hang.neuq.web;

import cn.hang.neuq.common.Response;
import cn.hang.neuq.entity.po.Gpa;
import cn.hang.neuq.service.GpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lihang15
 * @description
 * @create 2019-01-22 11:10
 **/
@RestController
@RequestMapping("/gpa")
@Slf4j
public class GpaController {
    private final GpaService gpaService;

    @Autowired
    public GpaController(GpaService gpaService) {
        this.gpaService = gpaService;
    }

    @GetMapping("/refresh")
    public Response refresh() {
        return gpaService.refresh();
    }

    @GetMapping("/list")
    public Response<List<Gpa>> list(@RequestParam String semester) {
        return gpaService.list(semester);
    }

}
