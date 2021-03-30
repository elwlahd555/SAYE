<template>
  <div>
    <div>kakaologin...</div>
  </div>
</template>

<script>
const axios = require("axios");
const spring_URL = process.env.VUE_APP_SPRING_URL;

import Swal from "sweetalert2";
import { getKakaoToken, getKakaoUserInfo } from "@/services/login";

export default {
  name: "KakaoCallback",
  created() {
    if (this.$route.query.code) {
      this.setKakaoToken();
    }
  },
  data: () => {
    return {
      user: {
        uId: "",
        uPassword: "",
        uNickname: ""
      }
    };
  },
  methods: {
    async setKakaoToken() {
      console.log("카카오 인증 코드", this.$route.query.code);
      const { data } = await getKakaoToken(this.$route.query.code);
      if (data.error) {
        Swal.fire({
          icon: "warning",
          title: "힝!",
          text: "카카오톡 로그인 오류입니다ㅠ",
          confirmButtonColor: "#f8bb86"
        });
        this.$router.replace("/login");
        return;
      }
      window.Kakao.Auth.setAccessToken(data.access_token);
      this.$cookies.set("access-token", data.access_token, "1d");
      this.$cookies.set("refresh-token", data.refresh_token, "1d");
      await this.setUserInfo();
      this.$router.replace("/");
    },
    async setUserInfo() {
      let user_data = await getKakaoUserInfo();
      // console.log(user_data)
      // 여기에 백으로 계정 정보 넘겨주면 된다.
      this.user.uId = user_data.kakao_account.email + "_" + user_data.id;
      this.user.uPassword = "kakaoPassword";
      this.user.uNickname = user_data.properties.nickname;
      axios
        .post(`${spring_URL}/auth/kakao/callback`, this.user)
        .then(res => {
          // console.log(res);
          let token = res.data["auth-token"];
          if (token === undefined) {
            Swal.fire({
              icon: "warning",
              title: "비밀번호 오류",
              text: "비밀번호를 다시 확인하세요!",
              confirmButtonColor: "#f8bb86"
            });
          } else {
            Swal.fire({
              title: `${this.uNickname} 님`,
              text: "반가워요!! ^.^",
              imageUrl: "https://unsplash.it/400/200",
              imageWidth: 400,
              imageHeight: 200,
              padding: "3em"
            });
            // context.commit('SET_USER_AUTH_DATA', res.data)
            localStorage.setItem("auth-token", token);
            // axios default 헤더에 현재 token 적재
            axios.defaults.headers.common[
              "auth-token"
            ] = window.localStorage.getItem("auth-token");
            this.$store.commit("SET_USER_AUTH_DATA", res.data);
            this.$router.push({ name: "HomePage" });
          }
        })
        .catch(err => {
          console.log(err);
          Swal.fire({
            icon: "error",
            title: "로그인 실패",
            text: "Oops.... T.T",
            confirmButtonColor: "#f27474"
          });
        });
    },
    submit() {
      if (this.$refs.form.validate()) {
        // sprin url 받기
        axios
          .post(`${spring_URL}/auth/kakao/callback`, this.user)
          .then(() => {
            Swal.fire({
              title: `${this.uNickname} 님`,
              text: "반가워요!! ^.^",
              imageUrl: "https://unsplash.it/400/200",
              imageWidth: 400,
              imageHeight: 200,
              padding: "3em"
            });
            this.$router.push({ name: "HomePage" });
          })
          .catch(() => {
            Swal.fire({
              icon: "error",
              title: "로그인 실패",
              text: "Oops.... T.T",
              confirmButtonColor: "#f27474"
            });
          });
      } else {
        console.log("카카오 소셜 로그인에 실패");
      }
    }
  }
};
</script>
