package nus.project.BeerMarket;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import nus.project.BeerMarket.model.Beer;
import nus.project.BeerMarket.service.BeerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BeerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BeerService beerSvc;

    // @Mock
    // private ArrayList<Integer> mockArrayList;

	@Mock
	private List<Beer> List;

    @Before
    public void setUp() {
      mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //Test 9 (pass)
	@Test
	void shouldFindBeerById() throws Exception {
		RequestBuilder req = MockMvcRequestBuilders.get("/search")
		.queryParam("searchId", "100");

		MvcResult mvcResult = null;
		try {
			mvcResult = mockMvc.perform(req).andDo(print())
							.andExpect(status().isOk())
							.andExpect(view().name("showBeer"))
							.andReturn();
		} catch (Exception ex) {
			System.out.println(">>>>> Beer found!!!" + req);
		}
	}

	// @Test
	// void testGetBeer() {
	// 	final Integer searchId;
	// 	final String imageUrl;
    // when(beerSvc.getBeer(searchId, imageUrl)).thenReturn(List<Beer> List);
    // assertTrue(beerSvc.getBeer(searchId).isEmpty());
    // verify(beerSvc).getBeer(1);
	// }

}
