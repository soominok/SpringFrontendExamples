import React from 'react'

// React에게 memo하라고 시키는 것
// React.memo는 데이터를 빠르게 처리하기 위한 방법(응답성 올라감)
const Counter = React.memo(({ number, increaseAsync, decreaseAsync }) => {
    return(
        <div>
            <h1>{ number }</h1>
            <div>
                <button onClick={ onIncrease }>+1</button>
                <button onClick={ onDecrease }>-1</button>
            </div>
        </div>
    )
})

export default Counter