<template>
  <div class="about">
    <v-container fluid pa-0 ma-0>
      <v-row align="center" justify="center">
        <v-col cols="4"> <lottie :options="defaultOptions"/></v-col>

        <v-col cols="8">
          <div style="height:300px;">
            <h1>SAYE's Dev Engineers</h1>
            <div>
              <h4>
                Jee-hyun, KIM :: #Backend #AWS Server #Leader
              </h4>
              <hr />
              <hr color="orange" />
              <h4>
                Dong-bin, KIM :: #Fullstack #Sentiment Analysis #STT
              </h4>
              <hr />
              <hr color="orange" />
              <h4>
                Yeon-jung, KIM :: #QA #PM
              </h4>
              <hr />
              <hr color="orange" />
              <h4>
                Tae-jin, KIM :: #Frontend
              </h4>
              <hr />
              <hr color="orange" />
              <h4>
                Ho-bin, IM :: #Backend #Database #Spotify API
              </h4>
              <hr />
              <hr color="orange" />
              <h4>
                Min-ho, JANG :: #Data-Analysist #Backend #Django
              </h4>
            </div>
            <a
              href="https://www.notion.so/oct14jh/SAYE-Sing-About-Your-Emotion-301b283ab8ca4fb7b49647eb99e98a20"
              rel="noopener noreferrer"
              target="_blank"
              >Saye's Dev Workplace</a
            >
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import Lottie from "@/components/Lottie.vue";
import * as animationData from "@/assets/lottieFiles/developer.json";

export default {
  components: {
    Lottie
  },
  data() {
    return {
      defaultOptions: { animationData: animationData.default },
      animationSpeed: 1
    };
  }
};
</script>

<style scoped>
.about {
  background-color: #3f3f3f;
  width: 40vw;
}
</style>
