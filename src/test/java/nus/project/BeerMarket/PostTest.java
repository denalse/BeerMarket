package nus.project.BeerMarket;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import nus.project.BeerMarket.model.Post;
import nus.project.BeerMarket.repository.PostRepository;

import nus.project.BeerMarket.model.Post;
import nus.project.BeerMarket.repository.PostRepository;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PostTest {

    @MockBean
    private PostRepository postRepo;

    @Mock
    private ResultSet rs;

    @Autowired
    private JdbcTemplate temp;

    @Autowired
    private MockMvc mockMvc;


    //Test Start (pass)
    @BeforeEach
    void setUp() {
        final String SQL_INSERT_POST =
        "insert into post (photo, comment, poster, mediatype) values (null, 'hello', 'Tester', 'image/png')";
    
        temp.update(SQL_INSERT_POST);
    }

    //Test End (pass)
    @AfterEach
    void removeSetUp() {
        final String query = "delete from post where poster = 'Tester'";
        temp.update(query);
    }
    
    //Test 10 (pass)
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

    //Test 11 (pass)
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

	//Test 12 (pass)
	@Test
	void insertPost() throws SQLException {
		Post post = new Post();
		post.setImage(null);
		post.setComment("test");
		post.setPoster("Hello");
		post.setImageType("image/png");
		post.setPostId(1);
		assertSame(post.getComment(), "test");
    }

    // Test 15 ()
    @Test
    void getPost() throws Exception {

        MockMultipartFile image = new MockMultipartFile("image", "Hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());

        RequestBuilder req = MockMvcRequestBuilders.post("/post")
            .accept(MediaType.MULTIPART_FORM_DATA_VALUE)
            .param("image", "image")
            .param("comment", "test1")
            .param("poster", "test1");
        
        mockMvc.perform(req).andDo(print())
            .andExpect(status().is4xxClientError());
    }


    // Test 14 (pass)
    @Test
	void shouldGetRestPostId() throws Exception {
        Integer postId = 1;
        RequestBuilder req = MockMvcRequestBuilders.get("/post/" + postId + "/image");
        
        // Optional<Post> opt = postRepo.getPostById(postId);
        // Post post = opt.get();

        MvcResult mvcResult = null;


			 mockMvc.perform(req).andDo(print())
							.andExpect(status().isNotFound());
                            // .andExpect(model().attribute("post", "opt.get()"))
							// .andExpect(view().name("post"))
							// .andReturn();

    }

}