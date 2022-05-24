package nus.project.BeerMarket;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.junit.*;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
// @RunWith(JUnit4.class)
class BeerMarketApplicationTests {

	private static Logger logger = LoggerFactory.getLogger(BeerMarketApplicationTests.class);

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JdbcTemplate temp;


	@BeforeClass
    public static void setup() {
        logger.info("startup - creating DB connection");
    }

    @AfterClass
    public static void tearDown() {
        logger.info("closing DB connection");
    }


	@Test
	void ahouldFindTextInPage() throws Exception {
		MockHttpSession sess = new MockHttpSession();
		RequestBuilder req = MockMvcRequestBuilders.get("/welcome").session(sess);
		
		mvc.perform(req).andDo(print()).andExpect(result -> assertTrue(
				result.getResponse().getContentAsString()
						.contains("Home")));
	}

	@Test
	void ahouldNotFindTextInPage() throws Exception {
		MockHttpSession sess = new MockHttpSession();
		RequestBuilder req = MockMvcRequestBuilders.get("/about").session(sess);
		
		mvc.perform(req).andDo(print()).andExpect(result -> assertFalse(
				result.getResponse().getContentAsString()
						.contains("Login")));
	}






}
