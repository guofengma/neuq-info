package cn.hang.neuq.base;

import cn.hang.neuq.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lihang15
 * @description
 * @create 2019-01-22 22:39
 **/
@Service
public class BaseService {
    @Autowired
    protected SessionUtils sessionUtils;

}
