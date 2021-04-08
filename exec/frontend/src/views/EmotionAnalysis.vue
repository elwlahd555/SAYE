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
    <v-container>
      <speech-to-text @speechend="speechEnd"></speech-to-text>
    </v-container>
    <div style="text-align: center">{{ sentences }}</div>
  </div>
</template>

<script>
import SpeechToText from "@/components/stt/SpeechToText.vue";

const axios = require("axios");
const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  name: "EmotionAnalysis",
  components: {
    SpeechToText
  },
  updated() {
    this.query = this.sentences; // 감정분석 쿼리에 말한 sentences를 대입
    this.testEmotion();
  },
  data: () => {
    return {
      query: "",
      text: "",
      sentences: null
    };
  },
  methods: {
    testEmotion() {
      console.log(this.query);
      axios
        .post(`${spring_URL}/emotion/analysis`, this.query, {
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
    },
    /**
     * STT
     */
    speechEnd({ sentences, text }) {
      console.log("text", text);
      console.log("sentences", sentences);
      this.sentences = sentences;
    }
  }
};
</script>

<style></style>
