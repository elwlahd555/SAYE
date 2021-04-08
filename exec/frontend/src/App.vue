<template>
  <v-app>
    <v-main>
      <Header v-if="!introPage" />
      <Aside v-if="!introPage" />
      <transition name="fade" mode="out-in">
        <router-view />
      </transition>
    </v-main>
  </v-app>
</template>

<script>
import Header from "@/components/comm/Header";
import Aside from "@/components/comm/Aside";

import axios from "axios";
import { mapState } from "vuex";

const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  name: "App",
  components: {
    Header,
    Aside
  },
  watch: {
    isLogin(val) {
      if (val == false) {
        console.log("로그아웃");
      } else {
        console.log("로그인");
        // (혹시 모르니) 50분 마다 토큰 갱신
        axios
          .get(`${spring_URL}/spotify/accesstoken`)
          .then(console.log("토큰 갱신!"));
      }
    }
  },
  computed: {
    ...mapState({
      isLogin: "isLogin"
    }),
    introPage() {
      return this.$route.name == "IntroPage";
    }
  },
  mounted() {
    if (this.isLogin == true) {
      setInterval(() => {
        axios
          .get(`${spring_URL}/spotify/accesstoken`)
          .then(console.log("토큰 갱신!"));
      }, 1000 * 50 * 60);
    }
  }
};
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition-duration: 0.3s;
  transition-property: opacity;
  transition-timing-function: ease;
}

.fade-enter,
.fade-leave-active {
  opacity: 0;
}
</style>
