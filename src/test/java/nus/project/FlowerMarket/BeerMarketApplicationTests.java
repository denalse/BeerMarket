package nus.project.FlowerMarket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nus.project.FlowerMarket.model.Post;

@SpringBootTest
class BeerMarketApplicationTests {

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
		System.out.println(p.getComment());
		System.out.println(p.getImageType());
		System.out.println(p.getPoster());
		System.out.println(p.getPostId());
		System.out.println(p.getImage());
	}

}
