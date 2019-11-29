package org.tnmk.practicespringarangodb.pro03enumfield

import org.junit.Ignore
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.tnmk.practicespringarangodb.pro03enumfield.Pro03EnumFieldApplication
import spock.lang.Specification

@Ignore
@DirtiesContext
@SpringBootTest(classes = Pro03EnumFieldApplication.class)
@ActiveProfiles("componenttest")
abstract class BaseSpecification extends Specification {

}