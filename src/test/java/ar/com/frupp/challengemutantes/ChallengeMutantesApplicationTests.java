package ar.com.frupp.challengemutantes;

import ar.com.frupp.challengemutantes.api.controller.MutantController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChallengeMutantesApplicationTests {

	@Autowired
	private MutantController controller;

	@Test
	@DisplayName("SpringBoot app context loaded")
	void contextLoads() {
		Assertions.assertNotNull(controller);
	}

}
