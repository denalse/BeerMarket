package nus.project.BeerMarket;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

import nus.project.BeerMarket.model.Post;
import nus.project.BeerMarket.repository.PostRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PostTest {

    @MockBean
    private PostRepository postRepo;

    @Mock
    private ResultSet rs;

    @Autowired
    private JdbcTemplate temp;


    //Test 14 (pass)
    @BeforeEach
    void setUp() {
        final String SQL_INSERT_POST =
        "insert into post (photo, comment, poster, mediatype) values (null, 'hello', 'Tester', 'image/png')";
    
        temp.update(SQL_INSERT_POST);
    }

    //Test 13 (pass)
    @AfterEach
    void removeSetUp() {
        final String query = "delete from post where poster = 'Tester'";
        temp.update(query);
    }
    
    //Test 12 (pass)
    @Test
    public void testPopulate() throws SQLException {
        // Define the behavior of the mock for each getString method's call
        Mockito.when(rs.getBytes("photo")).thenReturn(null);
        Mockito.when(rs.getString("comment")).thenReturn("test");
        Mockito.when(rs.getString("poster")).thenReturn("Tester1");
        Mockito.when(rs.getString("mediatype")).thenReturn("image/png");
        Mockito.when(rs.getInt("post_id")).thenReturn(1);


        // Launch the method against your mock
        Post post = new Post();
        post = Post.populate(rs);

        // Check the result
        Assert.assertNotNull(post);
        Assert.assertEquals(null, post.getImage());
        Assert.assertEquals("test", post.getComment());
        Assert.assertEquals("Tester1", post.getPoster());
        Assert.assertEquals("image/png", post.getImageType());
        Assert.assertEquals((Integer)1, post.getPostId());
    }

    //Test 13 (pass)
    @Test
    public void testShow() throws SQLException {
        // Define the behavior of the mock for each getString method's call
        Mockito.when(rs.getBytes("photo")).thenReturn(null);
        Mockito.when(rs.getString("comment")).thenReturn("test");
        Mockito.when(rs.getString("poster")).thenReturn("Tester1");
        Mockito.when(rs.getString("mediatype")).thenReturn("image/png");
        Mockito.when(rs.getInt("post_id")).thenReturn(1);


        // Launch the method against your mock
        Post post = new Post();
        post = Post.show(rs);

        // Check the result
        Assert.assertNotNull(post);
        Assert.assertEquals(null, post.getImage());
        Assert.assertEquals("test", post.getComment());
        Assert.assertEquals("Tester1", post.getPoster());
        Assert.assertEquals("image/png", post.getImageType());
        Assert.assertEquals((Integer)1, post.getPostId());
    }

    // @Test
	// void insertPostShouldFail() {
	// 	try {
	// 		postRepo.insertPost(Post post);
	// 	} catch (Exception ex) {
	// 		assertTrue(true);
	// 		return;
	// 	}

	// 	fail("Did not throw BFFException when email exists");
	// }

    // @Test
    // void getPostById() throws SQLException {
    //     Optional<Post> post = postRepo.getPostById(2);
    //     Assertions.assertEquals(2, post.get());
    // }

	//Test 13 (pass)
	@Test
	void insetPost() throws SQLException {
		Post post = new Post();
		post.setImage(null);
		post.setComment("test");
		post.setPoster("Hello");
		post.setImageType("image/png");
		post.setPostId(1);
		assertSame(post.getComment(), "test");
    }

	// @Test
	// void shouldNotGetPost() {

	// }
}
