import React, { Component } from "react";

class StateBasedEventIteration extends Component {
    state = {
        names: ["snow", "water", "fire", "thunder", "wind"],
        name: ''
    }
    // self 바인딩 해주기
    handleChange = (e) => {
        this.setState({ name: e.target.value })
    }
    // self 바인딩 해주기
    handleInsert = () => {
        this.setState({
            names: this.state.names.concat(this.state.name),
            name: ''
        })
    }
    // index를 받아와서
    handleRemove = (index) => {
        const { names } = this.state
        console.log('handleRemove: ' + names)
        this.setState({
            names: [
                ...names.slice(0, index),
                ...names.slice(index + 1, names.length)
            ]
        })
    }
    render() {
        const nameList = this.state.names.map(
            (name, index) => (<li key={ index } onDoubleClick={ () => this.handleRemove(index) }>{ name }</li>)
        )
        return(
            <div>
                <input onChange={ this.handleChange }
                        value={ this.state.name }/>
                <button onClick={ this.handleInsert }>Add Item</button>
                <ul>
                    { nameList }
                </ul>
            </div>
        )
    }
}

export default StateBasedEventIteration