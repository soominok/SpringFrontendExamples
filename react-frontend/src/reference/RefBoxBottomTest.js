import React, { Component } from "react";

class RefBoxBottomTest extends Component {
    scrollToBottom = () => {
        const { scrollHeight, clientHeight } = this.box
        console.log('scrollHeight: ' + scrollHeight + ', clientHeight: ' + clientHeight)
        this.box.scrollTop = scrollHeight - clientHeight
    }
    render() {
        const style = {
            border: '1px solid black',
            height: '300px',
            width: '300px',
            overflow: 'auto',
            position: 'relative'
        }
        const innerStyle ={
            width: '100%',
            height: '650px',
            background: 'linear-gradient(white, black)'
        }
        return(
            <div>
                <h1>Reference Box Bottom Test</h1>
                <div style={style} ref={(ref) => {this.box=ref}}>
                    <div style={innerStyle}/>
                </div>
            </div>
        )
    }
}

export default RefBoxBottomTest