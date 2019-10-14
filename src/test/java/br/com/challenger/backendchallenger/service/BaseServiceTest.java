package br.com.challenger.backendchallenger.service;

import br.com.challenger.backendchallenger.BackendChallengerApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = { BackendChallengerApplication.class})
@ActiveProfiles("test")
abstract class BaseServiceTest {
}
