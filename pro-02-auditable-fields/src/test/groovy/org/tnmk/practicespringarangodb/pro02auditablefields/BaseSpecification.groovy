package org.tnmk.practicespringarangodb.pro02auditablefields

import org.junit.Ignore
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.tnmk.practicespringarangodb.pro02auditablefields.Application
import spock.lang.Specification

@Ignore
@DirtiesContext
@SpringBootTest(classes = Application.class)
@ActiveProfiles("componenttest")
abstract class BaseSpecification extends Specification {

}