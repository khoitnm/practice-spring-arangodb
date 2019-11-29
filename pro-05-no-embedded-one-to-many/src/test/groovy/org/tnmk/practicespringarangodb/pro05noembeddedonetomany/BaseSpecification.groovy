package org.tnmk.practicespringarangodb.pro05noembeddedonetomany

import org.junit.Ignore
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.tnmk.practicespringarangodb.pro05noembeddedonetomany.Pro05NoEmbeddedOneToManyApplication
import spock.lang.Specification

@Ignore
@DirtiesContext
@SpringBootTest(classes = Pro05NoEmbeddedOneToManyApplication.class)
@ActiveProfiles("componenttest")
abstract class BaseSpecification extends Specification {

}