package cn.hang.neuq;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lihang15
 * @description
 * @create 2019-01-22 14:38
 **/
public class RestTestmplateTest {
    private RestTemplate restTemplate;

    @Before
    public void init() {
        restTemplate = new RestTemplate();
    }

    @lombok.Data
    static class InnerRes {
        private Status status;
        private Data result;
    }

    @lombok.Data
    static class Status {
        int code;
        String msg;
    }

    @lombok.Data
    static class Data {
        long id;
        String theme;
        String title;
        String dynasty;
        String explain;
        String content;
        String author;
    }

    @Test
    public void testGet() {
        // 使用方法一，不带参数
        String url = "https://story.hhui.top/detail?id=666106231640";
        InnerRes res = restTemplate.getForObject(url, InnerRes.class);
        System.out.println(res);


        // 使用方法一，传参替换
        url = "https://story.hhui.top/detail?id={?}";
        res = restTemplate.getForObject(url, InnerRes.class, "666106231640");
        System.out.println(res);

        // 使用方法二，map传参
        url = "https://story.hhui.top/detail?id={id}";
        Map<String, Object> params = new HashMap<>();
        params.put("id", 666106231640L);
        res = restTemplate.getForObject(url, InnerRes.class, params);
        System.out.println(res);

        // 使用方法三，URI访问
        URI uri = URI.create("https://story.hhui.top/detail?id=666106231640");
        res = restTemplate.getForObject(uri, InnerRes.class);
        System.out.println(res);
    }

    @Test
    public void test1() {
        float a = (float) (409.65 / 162.5);
        System.out.println(a);
        float b = (float) Math.round((409.65 / 162.5 * 1000));
        System.out.println(b);
        float c = (float) Math.round((409.65 / 162.5 * 10000)) / 10000;
        System.out.println(c);
    }

}

