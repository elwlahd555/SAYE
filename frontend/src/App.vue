<template>
  <v-app>
    <v-main>
      <Header v-if="!introPage" />
      <Aside v-if="!introPage" />
      <transition name="fade" mode="out-in">
        <router-view />
      </transition>
    </v-main>
  </v-app>
</template>

<script>
import Header from "@/components/comm/Header";
import Aside from "@/components/comm/Aside";

export default {
  name: "App",

  components: {
    Header,
    Aside
  },

  data: () => ({
    //
  }),
  computed: {
    introPage() {
      return this.$route.name == "IntroPage";
    }
  }
};
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition-duration: 0.3s;
  transition-property: opacity;
  transition-timing-function: ease;
}

.fade-enter,
.fade-leave-active {
  opacity: 0;
}
</style>
