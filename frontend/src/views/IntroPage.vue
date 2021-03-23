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
      <v-container fill-height pt-10>
        <v-tabs v-model="tab" fixed-tabs dark background-color="#3f3f3f">
          <v-tab v-for="item in items" :key="item.tab">
            {{ item.tab }}
          </v-tab>
        </v-tabs>
        <transition appear @before-enter="beforeEnter" @enter="enter">
          <v-tabs-items v-model="tab">
            <v-tab-item v-for="item in items" :key="item.tab">
              <component v-bind:is="item.content"></component>
            </v-tab-item>
          </v-tabs-items>
        </transition>
        <v-row justify="center">
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
        </v-row>
      </v-container>
    </v-col>
  </v-container>
</template>

<script>
import gsap from "gsap";

import Home from "@/components/intro/Home";
import Contact from "@/components/intro/Contact";

export default {
  components: {
    Home,
    Contact
  },
  data() {
    return {
      logo: require("@/assets/saye_logo.jpg"),
      tab: 0,
      items: [
        { tab: "Home", content: "Home" },
        { tab: "About Us", content: "" },
        { tab: "Our Services", content: "" },
        { tab: "Contact", content: "Contact" }
      ]
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
