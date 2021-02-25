import Vue from "vue";
import VueRouter from "vue-router";

import Home from "@/views/Home.vue";
import Login from "@/views/Login.vue";
import Join from "@/views/Join.vue";
import JoinDone from "@/views/JoinDone.vue";
import NotFound from "@/views/error/NotFound.vue";
import Error from "@/views/Error.vue";
import JoinFido from "../views/JoinFido.vue";
import FidoAuth from "@/views/FidoAuth.vue";
import JoinSkt from "@/views/JoinSkt.vue";
import store from "@/store/index";
import MyIAM from '@/views/MyIAM.vue';

Vue.use(VueRouter);

const checkLoggedIn = () => (to, from, next) => {
  if (store.state.isLoggedIn) {
    return next();
  }
  next("/login");
};

const redirectLoggedIn = () => (to, from, next) => {
  if (store.state.isLoggedIn) {
    return next("/");
  }
  next();
};

const doLogout = () => (to, from, next) => {
  store.dispatch("LOGGEDOUT");
  next("/login");
};

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
    beforeEnter: checkLoggedIn(),
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
    beforeEnter: redirectLoggedIn(),
  },
  {
    path: "/logout",
    name: "Logout",
    beforeEnter: doLogout(),
  },
  {
    path: "/join",
    name: "Join",
    component: Join,
  },
  {
    path: "/join-skt",
    name: "JoinSkt",
    component: JoinSkt,
  },
  {
    path: "/join-done/:dossId",
    name: "JoinDone",
    component: JoinDone,
  },
  {
    path: "/join-fido",
    name: "JoinFido",
    component: JoinFido,
  },
  {
    path: "/fido-auth/:dossId",
    name: "FidoAuth",
    component: FidoAuth,
  },
  {
    path: "/error",
    name: "Error",
    component: Error,
  },
  {
    path: "*",
    redirect: "404",
  },
  {
    path: "/404",
    component: NotFound,
  },
  { 
    path: '/my-iam',
    name: 'MyIAM',
    component: MyIAM,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
