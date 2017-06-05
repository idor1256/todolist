package kr.or.connect.todo.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {
	//Variables
		private TodoDao dao;

		//Generator
		TodoService(TodoDao dao){
			this.dao = dao;
		}

		//Select
		public Collection<Todo> findAll(){
			return dao.selectAll();
		}

		public Collection<Todo> findActive(){
			return dao.selectActive();
		}

		public Collection<Todo> findCompleted(){
			return dao.selectComplete();
		}

		//Count
			public Integer countTodos(){
				return dao.countTodo();
			}

		//Insert
		public Todo create(Todo todo){
			Integer id = dao.insert(todo);
			todo.setId(id);
			return todo; 
		}

		//Delete
		public boolean delete(Integer id){
			int affected = dao.deleteById(id);
			return affected == 1;
		}

		public boolean deleteCompleted(){
			int affected = dao.deleteCompleted();
			return affected == 1;
		}

		//Update
		public boolean updateCompleted(Integer id){
			int affected = dao.updateCompleted(id);
			return affected == 1;
		}

		public boolean updateActive(Integer id){
			int affected = dao.updateActive(id);
			return affected == 1;
		}
}
