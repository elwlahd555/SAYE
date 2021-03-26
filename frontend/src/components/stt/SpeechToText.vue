<template>
  <div>
    <v-btn class="btn" style="font-size: 30px" @click="startSpeechRecognition"
      >speech</v-btn
    >
  </div>
</template>

<script>
let SpeechRecognition =
  window.SpeechRecognition || window.webkitSpeechRecognition;
let recognition = SpeechRecognition ? new SpeechRecognition() : false;
export default {
  updated() {
    this.startSpeechRecognition();
  },
  props: {
    lang: {
      type: String,
      default: "ko-KR"
    }
  },
  data() {
    return {
      error: false,
      speaking: false,
      toggle: false,
      runtimeTranscription: "",
      sentences: []
    };
  },
  methods: {
    checkCompatibility() {
      if (!recognition) {
        this.error =
          "Speech Recognition is not available on this browser. Please use Chrome or Firefox";
      }
    },
    endSpeechRecognition() {
      recognition.stop();
      this.sentences.pop();
      this.toggle = false;
      this.$emit("speechend", {
        sentences: this.sentences
      });
    },
    startSpeechRecognition() {
      if (!recognition) {
        this.error =
          "Speech Recognition is not available on this browser. Please use Chrome or Firefox";
        return false;
      }
      this.toggle = true;
      recognition.lang = this.lang;
      recognition.continuous = true;
      recognition.interimResults = true;

      recognition.addEventListener("speechstart", () => {
        this.speaking = true;
      });

      recognition.addEventListener("speechend", () => {
        this.speaking = false;
      });

      recognition.addEventListener("result", () => {
        const text = Array.from(event.results)
          .map(result => result[0])
          .map(result => result.transcript)
          .join("");

        this.runtimeTranscription = text;

        this.endSpeechRecognition();
      });

      recognition.addEventListener("end", () => {
        if (this.runtimeTranscription !== "") {
          this.sentences.push(
            this.capitalizeFirstLetter(this.runtimeTranscription)
          );
          this.$emit(
            'update:text',
            `${this.text}${this.sentences.slice(-1)[0]}. `
          );
        }
        this.runtimeTranscription = "";
        recognition.stop();
        if (this.toggle) {
          // keep it going.
          recognition.start();
        }
      });
      recognition.start();
    },
    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    }
  },
  mounted() {
    this.checkCompatibility();
  }
};
</script>
