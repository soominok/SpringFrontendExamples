import React, { Component } from "react";

class IterationSample extends Component {
    render() {
        const names = ["snow", "water", "fire", "thunder", "wind"];
        // 위의 names 리스트를 으로 연결해서 해당 요소에 대해 <li> 태그를 ~~
        const nameList = names.map(
            (name) => (<li>{ name }</li>)
        )
        return(
            <ul>
                { nameList }
            </ul>
        )
    }
}

export default IterationSample