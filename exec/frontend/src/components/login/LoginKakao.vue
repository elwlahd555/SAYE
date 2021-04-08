<template>
  <img
    class="kakao_btn"
    src="@/assets/images/snsLogin/kakao.jpg"
    style="width:13%; margin-bottom: 20px"
    alt="kakao"
    @click="loginWithKakao"
  />
</template>

<script>
const server_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "LoginKakao",
  methods: {
    loginWithKakao() {
      const params = {
        redirectUri: `${server_URL}/kakaocallback`
      };
      window.Kakao.Auth.authorize(params);
    }
  }
};
</script>
