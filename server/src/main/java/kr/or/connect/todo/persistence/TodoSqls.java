package kr.or.connect.todo.persistence;

public class TodoSqls {
	//Variables
	static final String DELETE_BY_ID =
			"DELETE FROM todo WHERE id= :id";
	static final String SELECT_ALL = "SELECT * FROM todo ORDER BY date DESC";
	static final String SELECT_ACTIVE = "SELECT * FROM todo WHERE completed=0 ORDER BY date DESC";
	static final String SELECT_COMPLETED = "SELECT * FROM todo WHERE completed=1 ORDER BY date DESC";
	static final String DELETE_COMPLETED = "DELETE FROM todo WHERE completed=1";
	static final String COUNT_TODOS = "SELECT COUNT(*) FROM todo WHERE completed=0";
	static final String UPDATE_COMPLETE=
			"UPDATE todo SET\n"
			+ "completed = 1\n"
			+ "WHERE id = :id";
	static final String UPDATE_ACTIVE=
			"UPDATE todo SET\n"
			+ "completed = 0\n"
			+ "WHERE id = :id";

	//Getters
	public static String getUpdateActive() {
		return UPDATE_ACTIVE;
	}

	public static String getDeleteById() {
		return DELETE_BY_ID;
	}

	public static String getSelectAll() {
		return SELECT_ALL;
	}

	public static String getSelectActive() {
		return SELECT_ACTIVE;
	}

	public static String getSelectCompleted() {
		return SELECT_COMPLETED;
	}

	public static String getDeleteCompleted() {
		return DELETE_COMPLETED;
	}

	public static String getCountTodos() {
		return COUNT_TODOS;
	}

	public static String getUpdateComplete() {
		return UPDATE_COMPLETE;
	}
}
