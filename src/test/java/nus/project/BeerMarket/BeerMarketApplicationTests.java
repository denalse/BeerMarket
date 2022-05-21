package nus.project.BeerMarket;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import nus.project.BeerMarket.model.Post;
import nus.project.BeerMarket.repository.PostRepository;

@SpringBootTest
@AutoConfigureMockMvc
class BeerMarketApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PostRepository postRepo;

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
	void ahouldFindTextInPage() throws Exception {
		MockHttpSession sess = new MockHttpSession();
		RequestBuilder req = MockMvcRequestBuilders.get("/welcome").session(sess);
		
		mvc.perform(req).andDo(print()).andExpect(result -> assertTrue(
				result.getResponse().getContentAsString()
						.contains("Home")));

	}

}
