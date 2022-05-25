package nus.project.BeerMarket;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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


@SpringBootTest
@AutoConfigureMockMvc
class BeerMarketApplicationTests {

	private static Logger logger = LoggerFactory.getLogger(BeerMarketApplicationTests.class);

	@Autowired
	private MockMvc mvc;

	//Test Start
	@BeforeClass
    public static void setup() {
        logger.info("startup - creating DB connection");
    }

	//Test End
    @AfterClass
    public static void tearDown() {
        logger.info("closing DB connection");
    }

	//Test 1 (pass)
	@Test
	void shouldDirectToStartPage() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/");
           mvc.perform(req).andDo(print())
                    .andExpect(view().name("index")).andReturn();
	}

	//Test 2 , also welcome page (pass)
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

	//Test 3 (pass)
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

	//Test 4 (pass)
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

	//Test 5 (pass)
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

	//Test 6 (pass)
	@Test
	void ahouldFindTextInPage() throws Exception {
		MockHttpSession sess = new MockHttpSession();
		RequestBuilder req = MockMvcRequestBuilders.get("/welcome").session(sess);
		
		mvc.perform(req).andDo(print()).andExpect(result -> assertTrue(
				result.getResponse().getContentAsString()
						.contains("Home")));
	}

	//Test 7 (pass)
	@Test
	void ahouldNotFindTextInPage() throws Exception {
		MockHttpSession sess = new MockHttpSession();
		RequestBuilder req = MockMvcRequestBuilders.get("/about").session(sess);
		
		mvc.perform(req).andDo(print()).andExpect(result -> assertFalse(
				result.getResponse().getContentAsString()
						.contains("Login")));
	}

    //Test 8 (pass)
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

    @Test
    void shouldGetPostById() {
        /* 
        pls fill in the /post/postid --> the postid you want to test so that it will pass
        .get() if GetMapping; .post if PostMapping
        if PostMapping, then it's contentType() and not accept()
        so in here, we are building the req
        */ 
        RequestBuilder req = MockMvcRequestBuilders.get("/post/1")
            .accept(MediaType.TEXT_HTML_VALUE);
        // .get() if GetMapping; .post if PostMapping
        // if PostMapping, then it's contentType() and not accept()
        // so in here, we are building the req 

        MvcResult result = null;

        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot call controller", ex);
            return;
        }

        String payload = null;
        // this payload is a modelandview because that is what you are returning
        // so if you print this payload, it should print the html page you want

        try {
            payload = result.getResponse().getContentAsString();
            System.out.println(">>>>> payload: " + payload);
        } catch (Exception ex) {
            fail("cannot get payload", ex);
            return;
        }

        assertTrue(payload.contains("Description"));

    }

}

