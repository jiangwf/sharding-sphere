package io.shardingsphere.sharding.test;

import io.shardingsphere.sharding.unit.UnitApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试父类，其他模块的测试子类可继承
 * Created by weifeng.jiang on 2017-08-15 下午 3:31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnitApplication.class)
public class AppTest {

}
