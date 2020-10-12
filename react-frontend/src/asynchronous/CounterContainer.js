import React, { Component } from 'react'
import { connect } from 'react-redux'
import Counter from './counter'
import { increaseAsync, decreaseAsync } from './counter'

// 인자 받아야 하니까 셀프 바인딩 하기
const CounterContainer = ({ number, increaseAsync, decreaseAsync }) => {
    return(
        <Counter number={ number } onIncrease={ increaseAsync } onDecrease={ decreaseAsync }/>
    )
}

// 리액트는 전역으로 밖에 못 받음
// vue mutation과 비슷한 느낌 받을 것임
export default connect(
    state => ({
        number: state.counter
    }),
    {
        increaseAsync,
        decreaseAsync
    }
) (CounterContainer)

