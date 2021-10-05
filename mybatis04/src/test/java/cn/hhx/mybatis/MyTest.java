package cn.hhx.mybatis;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author hhxStellar
 * @date 2021/9/24-14:41
 */
public class MyTest {
    @Test
    public void test1() {
        String str = "1";
        System.out.println(str == "1");
    }

    @Test
    public void test2() {
        HashMap<String, String> map = new HashMap<>();
        map.put("hhh","3213");
        System.out.println(map.get("hhh"));
    }
}
