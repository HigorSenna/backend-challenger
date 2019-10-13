package br.com.challenger.backendchallenger.service;

import br.com.challenger.backendchallenger.BackendChallengerApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BackendChallengerApplication.class})
@ActiveProfiles("test")
public abstract class BaseServiceTest {
}
