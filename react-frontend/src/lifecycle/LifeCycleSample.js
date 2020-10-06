import React, { Component } from "react";

class LifeCycleSample extends Component {
    state = {
        number: 0,
        color: null
    }
    myRef = null
    constructor(props) {
        super(props)
        console.log('LifeCycle Constructor')
    }
    static getDerivedStateFromProps(nextProps, prevState) {
        if (nextProps.color !== prevState.color) {
            return { color: nextProps.color }
        }
        return null
    }
    // 객체 생성 시점
    componentDidMount() {
        console.log('componentDidMount()')
    }
    // 업데이트 시점 (한번에 데이터 처리해야 할 때는 한번에 모아서 업데이트 하니까)
    shouldComponentUpdate(nextProps, nextState) {
        console.log('LifeCycle shouldComponentUpdate()', nextProps, nextState)
        return nextState.number % 10 !== 4
    }
    // 해제 시점
    // component를 update 할 것이냐, mount를 해제할 것이냐
    componentWillUnmount() {
        console.log('LifeCycle componentWillUnmount()')
    }
    handleClick = () => {
        this.setState({
            number: this.state.number + 1
        })
    }
    // 업데이트 이전
    getSnapshotBeforeUpdate(prevProps, prevState) {
        console.log('LifeCycle getSnapshotBeforeUpdate()', prevProps, prevState)
        if (prevProps.color !== this.props.color) {
            return this.myRef.style.color
        }
        return null
    }
    componentDidUpdate(prevProps, prevState, snapshot) {
        console.log('LifeCylce componentDidUpdate()', prevProps, prevState)
        if (snapshot) {
            console.log('Before Update Color: ', snapshot)
        }
    }
    // 버튼을 눌렀을 때 실제의 값과 console 창에서의 값이 약간씩 다르게 나올 때가 있음!
    // 그래서 안정적이게 데이터를 처리하고 싶다면 동기 처리, 아니라면 비동기 처리로 선택하여 만들어 줘야 함!!
    render() {
        console.log('LifeCycle render()')
        const style = {
            color: this.props.color
        }
        return(
            <div>
                <h1 style={style} ref={ref => this.myRef=ref}>
                    { this.state.number }
                </h1>
                <p>color: { this.state.color }</p>
                <button onClick={ this.handleClick }>
                    Add
                </button>
            </div>
        )
    }
}

export default LifeCycleSample