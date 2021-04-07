<template>
  <v-container fluid pa-0>
    <div id="page" class="site">
      <HomeNav />

      <main id="content" class="site-content" role="main">
        <Slide0 />

        <section id="panels">
          <div id="panels-container" style="width: 300%">
            <Slide1 />
            <Slide2 />
            <Slide3 />
          </div>
        </section>
      </main>
    </div>
  </v-container>
</template>

<script>
import { gsap, ScrollTrigger, ScrollToPlugin } from "gsap/all";
gsap.registerPlugin(ScrollTrigger, ScrollToPlugin);

import HomeNav from "@/components/home/HomeNav";
import Slide0 from "@/components/home/Slide0";
import Slide1 from "@/components/home/Slide1";
import Slide2 from "@/components/home/Slide2";
import Slide3 from "@/components/home/Slide3";

export default {
  name: "Home",
  components: {
    HomeNav,
    Slide0,
    Slide1,
    Slide2,
    Slide3,
  },
  mounted: function () {
    this.scrollAnimation();
  },
  methods: {
    scrollAnimation() {
      /* Main navigation */
      const panelsSection = document.querySelector("#panels");
      const panelsContainer = document.querySelector("#panels-container");
      document.querySelectorAll(".anchor").forEach((anchor) => {
        anchor.addEventListener("click", function (e) {
          e.preventDefault();

          const targetElem = document.querySelector(
            e.target.getAttribute("href")
          );
          if (
            targetElem &&
            panelsContainer.isSameNode(targetElem.parentElement)
          ) {
            const containerOffset =
              panelsSection.offsetTop + targetElem.offsetLeft;

            gsap.to(window, {
              scrollTo: {
                y: containerOffset,
                autoKill: false,
              },
              duration: 1,
            });
          } else {
            gsap.to(window, {
              scrollTo: {
                y: targetElem,
                autoKill: false,
              },
              duration: 1,
            });
          }
        });
      });

      /* Panels */
      const panels = gsap.utils.toArray("#panels-container .panel");
      gsap.to(panels, {
        xPercent: -100 * (panels.length - 1),
        ease: "none",
        scrollTrigger: {
          trigger: "#panels-container",
          pin: true,
          start: "top top",
          scrub: 1,
          snap: {
            snapTo: 1 / (panels.length - 1),
            duration: { min: 0.1, max: 0.1 },
          },
          end: () => "+=" + (panelsContainer.offsetWidth - innerWidth),
        },
      });
    },
  },
};
</script>

<style scoped>
.full-screen {
  display: block;
  width: 100%;
  height: 100vh;
  overflow: hidden;
}

#intro {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

#panels #panels-container {
  height: 100vh;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-wrap: nowrap;
  flex-wrap: nowrap;
  padding: 0;
  overflow: hidden;
  background-color: #ddd;
}
#panels #panels-container .panel {
  position: relative;
  width: 100%;
  height: 100vh;
  overflow: hidden;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  color: #333;
  text-align: left;
  border-right: 1px solid #ddd;
}
#panels #panels-container .panel img {
  max-width: 100%;
  height: auto;
  display: block;
}
#panels #panels-container .panel .panels-navigation {
  width: 100%;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: end;
  -ms-flex-pack: end;
  justify-content: flex-end;
}
#panels #panels-container .panel .anchor-panel,
#panels #panels-container .panel .nav-panel {
  color: #000;
  text-transform: uppercase;
  margin-right: 2rem;
}
#panels #panels-container .panels-navigation {
  position: absolute;
  width: 100%;
  bottom: 2rem;
  right: 2rem;
  background-color: black;
}
</style>
