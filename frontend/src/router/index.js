import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import Test from '../views/Test.vue'
import Todo from '../views/Todo.vue'
import Concave from '../views/Concave.vue'

// For Cafe Board
import BoardListPage from '../views/BoardListPage.vue'
import BoardRegisterPage from '../views/BoardRegisterPage.vue'
import BoardReadPage from '../views/BoardReadPage.vue'
import BoardModifyPage from '../views/BoardModifyPage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/test',
    name: 'Test',
    component: Test
  },
  {
    path: '/todo',
    name: 'Todo',
    component: Todo
  },
  // component로 여러개를 사용할 때는 default로 걸어줘야 함.
  {
    path: '/concave',
    name: 'Concave',
    components: {
      default: Concave
    }
  },
  // compnents는 여러개 왔다갔다 할때 사용 가능 default도 써줘야 함.
  {
    path: '/board',
    name: 'BoardListPage',
    components: {
      default: BoardListPage
    }
  },
  {
    path: '/board/create',
    name: 'BoardRegisterPage',
    components: {
      default: BoardRegisterPage
    }
  },
  {
    path: '/board/boardNo',
    name: 'BoardReadPage',
    components: {
      default: BoardReadPage
    }
  },
  {
    path: '/board/boardNo/edit',
    name: 'BoardModifyPage',
    components: {
      default: BoardModifyPage
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
