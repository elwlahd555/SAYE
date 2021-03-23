<template>
  <v-container fluid fill-height style="height: 80vh">
    <v-card class="mx-auto" outlined elevation="15" width="500">
      <v-row>
        <v-col md="1">
          <v-card-actions class="pt-3 px-3">
            <v-btn
              class="ro"
              @click="hasHistory() ? $router.go(-1) : $router.push('/')"
              color="white"
              depressed
            >
              <v-icon large color="grey">mdi-arrow-left-circle</v-icon>
            </v-btn>
          </v-card-actions>
        </v-col>
        <v-col md="11">
          <Lottie
            style="height: 250px; padding-right: 25px;"
            :options="animationData"
          />
        </v-col>
      </v-row>
      <v-row justify="center">
        <h2>로그인</h2>
      </v-row>
      <v-row justify="center" no-gutters>
        <LoginForm />
      </v-row>
      <v-row justify="center">
        <v-col md="4">
          <v-divider></v-divider>
        </v-col>
        <h3>OR</h3>
        <v-col md="4">
          <v-divider></v-divider>
        </v-col>
      </v-row>

      <v-row class="py-3" justify="center" no-gutters>
        <LoginKakao />
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
import LoginKakao from "@/components/login/LoginKakao.vue";
import LoginForm from "@/components/login/LoginForm.vue";
import Lottie from "@/components/Lottie.vue";

import * as animationData from "@/assets/lottieFiles/login.json";

export default {
  name: "Login",
  components: {
    LoginKakao,
    LoginForm,
    Lottie
  },
  data() {
    return {
      animationData: {
        animationData: animationData.default
      }
    };
  },
  methods: {
    hasHistory() {
      return window.history.length > 2;
    }
  }
};
</script>

<style></style>
