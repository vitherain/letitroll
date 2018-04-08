package io.letitroll.client;

import io.letitroll.client.category.SlowIntegrationTest;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Category(SlowIntegrationTest.class)
public abstract class BaseSpringTest {
}
