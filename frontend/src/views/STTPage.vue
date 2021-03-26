<template>
  <div>
    <speech-to-text />
  </div>
</template>

<script>
import SpeechToText from "@/components/stt/SpeechToText.vue";

export default {
  components: {
    SpeechToText
  },
  data() {
    return {
      text: "",
      sentences: null
    };
  },
  methods: {
    speechEnd({ sentences, text }) {
      console.log("text", text);
      console.log("sentences", sentences);
      this.sentences = sentences;
    }
  }
};
</script>

<style></style>
