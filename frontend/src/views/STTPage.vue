<template>
  <div>
    <v-container fluid>
      <speech-to-text @speechend="speechEnd"></speech-to-text>
    </v-container>
    <div style="text-align: center">{{ sentences }}</div>
  </div>
</template>

<script>
import SpeechToText from "@/components/stt/SpeechToText.vue";

export default {
  components: {
    SpeechToText
  },
  updated() {
    let item = this.sentences;
    this.$store.commit("SET_CAMERA_ITEM", { item }); //아이템 넣어주고
  },
  data() {
    return {
      sentences: ""
    };
  },
  methods: {
    speechEnd({ sentences }) {
      console.log("sentences", sentences);
      this.sentences = sentences;
    }
  }
};
</script>

<style scoped>
.btn {
  font-weight: 50px;
  width: 50%;
  min-height: 250px;
}
</style>
