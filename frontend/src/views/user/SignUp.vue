<template>
  <v-container fluid fill-height style="height: 80vh">
    <v-card class="mx-auto" outlined elevation="15" width="500">
      <v-form @submit.prevent ref="form">
        <v-row>
          <v-col md="1">
            <v-card-actions class="pt-3 px-3">
              <v-btn class="ro" to="/login" color="white" depressed>
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
          <h2>회원가입</h2>
        </v-row>
        <v-card-text>
          <v-row>
            <v-col cols="9" class="p-0">
              <v-text-field
                ref="id"
                v-model="user.uId"
                label="아이디"
                prepend-icon="ID"
                :rules="[rules.required, rules.counter]"
                required
                persistent-hint
              ></v-text-field>
            </v-col>
            <v-col cols="3" class="d-flex pl-0 align-center">
              <v-btn color="amber darken-4" outlined>
                <span @click="idDoubleCheck"
                  >중복체크
                  <v-icon small>mdi-check</v-icon>
                </span>
              </v-btn>
            </v-col>
          </v-row>

          <v-text-field
            ref="password"
            v-model="user.uPassword"
            label="비밀번호"
            :rules="[rules.required]"
            :type="showPassword ? 'text' : 'password'"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            @click:append="showPassword = !showPassword"
            prepend-icon="mdi-lock"
          >
          </v-text-field>

          <v-text-field
            ref="passwordConfirm"
            v-model="passwordConfirm"
            :rules="[rules.required, passwordCheck]"
            label="비밀번호 확인"
            :type="showPassword2 ? 'text' : 'password'"
            :append-icon="showPassword2 ? 'mdi-eye' : 'mdi-eye-off'"
            @click:append="showPassword2 = !showPassword2"
            prepend-icon="mdi-lock-alert"
          >
          </v-text-field>

          <v-text-field
            ref="name"
            v-model="user.uNickname"
            required
            label="닉네임"
            :rules="[rules.required]"
            prepend-icon="mdi-account-circle"
            type="text"
            @keyup.enter="submit"
          >
          </v-text-field>
        </v-card-text>

        <v-card-actions>
          <v-row justify="center" align="center" class="mb-5">
            <v-btn
              style="width: 80%"
              color="teal darken-2"
              dark
              rounded
              @click="submit"
              >회원가입</v-btn
            >
          </v-row>
        </v-card-actions>
      </v-form>
    </v-card>
  </v-container>
</template>

<script>
import Swal from "sweetalert2";
import Lottie from "@/components/Lottie.vue";

import * as animationData from "@/assets/lottieFiles/register.json";

const axios = require("axios");
const spring_URL = process.env.VUE_APP_SPRING_URL;
// const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
export default {
  name: "Signup",
  components: {
    Lottie
  },
  data: () => {
    return {
      animationData: {
        animationData: animationData.default
      },
      user: {
        uId: "",
        uPassword: "",
        uNickname: "",
        uImg: ""
      },
      dialog: false,
      passwordConfirm: "",
      showPassword: false,
      showPassword2: false,
      SignupFormHasError: false,
      authCodeIsRight: false,
      authCode: "",
      button_color: "blue-grey lighten-1",
      idNotDup: false,
      auth_button_color: "blue-grey lighten-1",
      dup2: false,
      rules: {
        required: value => !!value,
        counter: value => value.length <= 20 || "최대 20자까지 가능합니다."
      }
    };
  },
  computed: {
    passwordCheck() {
      return () =>
        this.user.uPassword === this.passwordConfirm ||
        "비밀번호가 일치하지 않습니다.";
      // return () => (this.user.uPassword === this.passwordConfirm) || '비밀번호가 일치하지 않습니다.'
    }
  },
  methods: {
    //아이디 중복 체크
    idDoubleCheck() {
      // if (pattern.test(this.user.uId)) {
      if (this.user.uId) {
        axios
          .get(`${spring_URL}/user/checkid?uId=${this.user.uId}`)
          .then(res => {
            if (res.data === "중복아님") {
              // 사용가능한 아이디
              Swal.fire({
                icon: "success",
                title: "Success",
                text: "사용 가능한 아이디입니다!",
                confirmButtonColor: "#a5dc86"
              });
              this.idNotDup = true;
            } else {
              Swal.fire({
                icon: "error",
                title: "중복 아이디",
                text: "다른 아이디를 입력해주세요!",
                confirmButtonColor: "#f27474"
              });
              this.idNotDup = false;
            }
          })
          .catch(error => {
            console.log(error + "안됨");
          });
      } else {
        Swal.fire({
          icon: "warning",
          title: "아이디 입력",
          text: "사용할 아이디를 입력해주세요!",
          confirmButtonColor: "#f8bb86"
        });
        return false;
      }
    },

    // 회원가입 제출
    submit() {
      if (this.$refs.form.validate()) {
        // sprin url 받기
        this.user.uImg = require("@/assets/images/saye.png");
        console.log(this.user);
        axios
          .post(`${spring_URL}/user/signup`, this.user, {
            headers: {
              "content-type": "application/json"
            }
          })
          .then(() => {
            Swal.fire({
              icon: "success",
              title: "회원가입 완료",
              text: "회원가입을 축하드립니다",
              confirmButtonColor: "#a5dc86"
            });
            this.$router.push({ name: "LoginPage" });
          })
          .catch(() => {
            Swal.fire({
              icon: "error",
              title: "회원가입 실패",
              text: "Oops.... T.T",
              confirmButtonColor: "#f27474"
            });
          });
      } else {
        Swal.fire({
          icon: "warning",
          title: "잠깐!",
          text: "모든 빈 칸에 값을 입력하세요!",
          confirmButtonColor: "#f8bb86"
        });
      }
    },
    disagree() {
      this.dup2 = false;
      this.dialog = false;
      this.authCode = "";
    }
  }
};
</script>
