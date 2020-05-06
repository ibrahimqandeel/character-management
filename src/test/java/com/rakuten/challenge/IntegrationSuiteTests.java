package com.rakuten.challenge;

import com.rakuten.challenge.controller.CharacterControllerIntegrationTest;
import com.rakuten.challenge.controller.ClassControllerIntegrationTest;
import com.rakuten.challenge.controller.RaceControllerIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({RaceControllerIntegrationTest.class, ClassControllerIntegrationTest.class, CharacterControllerIntegrationTest.class})

public class IntegrationSuiteTests {
}
