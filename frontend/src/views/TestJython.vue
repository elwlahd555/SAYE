<template>
  <v-btn color="amber darken-4" outlined>
    <span @click="testJython">
      자이썬 테스트
      <v-icon small>mdi-check</v-icon>
    </span>
  </v-btn>
</template>

<script>
const axios = require("axios");
const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  methods: {
    testJython() {
      axios
        .get(`${spring_URL}/saye/testJython`)
        .then(res => {
          console.log(res);
        })
        .catch(error => {
          console.log(error + "안됨");
        });
    }
  }
};
</script>

<style></style>
