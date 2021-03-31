<template>
  <v-app-bar class="my-5 mr-10 px-16" color="white" flat elevate-on-scroll>
    <router-link to="/home" style="margin-top: 10px">
      <v-img height="50" width="150" src="@/assets/saye_logo.jpg"></v-img>
    </router-link>

    <v-spacer></v-spacer>

    <v-tooltip bottom>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          :to="links.login"
          v-show="!isLogin"
          v-bind="attrs"
          v-on="on"
          plain
        >
          <v-icon large color="success">
            mdi-account-check
          </v-icon>
        </v-btn>
      </template>
      <span>Login</span>
    </v-tooltip>

    <v-tooltip bottom>
      <template v-slot:activator="{ on, attrs }">
        <v-btn @click="logout" v-show="isLogin" v-bind="attrs" v-on="on" plain>
          <v-icon large color="error">
            mdi-connection
          </v-icon>
        </v-btn>
      </template>
      <span>Logout</span>
    </v-tooltip>

    <v-tooltip bottom>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          :to="{ name: 'MyPage' }"
          v-show="isLogin"
          v-bind="attrs"
          v-on="on"
          plain
        >
          <v-icon large color="success">
            mdi-buffer
          </v-icon>
        </v-btn>
      </template>
      <span>MyPage</span>
    </v-tooltip>

    <v-tooltip bottom>
      <template v-slot:activator="{ on, attrs }">
        <v-btn to="/" v-bind="attrs" v-on="on" plain>
          <v-icon large color="primary">
            mdi-information
          </v-icon>
        </v-btn>
      </template>
      <span>Info</span>
    </v-tooltip>
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
