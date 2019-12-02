package org.tnmk.practicespringarangodb.pro01simpleentity;

import com.arangodb.ArangoDB;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.tnmk.practicespringarangodb.common.arangodb.ArangoDBContainer;
import org.tnmk.practicespringarangodb.pro01simpleentity.sample.story.PersonService;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("componenttest")
public class ArangoDBContainerTest {

	@Rule
	public ArangoDBContainer arangoDBContainer = new ArangoDBContainer().withoutAuthentication();

	@Autowired
	private PersonService personService;

	@Test
	public void testGetter() {
		Assert.assertNotNull(arangoDBContainer.getDatabase());
		personService.findAll();
	}

	@Test(expected = Exception.class)
	public void testGetClientFail(){
		arangoDBContainer.stop();
		arangoDBContainer.getArangoDB();
	}
}