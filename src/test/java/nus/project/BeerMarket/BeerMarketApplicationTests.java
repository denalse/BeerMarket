package nus.project.BeerMarket;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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


import nus.project.BeerMarket.model.Post;
import nus.project.BeerMarket.repository.PostRepository;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(JUnit4.class)
class BeerMarketApplicationTests {

	private static Logger logger = LoggerFactory.getLogger(BeerMarketApplicationTests.class);

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JdbcTemplate temp;

	@MockBean
	private PostRepository postRepo;

	@BeforeClass
    public static void setup() {
        logger.info("startup - creating DB connection");
    }

    @AfterClass
    public static void tearDown() {
        logger.info("closing DB connection");
    }

	//test for new post (passed)
	@Test
	void testNewPost(){
		Post p = new Post();
		p.setComment("test");
		p.setImage(null);
		p.setImageType("image/png");
		p.setPostId(1);
		p.setPoster("Hello");
		
	}

	//test to get values from the post (passed)
	@Test
	void testGetValuesPost(){
		Post p = new Post();
		p.setComment("test");
		p.setImage(null);
		p.setImageType("image/png");
		p.setPostId(1);
		p.setPoster("Hello");
		assertSame(p.getComment(), "test");
	}

	@Test
	void shouldNotGetPost() {

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

	// @Test
	// void shouldNotFindBeerById() throws Exception {
	// 	RequestBuilder req = MockMvcRequestBuilders.get("/market/{searchId}")
	// 			.sessionAttr("post", "abc");

	// 	MvcResult mvcResult = null;
	// 	try {
	// 		mvcResult = mvc.perform(req).andDo(print())
	// 						.andExpect(status().isOk())
	// 						.andExpect(view().name("showbeer"))
	// 						.andReturn();
	// 	} catch (Exception ex) {
	// 		System.out.println(">>>>> No such beer found!");
	// 	}
		
	// }

	// @Test
	// void shouldFindBeerById() throws Exception {
	// 	RequestBuilder req = MockMvcRequestBuilders.get("/market/{searchId}")
	// 	.sessionAttr("post", "100");

	// 	MvcResult mvcResult = null;
	// 	try {
	// 		mvcResult = mvc.perform(req).andDo(print())
	// 						.andExpect(status().isOk())
	// 						.andExpect(view().name("showbeer"))
	// 						.andReturn();
	// 	} catch (Exception ex) {
	// 		System.out.println(">>>>> Beer found!");
	// 	}
	// }




}
