package kr.or.connect.todo.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
	private final TodoService service;

	@Autowired
	public TodoController(TodoService service){
		this.service = service;
	}

	//Select
	@GetMapping("/list-todo/All")
	Collection<Todo> readList(){
		return service.findAll();
	}

	@GetMapping("/list-todo/Active")
	Collection<Todo> readActive(){
		return service.findActive();
	}

	@GetMapping("/list-todo/Completed")
	Collection<Todo> readCompleted(){
		return service.findCompleted();
	}

	//Count
	@GetMapping("/count-todo")
	Integer countTodo(){
		return service.countTodos();
	}

	//Insert Action
	@PostMapping("/new-todo")
	@ResponseStatus(HttpStatus.CREATED)
	Todo create(@RequestBody Todo todo){
		service.create(todo); 
		return todo;
	}

	//Delete Action
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Integer id){
		service.delete(id);
	}

	@DeleteMapping("/delete-completed")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteCompleted(){
		service.deleteCompleted();
	}

	//Update Action
	@PutMapping("/change-completed/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void changeCompleted(@PathVariable Integer id){
		service.updateCompleted(id);
	}

	@PutMapping("/change-active/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void changeActive(@PathVariable Integer id){
		service.updateActive(id);
	}
}
