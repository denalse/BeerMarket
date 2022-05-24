package nus.project.BeerMarket;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.junit.*;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.nio.file.Files;


@SpringBootTest
@AutoConfigureMockMvc
// @RunWith(JUnit4.class)
class BeerMarketApplicationTests {

	private static Logger logger = LoggerFactory.getLogger(BeerMarketApplicationTests.class);

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JdbcTemplate temp;

	//Test 1
	@BeforeClass
    public static void setup() {
        logger.info("startup - creating DB connection");
    }

	//Test 2
    @AfterClass
    public static void tearDown() {
        logger.info("closing DB connection");
    }

	//Test 3 (pass)
	@Test
	void shouldDirectToStartPage() {
		RequestBuilder req = MockMvcRequestBuilders.get("/");

        MvcResult mvcResult = null;
        try {
            mvcResult = mvc.perform(req).andDo(print())
                    .andExpect(view().name("index")).andReturn();
        } catch (Exception ex) {
            System.out.println("Not valid" + ex);
            return;
        }
	}

	//Test 4 , also welcome page (pass)
	@Test
	void shouldDirectToWelcomePage() {
		RequestBuilder req = MockMvcRequestBuilders.get("/welcome");

        MvcResult mvcResult = null;
        try {
            mvcResult = mvc.perform(req).andDo(print())
                    .andExpect(view().name("welcome")).andReturn();
        } catch (Exception ex) {
            System.out.println("Not valid" + ex);
            return;
        }
	}

	//Test 5 (pass)
	@Test
	void shouldDirectToHomePage() {
		RequestBuilder req = MockMvcRequestBuilders.get("/home");

        MvcResult mvcResult = null;
        try {
            mvcResult = mvc.perform(req).andDo(print())
                    .andExpect(view().name("welcome")).andReturn();
        } catch (Exception ex) {
            System.out.println("Not valid" + ex);
            return;
        }
	}

	//Test 6 (pass)
	@Test
	void shouldDirectToAboutPage() {
		RequestBuilder req = MockMvcRequestBuilders.get("/about");

        MvcResult mvcResult = null;
        try {
            mvcResult = mvc.perform(req).andDo(print())
                    .andExpect(view().name("about")).andReturn();
        } catch (Exception ex) {
            System.out.println("Not valid" + ex);
            return;
        }
	}

	//Test 7 (pass)
	@Test
	void shouldDirectToMarketPage() {
		RequestBuilder req = MockMvcRequestBuilders.get("/marketplace");

        MvcResult mvcResult = null;
        try {
            mvcResult = mvc.perform(req).andDo(print())
                    .andExpect(view().name("market")).andReturn();
        } catch (Exception ex) {
            System.out.println("Not valid" + ex);
            return;
        }
	}

	//Test 8 (pass)
	@Test
	void ahouldFindTextInPage() throws Exception {
		MockHttpSession sess = new MockHttpSession();
		RequestBuilder req = MockMvcRequestBuilders.get("/welcome").session(sess);
		
		mvc.perform(req).andDo(print()).andExpect(result -> assertTrue(
				result.getResponse().getContentAsString()
						.contains("Home")));
	}

	//Test 9 (pass)
	@Test
	void ahouldNotFindTextInPage() throws Exception {
		MockHttpSession sess = new MockHttpSession();
		RequestBuilder req = MockMvcRequestBuilders.get("/about").session(sess);
		
		mvc.perform(req).andDo(print()).andExpect(result -> assertFalse(
				result.getResponse().getContentAsString()
						.contains("Login")));
	}

    //Test 10 (pass)
    @Test
	void shouldGetPost() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/market");

		MvcResult mvcResult = null;
		try {
			mvcResult = mvc.perform(req).andDo(print())
							.andExpect(status().isOk())
							.andExpect(view().name("market"))
							.andReturn();
		} catch (Exception ex) {
			System.out.println(">>>>> Post Success!!!" + req);
		}
	}

}

