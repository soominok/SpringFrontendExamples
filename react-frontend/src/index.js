import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import BackupApp from './BackupApp'
import * as serviceWorker from './serviceWorker';
import { BrowserRouter } from 'react-router-dom';

// 비동기, 동기 처리 해보기
import { createStore, applyMiddleware } from 'redux'
import { Provider } from 'react-redux'
import rootReducer, { rootSaga } from './asynchronous'
import { createLogger } from 'redux-logger/src'
import { composeWithDevTools } from 'redux-devtools-extension'
import createSagaMiddleware from 'redux-saga'
import ReduxThunk from 'redux-thunk'

const logger = createLogger();
const sagaMiddleware = createSagaMiddleware();
const store = createStore(
    rootReducer,
    composeWithDevTools(applyMiddleware(logger, ReduxThunk, sagaMiddleware))
)
sagaMiddleware.run(rootSaga)

ReactDOM.render(
  //<React.StrictMode>
  // <BrowserRouter>
  //   <BackupApp/>
  //   <App/>
  // </BrowserRouter>,
  //</React.StrictMode>,
    <Provider store={ store }>
      <App/>
    </Provider>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();

// BrowserRouter 아이디, 패스워드 가지고 router하려고 한 건데,
// store를 사용해서 강제로 전역화시키려고 하는 것.

// 리덕스를 쓰는 이유는 데이터를 대규모로 땡겨올 때 (작업량 많은)
// 해당 시간 내에 끝내야 한다 할때는 동기 처리,
// 해당 시간이 정해진 것이 아니라 될 때 바로바로 처리해야 할 때는 비동기 처리!!