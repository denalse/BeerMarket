package nus.project.BeerMarket;

import static org.junit.Assert.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import nus.project.BeerMarket.model.Post;
import nus.project.BeerMarket.repository.PostRepository;

@SpringBootTest
public class PostTest {

    @MockBean
    private PostRepository postRepo;

    @Test
    void contextLoads() {

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
}
