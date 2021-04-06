<template>
  <article id="panel-2" class="panel full-screen orange">
    <v-container fill-height fluid>
      <v-row align="center" justify="center">
        <v-col>
          <speech-to-text @speechend="speechEnd"></speech-to-text>
        </v-col>
      </v-row>
    </v-container>
  </article>
</template>

<script>
import SpeechToText from "@/components/stt/SpeechToText.vue";

const axios = require("axios");
const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  components: {
    SpeechToText
  },
  updated() {
    this.query = this.sentences; // 감정분석 쿼리에 말한 sentences를 대입
    // this.testEmotion();
  },
  data() {
    return {
      query: "",
      text: "",
      sentences: null,
      scY: 0,
      emotion: ""
    };
  },
  methods: {
    testEmotion() {
      // console.log(this.query);
      axios
        .post(`${spring_URL}/emotion/analysis`, this.query, {
          headers: {
            "Content-Type": "application/json"
          }
        })
        .then(res => {
          // console.log(res.data);
          this.emotion = res.data.Result[0][1];
          this.$router.push({
            name: "EmotionMusic",
            params: {
              emotion: this.emotion
            }
          });
        })
        .catch(error => {
          console.log(this.query);
          console.log(error + "안됨");
        });
    },
    /**
     * STT
     */
    // speechEnd({ sentences, text }) {
    speechEnd({ sentences }) {
      // console.log("text", text);
      // console.log("sentences", sentences);
      this.query = sentences;
      this.testEmotion();
    }
  }
};
</script>

<style></style>
