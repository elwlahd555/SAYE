<template>
  <div>
    <v-text-field
      ref="query"
      v-model="query"
      label="오늘 어땠어?"
      prepend-icon="mdi-account-circle"
      type="text"
      @keyup.enter="testEmotion"
    >
    </v-text-field>
    <v-btn color="amber darken-4" outlined>
      <span @click="testEmotion">
        감정분석
        <v-icon small>mdi-check</v-icon>
      </span>
    </v-btn>
  </div>
</template>

<script>
const axios = require("axios");
const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  name: "EmotionAnalysis",
  data: () => {
    return {
      query: ""
    };
  },
  methods: {
    testEmotion() {
      console.log(this.query);
      axios
        .post(`${spring_URL}/saye/emotion/analysis`, this.query, {
          headers: {
            "Content-Type": "application/json"
          }
        })
        .then(res => {
          console.log(res.data);
        })
        .catch(error => {
          console.log(this.query);
          console.log(error + "안됨");
        });
    }
  }
};
</script>

<style></style>
