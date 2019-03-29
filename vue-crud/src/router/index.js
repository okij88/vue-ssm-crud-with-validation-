import Vue from "vue";
import VueRouter from "vue-router";
Vue.use(VueRouter);
import NotFound from "@/components/NotFound";
import Home from "@/components/Home";
import Test from "@/components/Test"; //预留页面
import Login from "@/components/Login";



let routes = [
  {
    path: "*",
    redirect: { path: "/404" },
    meta: { requireAuth: false },
    hidden: true
  },
  {
    path: "/",
    redirect: { path: "/login" },
    meta: { requireAuth: false },
    hidden: true
  },
  {
    path: "/404",
    component: NotFound,
    meta: { requireAuth: false },
    hidden: true
  },
  {
    path: "/login",
    component: Login,
    key: "Login",
    hidden: true
  },
  {
    path: "/",
    component: Home,
    icon: "file",
    leaf: false, //只有一个节点
    key: "Major",
    permit: 1,
    children: [
      {
        path: "/Major",
        key: "Major",
        component: Test,
        meta: { requireAuth: true },
        name: "CRUD"
      }
    ]
  }
];
const router = new VueRouter({
  routes
});
router.beforeEach((to,from,next)=>{
  if (to.matched.some(res => res.meta.requireAuth)) {// 判断是否需要登录权限
    if (sessionStorage.getItem('username')) {// 判断是否登录
      next()
    } else {// 没登录则跳转到登录界面
      next({
        path: '/login',
      })
    }
  } else {
    next()
  }
});
export default router;
