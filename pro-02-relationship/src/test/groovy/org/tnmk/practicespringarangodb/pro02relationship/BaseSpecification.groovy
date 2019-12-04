package org.tnmk.practicespringarangodb.pro02relationship

import org.junit.Ignore
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.tnmk.practicespringarangodb.pro02relationship.Pro02RelationshipApplication
import spock.lang.Specification

@Ignore
@DirtiesContext
@SpringBootTest(classes = Pro02RelationshipApplication.class)
@ActiveProfiles("componenttest")
abstract class BaseSpecification extends Specification {

}