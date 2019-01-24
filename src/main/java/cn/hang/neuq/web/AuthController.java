package cn.hang.neuq.web;

import cn.hang.neuq.common.Response;
import cn.hang.neuq.entity.vo.JwAuthVO;
import cn.hang.neuq.service.AuthService;
import cn.hang.neuq.service.GpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lihang15
 * @description
 * @create 2019-01-22 11:10
 **/
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Response login(@RequestBody JwAuthVO jwAuthVO) {
        log.info("auth/login params{}", jwAuthVO);
        return authService.login(jwAuthVO);
    }

}
