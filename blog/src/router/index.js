import {createRouter, createWebHistory} from 'vue-router'
import Layout from '../layout/Layout.vue'


const routes = [
  {
      path: '/',
      name: 'Layout',
      component: Layout,
      redirect: "/user",
      children: [
          {
              path: 'user',
              name: 'User',
              component: () => import("@/views/UserView")
          },
          {
              path: 'person',
              name: 'Person',
              component: () => import("@/views/PersonView")
          },
          {
              path: 'book',
              name: 'Book',
              component: () => import("@/views/BookView")
          }
      ]
  },
    {
        path: '/login',
        name: 'login',
        component: () => import("@/views/LoginView")
    },
    {
        path: '/register',
        name: 'register',
        component: () => import("@/views/RegisterView")
    }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
