package kr.or.connect.todo.domain;

public class Todo {
	//Variables
	private int id;
	private String todo;
	private boolean completed;
	private String date;

	//Generators
	Todo(){
		
	}

	Todo(String todo){
		this.todo = todo;
	}

	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}


	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
