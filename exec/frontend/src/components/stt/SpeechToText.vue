<template>
  <div>
    <v-row justify="center">
      <div class="balloon transform" :class="{ 'transform-active': toggle }">
        <v-textarea
          solo
          flat
          :height="toggle ? 300 : 0"
          class="think area text-center pa-2"
          type="text"
          style="line-height:200%"
          v-model="sentences"
        />
      </div>
      <div class="micBox" :class="{ transbutton: toggle }">
        <v-btn
          class="micButton"
          dark
          @click.stop="
            toggle ? endSpeechRecognition() : startSpeechRecognition()
          "
          icon
          :color="!toggle ? 'grey' : speaking ? 'red' : 'red darken-3'"
          :class="{ 'animated infinite pulse': toggle }"
          depressed
          fab
          x-large
          :to="{ name: '' }"
        >
          <v-icon>{{
            toggle ? "mdi-text-to-speech" : "mdi-text-to-speech-off"
          }}</v-icon>
        </v-btn>
      </div>
    </v-row>
  </div>
</template>

<script>
let SpeechRecognition =
  window.SpeechRecognition || window.webkitSpeechRecognition;
let recognition = SpeechRecognition ? new SpeechRecognition() : false;

export default {
  data() {
    return {
      error: false,
      speaking: false,
      toggle: false,
      runtimeTranscription: "",
      sentences: [],
      scY: 0
    };
  },
  props: {
    lang: {
      type: String,
      default: "ko-KR"
    },
    text: {
      type: [String, null],
      required: false
    }
  },
  methods: {
    handleScroll: function() {
      this.scY = window.scrollY;
    },
    checkCompatibility() {
      if (!recognition) {
        this.error =
          "Speech Recognition is not available on this browser. Please use Chrome or Firefox";
      }
    },
    endSpeechRecognition() {
      recognition.stop();
      this.toggle = false;
      if (typeof this.sentences == "string") {
        this.$emit("speechend", {
          sentences: this.sentences,
          text: this.sentences
        });
      } else {
        this.$emit("speechend", {
          sentences: this.sentences,
          text: this.sentences.join(". ")
        });
      }
    },
    startSpeechRecognition() {
      if (!recognition) {
        this.error =
          "Speech Recognition is not available on this browser. Please use Chrome or Firefox";
        return false;
      }
      this.toggle = true;
      recognition.lang = this.lang;
      recognition.interimResults = true;

      recognition.addEventListener("speechstart", () => {
        this.speaking = true;
      });

      recognition.addEventListener("speechend", () => {
        this.speaking = false;
      });

      recognition.addEventListener("result", event => {
        const text = Array.from(event.results)
          .map(result => result[0])
          .map(result => result.transcript)
          .join("");
        this.runtimeTranscription = text;
      });

      recognition.addEventListener("end", () => {
        if (this.runtimeTranscription !== "") {
          this.sentences.push(
            this.capitalizeFirstLetter(this.runtimeTranscription)
          );
          this.$emit(
            "update:text",
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
    window.addEventListener("scroll", this.handleScroll);
  }
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");

.description {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, 50%);
}

.micBox {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, 50%);
  background: #2f3640;
  height: 90px;
  border-radius: 40px;
  padding: 10px;
}

.micButton {
  float: right;
  background: #d1ebd9;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: 0.4s;
}

.micInput {
  border: none;
  background: none;
  outline: none;
  float: left;
  color: white;
  font-size: 1.5em;
  transition: 0.4s;
  line-height: 70px;
  width: 0px;
  transform: translateX(20px);
}

.balloon {
  visibility: hidden;
  font-size: 2em;
  font-family: "Do Hyeon", sans-serif;
  text-align: center;
  position: relative;
  width: 0px;
  height: 0px;
  background: #2f3640;
  border-radius: 10px;
}

.balloon textarea {
  line-height: 100%;
}

.area {
  font-size: 1.2em;
  margin: 15px;
}

.transform {
  -webkit-transition: all 0.8s ease;
  -moz-transition: all 0.8s ease;
  -o-transition: all 0.8s ease;
  -ms-transition: all 0.8s ease;
  transition: all 0.8s ease;
}

.transform-active {
  visibility: visible;
  background-color: white;
  height: 350px;
  width: 500px;
}

.transbutton {
  -webkit-transition: all 0.8s ease;
  -moz-transition: all 0.8s ease;
  -o-transition: all 0.8s ease;
  -ms-transition: all 0.8s ease;
  transition: all 0.8s ease;
  margin-top: 160px;
}
</style>
