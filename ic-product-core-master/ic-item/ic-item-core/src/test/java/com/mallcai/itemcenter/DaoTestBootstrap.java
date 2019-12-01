package com.mallcai.itemcenter;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * TestConfiguration
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/17 14:11<br/>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DaoTestConfiguration.class)
@Transactional
@Rollback
@ActiveProfiles("test")
public class DaoTestBootstrap {
}
