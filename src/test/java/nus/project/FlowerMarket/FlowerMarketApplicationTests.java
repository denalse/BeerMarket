package nus.project.FlowerMarket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nus.project.FlowerMarket.model.Post;

@SpringBootTest
class FlowerMarketApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testNewPost(){
		Post p = new Post();
		p.setComment("test");
		p.setImage(null);
		p.setImageType("image/png");
		p.setPostId(1);
		p.setPoster("Hello");
		
	}

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
