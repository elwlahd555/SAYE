<template>
  <div style="width:80%">
    <v-form @submit.prevent ref="form">
      <v-card-text>
        <v-text-field
          ref="id"
          v-model="user.uId"
          label="아이디"
          prepend-icon="ID"
          :rules="[rules.required, rules.counter]"
        />
        <v-text-field
          ref="password"
          v-model="user.uPassword"
          :type="showPassword ? 'text' : 'password'"
          :rules="[rules.required]"
          label="비밀번호"
          prepend-icon="mdi-lock"
          :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
          @click:append="showPassword = !showPassword"
          @keyup.enter="loginsubmit"
        />
      </v-card-text>
      <v-card-actions>
        <v-col md="6">
          <v-btn @click="loginsubmit" color="indigo accent-2" rounded dark block
            >Login</v-btn
          >
        </v-col>
        <v-col md="6">
          <v-btn to="/signup" color="teal darken-1" rounded dark block
            >SignUp</v-btn
          >
        </v-col>
      </v-card-actions>
    </v-form>
  </div>
</template>

<script>
import Swal from "sweetalert2";
import { mapState } from "vuex";

const axios = require("axios");
const spring_URL = process.env.VUE_APP_SPRING_URL;
export default {
  name: "LoginForm",

  components: {},
  computed: {
    ...mapState(["uName"])
  },
  methods: {
    loginsubmit() {
      // 아이디, 비번 다 기입했는지 확인
      if (!this.$refs.form.validate()) {
        Swal.fire({
          icon: "warning",
          title: "잠깐!",
          text: "모든 빈 칸에 값을 입력하세요!",
          confirmButtonColor: "#f8bb86"
        });
      } else {
        console.log("확인");
        localStorage.setItem("auth-token", "");
        axios
          .post(`${spring_URL}/login`, this.user)
          .then(res => {
            let token = res.data["auth-token"];
            if (token === undefined) {
              Swal.fire({
                icon: "warning",
                title: "비밀번호 오류",
                text: "비밀번호를 다시 확인하세요!",
                confirmButtonColor: "#f8bb86"
              });
            } else {
              // context.commit('SET_USER_AUTH_DATA', res.data)
              localStorage.setItem("auth-token", token);
              // axios default 헤더에 현재 token 적재
              axios.defaults.headers.common[
                "auth-token"
              ] = window.localStorage.getItem("auth-token");
              this.$store.commit("SET_USER_AUTH_DATA", res.data);
              this.$router.push({ name: "HomePage" });

              Swal.fire({
                title: `${this.uName} 님`,
                text: "반가워요!! ^.^",
                imageUrl: "https://unsplash.it/400/200",
                imageWidth: 400,
                imageHeight: 200,
                padding: "3em"
              });
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
      }
    }
  },
  data: () => {
    return {
      user: {
        uId: "",
        uPassword: ""
      },
      showPassword: false,
      formHasErrors: false,
      rules: {
        required: value => !!value,
        counter: value => value.length <= 20 || "최대 20자까지 가능합니다."
      }
    };
  }
};
</script>
<style scoped></style>
