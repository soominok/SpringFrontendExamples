import React, { useState, useRef, useCallback } from 'react';

import TodoTemplate from "./todo_components/TodoTemplate";
import TodoInsert from "./todo_components/TodoInsert";
import TodoList from "./todo_components/TodoList";

import CounterContainer from "./asynchronous/CounterContainer"

const App = () => {
    // It's for Todo App
    const [todos, setTodos] = useState([
        {
            id: 1,
            text: 'ReactJS',
            checked: true
        },
        {
            id: 2,
            text: 'Git',
            checked: false
        },
        {
            id: 3,
            text: 'Golang',
            checked: false
        },
    ])
    // 값을 넣는다면 기준 값이 필요함
    const nextId = useRef(4)

    const onInsert = useCallback(
        text => {
            const todo = {
                id: nextId.current,
                text,
                checked: false
            }

            setTodos(todos.concat(todo))
            nextId.current += 1
        }, [todos]
    )
    // Callback 쓰는 애들은 제어 시점을 운영체제가 관리함. (여기서는 동작하는게 아니라 이런 이벤트가 작동하면 ~~한 동작을 하세요.라고 지정해주는 것!!)
    const onRemove = useCallback(
        id => {
            setTodos(todos.filter(todo => todo.id !== id))
        }, [todos]
    )
    // ...todo에서 ...은 이것을 제외한 나머지들을 의미
    const onToggle = useCallback(
        id => {
            setTodos(
                todos.map(todo =>
                    todo.id === id ? { ...todo, checked: !todo.checked } : todo,
                ),
            );
        }, [todos],
    );

    return (
        <div>
            <div>Todo App</div>
            <TodoTemplate>
                <TodoInsert onInsert={ onInsert }/>
                <TodoList todos={ todos } onRemove={ onRemove } onToggle={ onToggle }/>
            </TodoTemplate>
            <CounterContainer/>
        </div>
    );
}
// Finish Todo App Resource Data
export default App;