import React from "react";
import './TodoList.scss';
import TodoListItem from './TodoListItem'

// 셀프 바인딩을 할 건데, 인자들이 필요함.
//  todos: 배열(안에 있는 요소를 받음) , onRemove: 지우는 이벤트 핸들러, onToggle
const TodoList = ({ todos, onRemove, onToggle }) => {
    return(
        <div className="TodoList">
            { todos.map(todo => (
                <TodoListItem todo={ todo } key={ todo.id }
                            onRemove={ onRemove } onToggle={ onToggle }/>
            ))}
        </div>
    )
}

export default TodoList