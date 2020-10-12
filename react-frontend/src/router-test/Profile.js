import React from 'react';
import { withRouter } from 'react-router-dom';
import WithRouterSample from './WithRouterSample'

// 데이터 만들어줄건데, function 방식이니까 강제로 description을 셋팅해주기!! (원래라면 DB에서 가져와야 할 정보인데 고정시켜 놓은 것!)
// DB에서 가져올 때는 axios 요청하면 됨! (여기서는 const로 값을 셋팅해 놓음! -> 고정시켜 놓았으니까)
const data = {
    test1: {
        name: 'test1',
        description: 'test1 desc'
    },
    test2: {
        name: 'test2',
        description: 'test2 desc'
    },
}

// 인자 필요하니 self 바인딩 방식
const Profile = ({ match }) => {
    const { username } = match.params
    const profile = data[username]

    if (!profile) {
        return <div>존재하지 않는 사용자다!</div>
    }

    return(
        <div>
            <h2>
                { username }({ profile.name })
            </h2>
            <p>{ profile.description } </p>
            <WithRouterSample/>
        </div>
    )
}

export default withRouter(Profile)