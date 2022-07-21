import {createRouter, createWebHistory} from 'vue-router'
import Layout from '../layout/Layout.vue'


const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import("@/views/HomeView")
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: () => import("@/views/LoginView")
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
