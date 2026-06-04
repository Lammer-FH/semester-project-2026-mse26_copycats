import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import HomePage from '../views/HomePage.vue'
import RoomOverview from '../views/RoomOverview.vue'
import RoomDetailPage from '../views/RoomDetailPage.vue'
import BookingPage from '@/views/BookingPage.vue'
import AboutPage from '@/views/AboutPage.vue';
import ImprintPage from '@/views/ImprintPage.vue';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'Home',
    component: HomePage
  },
  {
    path: '/rooms',
    name: 'Rooms',
    component: RoomOverview
  },
  {
    path: '/rooms/:id',
    name: 'RoomDetail',
    component: RoomDetailPage
  },
  {
    path: '/rooms/:id/booking',
    name: 'Booking',
    component: BookingPage
  },
  {
    path: '/about',
    name: 'About',
    component: AboutPage
  },
  {
    path: '/imprint',
    name: 'Imprint',
    component: ImprintPage
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
