package hello;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private HomeController homeController;

    @Test
    public void shouldLoadContextWhenStarting() throws Exception {

    }

    @Test
    public void shouldLoadControllersWhenStarting() {
        assertThat(homeController).isNotNull();
    }
}