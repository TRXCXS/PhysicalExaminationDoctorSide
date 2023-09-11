import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../views/Login.vue'
import OrdersList from '../views/OrdersList.vue'
import Register from '../views/Register.vue'
const routes = [
  {
    path: '/', 
    name: 'home',
    component: Login
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path:'/orderslist',
    name:'orderslist',
    component:OrdersList
  },
  {
    path:'/register',
    name:'register',
    component:Register
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
