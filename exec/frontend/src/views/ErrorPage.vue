<template>
  <v-container>
    <lottie :options="defaultOptions" style="max-width: 700px" />

    <v-btn
      class="ro"
      @click="hasHistory() ? $router.go(-1) : $router.push('/')"
      color="white"
      depressed
    >
      <v-icon left>mdi-arrow-left-circle</v-icon>
      Back to Home
    </v-btn>
  </v-container>
</template>

<script>
import Lottie from "@/components/Lottie.vue";
import * as animationData from "@/assets/lottieFiles/404error.json";

export default {
  name: "app",
  components: {
    lottie: Lottie
  },
  data() {
    return {
      defaultOptions: { animationData: animationData.default },
      animationSpeed: 1
    };
  },
  methods: {
    hasHistory() {
      return window.history.length > 2;
    }
  }
};
</script>

<style scoped>
.homeBtn {
  position: absolute;
  bottom: 10%;
}
</style>
