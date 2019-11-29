package org.tnmk.practicespringarangodb.pro04embeddeditems

import org.junit.Ignore
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.tnmk.practicespringarangodb.pro04embeddeditems.Pro04EmbeddedItemsApplication
import spock.lang.Specification

@Ignore
@DirtiesContext
@SpringBootTest(classes = Pro04EmbeddedItemsApplication.class)
@ActiveProfiles("componenttest")
abstract class BaseSpecification extends Specification {

}