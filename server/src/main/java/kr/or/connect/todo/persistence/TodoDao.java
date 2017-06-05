package kr.or.connect.todo.persistence;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.todo.domain.Todo;

@Repository
public class TodoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Todo> rowMapper = BeanPropertyRowMapper.newInstance(Todo.class);
	
	//Generator
	public TodoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("todo")
				.usingGeneratedKeyColumns("id")
				.usingColumns("todo");
	}
	
	//Select
		public List<Todo> selectAll(){
			Map<String, Object> params = Collections.emptyMap();
			String selectAll = TodoSqls.getSelectAll();
			return jdbc.query(selectAll, params, rowMapper);
		}

		public List<Todo> selectActive(){
			Map<String, Object> params = Collections.emptyMap();
			String selectActive = TodoSqls.getSelectActive();
			return jdbc.query(selectActive, params, rowMapper);
		}

		public List<Todo> selectComplete(){
			Map<String, Object> params = Collections.emptyMap();
			String selectCompleted = TodoSqls.getSelectCompleted();
			return jdbc.query(selectCompleted, params, rowMapper);
		}

		//Count
		public Integer countTodo(){
			Map<String, Object> params = Collections.emptyMap();
			String countTodo = TodoSqls.getCountTodos();
			return jdbc.queryForObject(countTodo, params, Integer.class);
		}

		//Insert
		public Integer insert(Todo todo){
			SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
			return insertAction.executeAndReturnKey(params).intValue();
		}

		//Delete
		public int deleteById(Integer id){
			Map<String, ?> params = Collections.singletonMap("id",  id);
			String deleteById = TodoSqls.getDeleteById();
			return jdbc.update(deleteById,  params);
		}

		public int deleteCompleted(){
			Map<String, ?> params = Collections.emptyMap();
			String deleteCompleted = TodoSqls.getDeleteCompleted();
			return jdbc.update(deleteCompleted, params);
		}

		//Update
		public int updateCompleted(Integer id){
			Map<String, ?> params = Collections.singletonMap("id",  id);
			String updateCompleted = TodoSqls.getUpdateComplete();
			return jdbc.update(updateCompleted, params);
		}

		public int updateActive(Integer id){
			Map<String, ?> params = Collections.singletonMap("id",  id);
			String updateActive = TodoSqls.getUpdateActive();
			return jdbc.update(updateActive, params);
		}
}
