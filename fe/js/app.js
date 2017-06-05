(function (window) {
	/*
	 * Variables
	 **/
	var $newTodo = $('.new-todo');
	var $todoList = $('.todo-list');
	var $todoCount = $('.todo-count');
	var $toggle = $('.toggle:checkbox');
	var $todoDestroy = $('.destroy');
	var $filters = $('.filters');

	var liStr = '<li id="';
	var addTodoStr = '">'+'<div class="view">'+'<input class="toggle" type="checkbox">'+'<label>';
	var addTodoStrEnd = '</label>'+'<button class="destroy"></button>'+'</div>'+'<input class="edit">'+'</li>';
	var contentTypeStr = 'application/json; charset=UTF-8';


	/*
	 * Methods
	 **/
	//TASK 1. Insert Todo
	$newTodo.keydown(function(key){
		if(key.keyCode == 13){
			if($newTodo.val()!=''){
				var newContent = $newTodo.val();
				if($.trim(newContent).length != 0){	//Check Todo is Not Null
					$.ajax({	//Send Insert New Todo Request
						url : '/api/todos/new-todo',
						method: "post",
						dataType : 'json',
						data : JSON.stringify({
								todo : newContent
							}),
						processData : true,
						contentType : contentTypeStr,
						success : function(data){
							var prependStr = liStr+data.id+addTodoStr+data.todo+addTodoStrEnd;
							$todoList.prepend(prependStr);
							CountTodo();	//Refresh Number of Uncompleted Todos
						}
					})
				}
			}
		}
	})

	//TASK 2. Show Todo List
	LoadList('/api/todos/list-todo/All');

	//TASK 3. Change Todo Completed when Checkbox Clicked
	$todoList.on('change', 'input.toggle',  function(data){
		if($(this).is(":checked")){	//if checkbox is 'checked'
			$(this).parents('li').addClass('completed');
			ChangeCompleted($(this).parents('li').attr('id'));
		}
		else{	//if checkbox is 'unchecked'
			$(this).parents('li').removeClass('completed');
			ChangeActive($(this).parents('li').attr('id'));
		}
	})
	
	//TASK 4. Todo Delete when Delete Button Clicked
	$todoList.on('click', 'button.destroy', function(){
		$.ajax({	//Send Delete Item Request to Server
			url:'/api/todos/'+$(this).parents('li').attr('id'),
			method:"delete",
			processData:true,
			contentType : "application/json; charset=UTF-8",
			success: function(){
				CountTodo();
			}
		})
		$(this).parents('li').remove();
	})

	//TASK 6. Todo List Filtering when Filterring Button Clicked
	$filters.on('click', 'a', function(){
		//Set class to Selected Button
		$(this).parent().siblings().children('a').removeClass('selected');
		$(this).addClass('selected');
		//Show Todo List Request
		var urls = '/api/todos/list-todo/'+$(this).text();
		LoadList(urls);
	})

	//TASK 7. Completed Todo Delete when Clear completed Button Clicked
	$('.footer').on('click', 'button.clear-completed', function(){
		$.ajax({	//Send Delete Items Request to Server
			url:'/api/todos/delete-completed',
			method:"delete",
			processData:true,
			contentType : "application/json; charset=UTF-8",
			success: function(){
				var urls = '/api/todos/list-todo/'+$('.selected').text();
				LoadList(urls);
			}
		})
	})


	/**
	 *Functions
	 */

	//Load Todo List from Server
	function LoadList(urls){
		$.ajax({
			url:urls,
			method:"get",
			processData:true,
			contentType : contentTypeStr,
			success : function(data){
				$todoList.children('li').remove();
				for(var i in data){
					var appendStr = liStr+data[i].id+addTodoStr+data[i].todo+addTodoStrEnd;
					$todoList.append(appendStr);
					if(data[i].completed){
						$todoList.find('li:last').addClass('completed').find('.toggle').attr('checked', 'checked');
					}
				}
				CountTodo();
			}
		})
	}

	//Request Server to Change Completed 'completed'
	function ChangeCompleted(id){
		$.ajax({
			url:'/api/todos/change-completed/'+id,
			method:"put",
			processData:true,
			contentType : contentTypeStr,
			success: function(){
				CountTodo();
			}
		})
	}

	//Request Server to Change Completed 'uncompleted'
	function ChangeActive(id){
		$.ajax({
			url:'/api/todos/change-active/'+id,
			method:"put",
			processData:true,
			contentType : contentTypeStr,
			success: function(){
				CountTodo();
			}
		})
	}

	//TASK 5. Get Number of Uncompleted Todo
	function CountTodo(){
		$.ajax({
			url:'/api/todos/count-todo',
			method:"get",
			processData:true,
			contentType : contentTypeStr,
			success : function(data){
				$todoCount.children('strong').text(data);
			}
		})
	}
})(window);
