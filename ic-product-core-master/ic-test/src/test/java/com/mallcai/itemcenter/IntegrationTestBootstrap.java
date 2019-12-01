package com.mallcai.itemcenter;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.mallcai.itemcenter.utils.JsonMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * com.mallcai.itemcenter.IntergrationTestBootstrap
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 20:12<br/>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = IntegrationTestConfiguration.class)
@Transactional
@Rollback
@ActiveProfiles("integration")
@Sql(scripts = "classpath:data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class IntegrationTestBootstrap {
    private static final String inputJsonPath = "data/api/input";
    private static final String outputJsonPath = "data/api/output";

    protected String loadOutputJson(String name) throws IOException {
        String data = loadJson(name, outputJsonPath);
        return JsonMapper.nonEmptyMapper().treeFromJson(data).toString();
    }

    protected String loadInputJson(String name) throws IOException {
        return loadJson(name, inputJsonPath);
    }

    protected String loadJson(String name, String path) throws IOException {
        if (!name.startsWith("/")) {
            path += "/";
        }
        path += name;
        if (!name.toLowerCase().endsWith(".json")) {
            path += ".json";
        }
        return Resources.toString(Resources.getResource(path), Charsets.UTF_8).trim();
    }
}
