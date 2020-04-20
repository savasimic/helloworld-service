package xyz.infiniteloop;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import xyz.infiniteloop.model.Hello;

/**
 * @author Sava Simic (sava.simic@gmail.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HelloWorldServiceApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String USERNAME = "user";
    private static final String PASSWORD = "user123!";
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123!";

    @Test
    public void testHello() {
        Hello response = this.restTemplate.getForObject("http://localhost:5000/hello", Hello.class);
        assertEquals(1, response.id());
        assertEquals("Hello, Stranger! Contact us: sava.simic@gmail.com!", response.content());
    }

    @Test
    public void testHelloWithUsername() {
        Hello response = this.restTemplate.getForObject("http://localhost:5000/hello?name=Sava", Hello.class);
        assertEquals(2, response.id());
        assertEquals("Hello, Sava! Contact us: sava.simic@gmail.com!", response.content());
    }

    @Test
    public void testUser1() {
        Hello response = this.restTemplate.withBasicAuth(USERNAME, PASSWORD).getForObject("http://localhost:5000/user", Hello.class);
        assertEquals(1, response.id());
        assertEquals("Hello, user! This is user page, your roles are: ROLE_USER!", response.content());
    }

    @Test
    public void testUser1WithAdmin() {
        Hello response = this.restTemplate.withBasicAuth(ADMIN_USERNAME, ADMIN_PASSWORD).getForObject("http://localhost:5000/user", Hello.class);
        assertEquals(2, response.id());
        assertEquals("Hello, admin! This is user page, your roles are: ROLE_ADMIN,ROLE_USER!", response.content());
    }

    @Test
    public void testAdmin() {
        Hello response = this.restTemplate.withBasicAuth(ADMIN_USERNAME, ADMIN_PASSWORD).getForObject("http://localhost:5000/admin", Hello.class);
        assertEquals(1, response.id());
        assertEquals("Hello, admin! This is admin page, your roles are: ROLE_ADMIN,ROLE_USER!", response.content());
    }
}
