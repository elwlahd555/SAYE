import Vue from "vue";
import VueRouter from "vue-router";
import { store } from "../store/index";

/* Pages */
import IntroPage from "@/views/IntroPage.vue";
import HomePage from "@/views/HomePage.vue";
import AboutPage from "@/views/AboutPage.vue";
import ErrorPage from "@/views/ErrorPage.vue";
import KakaoCallback from "@/views/user/KakaoCallback.vue";
import TestJython from "@/views/TestJython.vue";
import EmotionAnalysis from "@/views/EmotionAnalysis.vue";
import STTPage from "@/views/STTPage.vue";
import Board from "@/views/board/Board.vue";

Vue.use(VueRouter);
Vue.use(store);

const routes = [
  {
    path: "*",
    redirect: "/404"
  },
  {
    path: "/",
    name: "IntroPage",
    component: IntroPage
  },
  {
    path: "/home",
    name: "HomePage",
    component: HomePage
  },
  {
    path: "/about",
    name: "AboutPage",
    component: AboutPage
  },
  /**
   * 메인 기능 페이지 (검색, 게시판, 플레이리스트, 랭킹)
   */
  {
    path: "/basicSearch",
    name: "BasicSearch",
    component: () => import("@/views/musik/BasicSearch.vue")
  },
  {
    path: "/myplaylist",
    name: "MyplaylistPage",
    component: () => import("@/views/musik/MyplaylistPage.vue")
  },
  {
    path: "/ranking",
    name: "RankingPage",
    component: () => import("@/views/musik/RankingPage.vue")
  },
  // {
  //   path: "/board",
  //   name: "BoardPage",
  //   component: () => import("@/views/board/BoardPage.vue")
  // },

  // {
  //   path: "/boardDetail",
  //   name: "BoardDetailPage",
  //   component: () => import("@/views/board/BoardDetailPage.vue")
  // },

  /**
   * board 관련
   */
  {
    name: "Board",
    path: "/board",
    component: Board,
    children: [
      {
        path: "",
        name: "BoardList",
        component: () => import("@/components/board/BoardList.vue")
      },
      {
        path: "/add",
        name: "BoardRegister",
        component: () => import("@/components/board/BoardRegister.vue")
      },
      {
        path: "/detail/:bNo",
        name: "BoardDetail",
        component: () => import("@/components/board/BoardDetail.vue")
      },
      {
        path: "edit/:bNo",
        name: "BoardEdit",
        component: () => import("@/components/board/BoardEdit2.vue")
      }
    ],
    redirect: () => {
      return "/board";
    }
  },

  /**
   * user 관련 (로그인, 회원가입)
   */
  {
    path: "/login",
    name: "LoginPage",
    component: () => import("@/views/user/LoginPage.vue")
  },
  {
    path: "/signup",
    name: "SignUp",
    component: () => import("@/views/user/SignUp.vue")
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: () => import("@/views/user/MyPage.vue")
  },
  {
    path: "/kakaocallback",
    name: "KakaoCallback",
    component: KakaoCallback
  },
  /**
   * Test Page
   */
  {
    path: "/testJython",
    name: "TestJython",
    component: TestJython
  },
  /**
   * Emotion Analysis Page
   */
  {
    path: "/emotionAnalysis",
    name: "EmotionAnalysis",
    component: EmotionAnalysis
  },
  /**
   * Emotion base music recommendation
   */
  {
    path: "/emotionMusic",
    name: "EmotionMusic",
    component: () => import("@/views/musik/EmotionMusic.vue")
  },
  /**
   * Speech To Text
   */
  {
    path: "/STTPage",
    name: "STTPage",
    component: STTPage
  },
  /**
   * page not found
   */
  {
    path: "/404",
    name: "ErrorPage",
    component: ErrorPage
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

router.beforeEach((to, from, next) => {
  const publicPages = ["/login", "/signup", "/", "/404", "/kakaocallback"];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = store.state.isLogin;
  //const authToken = localStorage.getItem("auth-token");

  // redirect to login page
  if (authRequired && !loggedIn) {
    next("/login");
  } else {
    next();
  }
});

export default router;
