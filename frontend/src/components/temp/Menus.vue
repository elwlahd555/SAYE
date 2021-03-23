<template>
  <v-container fluid fill-height>
    <transition-group
      appear
      tag="div"
      @before-enter="beforeMenuEnter"
      @enter="menuEnter"
    >
      <div
        v-for="(data, idx) in animationData"
        :key="data.icon"
        :data-index="idx"
      >
        <div class="menu" :class="data.dataStyle">
          <lottie :options="data" />
          <v-btn
            depressed
            fab
            x-large
            :class="data.btnStyle"
            :color="data.iconColor"
            @mouseenter="hoverEffect"
            @click="leaveAnim(data.navTo, data.btnStyle)"
          >
            <v-icon>{{ data.icon }}</v-icon>
          </v-btn>
          <div :class="data.btnHover"></div>
        </div>
      </div>
    </transition-group>
  </v-container>
</template>

<script>
import Lottie from "@/components/Lottie.vue";
import { gsap } from "gsap";

import * as data1 from "@/assets/lottieFiles/basicSearch.json";
import * as data2 from "@/assets/lottieFiles/emotionAnalysis.json";
import * as data3 from "@/assets/lottieFiles/personalAnalysis.json";
import * as data4 from "@/assets/lottieFiles/ranking.json";

export default {
  name: "app",
  components: {
    lottie: Lottie
  },
  data() {
    return {
      animationData: [
        {
          animationData: data1.default,
          dataStyle: "topleft",
          icon: "mdi-magnify",
          iconColor: "#d1ebd9",
          btnStyle: "btn1",
          btnHover: "btn1Hover",
          navTo: "/basicSearch"
        },
        {
          animationData: data2.default,
          dataStyle: "topright",
          icon: "mdi-pencil",
          iconColor: "#d2e3fc",
          btnStyle: "btn2",
          btnHover: "btn2Hover",
          navTo: "/board"
        },
        {
          animationData: data3.default,
          dataStyle: "bottomleft",
          icon: "mdi-share",
          iconColor: "#ffefc3",
          btnStyle: "btn3",
          btnHover: "btn3Hover",
          navTo: "/myplaylist"
        },
        {
          animationData: data4.default,
          dataStyle: "bottomright",
          icon: "mdi-finance",
          iconColor: "#f8d2ce",
          btnStyle: "btn4",
          btnHover: "btn4Hover",
          navTo: "/ranking"
        }
      ],
      animationSpeed: 1
    };
  },
  methods: {
    beforeMenuEnter: el => {
      el.style.opacity = 0;
    },
    menuEnter: (el, done) => {
      gsap.to(el, {
        duration: 1.5,
        delay: (el.dataset.index + 1) * 0.07,
        opacity: 1,
        ease: "circ.out",
        onComplete: done
      });
    },
    hoverEffect: el => {
      console.log(el);
    },
    leaveAnim(path) {
      // gsap.to(`.${el}`, {
      //   duration: 0.5,
      //   x: 200,
      //   y: 200,
      //   ease: "sine.easeOut"
      // });
      // setTimeout(() => {
      this.$router.push(path);
      // }, 2000);
    }
  }
};
</script>

<style scoped>
.menu {
  position: absolute;
  transform: translate(-50%, -40%);
  height: 30%;
  width: 25%;
}
.topleft {
  left: 20%;
  top: 25%;
}
.topright {
  left: 80%;
  top: 25%;
  z-index: 1;
}
.bottomleft {
  left: 20%;
  top: 75%;
}
.bottomright {
  left: 80%;
  top: 75%;
}

.btn1 {
  position: absolute;
  left: 70%;
  bottom: 25%;
  transform: translate(70%, 25%);
}
.btn2 {
  position: absolute;
  left: 10%;
  bottom: 25%;
  transform: translate(-80%, 25%);
}
.btn3 {
  position: absolute;
  left: 70%;
  bottom: 75%;
  transform: translate(70%, 25%);
}
.btn4 {
  position: absolute;
  left: 10%;
  bottom: 75%;
  transform: translate(-80%, 25%);
}
</style>
