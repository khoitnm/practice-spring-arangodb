package org.tnmk.practicespringarangodb.pro01simpleentity

import org.junit.Ignore
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.tnmk.practicespringarangodb.pro01simpleentity.Pro01SimpleEntityApplication
import spock.lang.Specification

@Ignore
@DirtiesContext
@SpringBootTest(classes = Pro01SimpleEntityApplication.class)
@ActiveProfiles("componenttest")
abstract class BaseSpecification extends Specification {

}