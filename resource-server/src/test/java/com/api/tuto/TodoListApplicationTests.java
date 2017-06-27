package com.api.tuto;

import com.api.tuto.domain.Task;
import com.api.tuto.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TodoListApplication.class)
@WebIntegrationTest
public class TodoListApplicationTests {

	private RestTemplate restTemplate = new RestTemplate();


	@Autowired
	private TaskRepository taskRepository;
	
	@Test
	public void should_list_all_tasks() throws URISyntaxException {
		List tasks = restTemplate.getForObject(new URI("http://localhost:8080/api/tasks"), List.class);
		assertThat(tasks).extracting("content").contains("Install Java 8 and Maven 3","Write documentation", "Deploy Todolist API", "A task  number 3");
		assertThat(taskRepository.findAll()).extracting("content").containsSubsequence("Install Java 8 and Maven 3","Write documentation", "Deploy Todolist API", "A task  number 3");
	}
	
	@Test
	public void should_list_one_tasks() throws URISyntaxException {
		Task todoListDeploy = restTemplate.getForEntity(new URI("http://localhost:8080/api/tasks/10"), Task.class).getBody();
		assertThat(todoListDeploy.getContent()).isEqualTo("Deploy Todolist API");
	}
	
	@Test
	public void should_add_one_new_task() throws URISyntaxException {
		Task aNewTask = newTask("My new Task");
		HttpEntity<Object> request = new HttpEntity<>(aNewTask);

		ResponseEntity<Object> response = restTemplate.postForEntity(new URI("http://localhost:8080/api/tasks"),request, Object.class);
		assertThat(response.getStatusCode()).isEqualTo(CREATED);
		assertThat(taskRepository.findAll()).extracting("content").containsSubsequence("My new Task");
	}
	
	private Task newTask(String content) {
		Task aNewTask = new Task();
		aNewTask.setContent(content);
		aNewTask.setCompleted(false);
		aNewTask.setCreatedAt(new Date());
		return aNewTask;
	}

	@Test
	public void should_delete_one_existing_task() throws URISyntaxException {
		ResponseEntity<Object> response = restTemplate.exchange(new URI("http://localhost:8080/api/tasks/3"), DELETE, null, Object.class);
		assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);
		assertThat(taskRepository.findAll()).extracting("content").doesNotContain("Configure your IDE");
		
	}

}
