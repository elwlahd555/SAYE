<template>
  <v-container fluid fill-height pa-0>
    <v-col align="center">
      <!-- Left side -->
      <v-col>
        <v-img :src="logo" width="600"></v-img>
      </v-col>
    </v-col>
    <v-col class="rightSide">
      <!-- Right side -->
      <v-container fill-height>
        <v-row align="center">
          <Navbar />
        </v-row>

        <transition appear @before-enter="beforeEnter" @enter="enter">
          <TextAboutPage />
        </transition>

        <v-col align="center">
          <v-btn
            color="white accent-2"
            :to="{ name: 'HomePage' }"
            rounded
            large
          >
            <v-icon left>
              mdi-star
            </v-icon>
            Start Now
          </v-btn>
        </v-col>
      </v-container>
    </v-col>
  </v-container>
</template>

<script>
import gsap from "gsap";

import TextAboutPage from "@/components/intro/TextAboutPage";
import Navbar from "@/components/intro/Navbar";

export default {
  components: {
    TextAboutPage,
    Navbar
  },
  data() {
    return {
      logo: require("@/assets/saye_logo.jpg")
    };
  },
  methods: {
    beforeEnter: el => {
      console.log("before enter");
      el.style.transform = "translateY(-200px)";
      el.style.opacity = 0;
    },
    enter: (el, done) => {
      console.log("starting to enter");
      gsap.to(el, {
        duration: 1.5,
        y: 0,
        opacity: 1,
        ease: "bounce.out",
        onComplete: done
      });
    }
  }
};
</script>

<style scoped>
.rightSide {
  height: 100vh;
  background-color: #3f3f3f;
  color: white;
}
</style>
