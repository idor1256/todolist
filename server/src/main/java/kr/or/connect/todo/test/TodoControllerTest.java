package kr.or.connect.todo.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoControllerTest {
	@Autowired
	WebApplicationContext wac;
	MockMvc mvc;

	@Before
	public void setUp(){
		this.mvc = webAppContextSetup(this.wac)
				.alwaysDo(print(System.out))
				.build();
	}

	//Select Test
	@Test
	public void shouldSelectAll() throws Exception{		
		mvc.perform(
				get("/api/todos/list-todo/All")
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk());
	}

	@Test
	public void shouldSelectActive() throws Exception{		
		mvc.perform(
				get("/api/todos/list-todo/Active")
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk());
	}

	@Test
	public void shouldSelectCompleted() throws Exception{		
		mvc.perform(
				get("/api/todos/list-todo/Completed")
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk());
	}

	//Count Test
	public void shouldCount() throws Exception{		
		mvc.perform(
				get("/api/todos/count-todo")
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk());
	}

	//Insert Test	
	@Test
	public void shouldInsert() throws Exception{
		String requestBody = "{\"todo\":\"ddd\"}";
		
		mvc.perform(
				post("/api/todos/new-todo")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody)
				)
				.andExpect(status().isCreated());
	}

	//Update Test
	@Test
	public void shouldChangeActive() throws Exception{
		mvc.perform(
				put("/api/todos/change-active/55")
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isNoContent());
	}

	@Test
	public void shouldChangeCompleted() throws Exception{
		mvc.perform(
				put("/api/todos/change-completed/55")
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isNoContent());
	}

	//Delete Test
	@Test
	public void shouldDelete() throws Exception{
		mvc.perform(
				delete("/api/todos/12")
					.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isNoContent());
	}

	@Test
	public void shouldDeleteCompleted() throws Exception{
		mvc.perform(
				delete("/api/todos/delete-completed")
					.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isNoContent());
	}

}
