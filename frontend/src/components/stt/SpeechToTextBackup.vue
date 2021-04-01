<template>
  <div>
    <v-card>
      <v-card-text>
        <v-layout row wrap justify-space-around>
          <v-flex xs8 sm9 text-xs-center>
            <p v-if="error" class="grey--text">{{ error }}</p>
            <div v-else class="mb-0">
              <ul v-if="sentences.length > 0">
                <span v-for="sentence in sentences" :key="sentence"
                  >{{ sentence }}.
                </span>
              </ul>
              <span>{{ runtimeTranscription }}</span>
            </div>
          </v-flex>
          <v-flex xs2 sm1 text-xs-center>
            <v-btn
              dark
              @click.stop="
                toggle ? endSpeechRecognition() : startSpeechRecognition()
              "
              icon
              :color="!toggle ? 'grey' : speaking ? 'red' : 'red darken-3'"
              :class="{ 'animated infinite pulse': toggle }"
            >
              <v-icon>{{
                toggle ? "mdi-text-to-speech" : "mdi-text-to-speech-off"
              }}</v-icon>
            </v-btn>
          </v-flex>
        </v-layout>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
let SpeechRecognition =
  window.SpeechRecognition || window.webkitSpeechRecognition;
let recognition = SpeechRecognition ? new SpeechRecognition() : false;
export default {
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
      this.$emit("speechend", {
        sentences: this.sentences,
        text: this.sentences.join(". ")
      });
      // console.log(this.text);
      console.log(this.sentences);
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

<style scoped>
.description {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, 50%);
}
.searchBox {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, 50%);
  background: #2f3640;
  height: 90px;
  border-radius: 40px;
  padding: 10px;
}

.searchBox:hover > .searchInput {
  width: 440px;
  padding: 0 6px;
}

.searchBox:hover > .searchBtton {
  background: white;
  color: #d1ebd9;
}

.searchButton {
  float: right;
  background: #d1ebd9;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: 0.4s;
}

.searchInput {
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

@media screen and (max-width: 1250px) {
  .searchBox:hover > .searchInput {
    width: 300px;
    padding: 0 6px;
  }
}
</style>
