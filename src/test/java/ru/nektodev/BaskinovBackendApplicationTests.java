package ru.nektodev;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.nektodev.baskinov.BaskinovBackendApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BaskinovBackendApplication.class)
@WebAppConfiguration
public class BaskinovBackendApplicationTests {

	@Test
	public void contextLoads() {
	}

}
