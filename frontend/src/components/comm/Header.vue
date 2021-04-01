<template>
  <v-app-bar class="my-5 mr-10 px-16" color="white" flat elevate-on-scroll>
    <router-link to="/home" style="margin-top: 10px">
      <v-img height="50" width="150" src="@/assets/saye_logo.jpg"></v-img>
    </router-link>

    <v-spacer></v-spacer>

    <v-btn large :to="links.login" v-show="!isLogin">
      <v-icon>mdi-account-circle</v-icon>
      login
    </v-btn>

    <v-btn large @click="logout" v-show="isLogin">
      <v-icon>mdi-connection</v-icon>
      logout
    </v-btn>

    <v-btn large :to="{ name: 'MyPage' }" v-show="isLogin">
      <v-icon>mdi-buffer</v-icon>
      mypage
    </v-btn>

    <v-btn large to="/">
      <v-icon>mdi-information</v-icon>
      info
    </v-btn>
  </v-app-bar>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      links: {
        login: "/login",
        logout: "",
        mypage: "/mypage",
        about: "/about"
      },
      menuFlag: false,
      activeMenu: null
    };
  },
  computed: {
    ...mapState(["isLogin", "uName"])
  },
  methods: {
    //...mapActions({ logout: "LOGOUT" }),
    logout() {
      this.$store.dispatch("LOGOUT").then(() => {
        this.$router.go({ name: "HomePage" });
      });
    },
    showMenu() {
      console.log(this.menuFlag);
      this.menuFlag = !this.menuFlag;
      if (this.menuFlag) {
        this.activeMenu = "activeMenu";
      } else {
        this.activeMenu = null;
      }
    }
  }
};
</script>

<style scoped></style>
