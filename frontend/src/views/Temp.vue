<template>
  <v-container fluid id="home">
    <transition appear @before-enter="beforeTextEnter" @enter="textEnter">
      <CenterText />
    </transition>

    <Menus />
  </v-container>
</template>

<script>
import { gsap } from "gsap";

import CenterText from "@/components/home/CenterText";
import Menus from "@/components/home/Menus";

export default {
  name: "Home",
  components: {
    CenterText,
    Menus
  },
  methods: {
    beforeTextEnter: el => {
      el.style.transform = "translate(-200px, -500px)";
      el.style.opacity = 0;
    },
    textEnter: (el, done) => {
      gsap.to(el, {
        duration: 1.5,
        x: 0,
        y: 0,
        xPercent: -50,
        yPercent: -50,
        opacity: 1,
        ease: "elastic.inOut",
        onComplete: done
      });
    }
  }
};
</script>

<style scoped>
#home {
  z-index: 1;
}
</style>
