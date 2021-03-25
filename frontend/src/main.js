import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import VueCookies from "vue-cookies";
import VueYoutube from "vue-youtube";
import Buefy from "buefy";
import vueMoment from "vue-moment";
import VueMq from "vue-mq";

window.Kakao.init("c626463e9b79a84d5a8185d060679ef3");

Vue.config.productionTip = false;
Vue.use(VueCookies);
Vue.$cookies.config("7d");

Vue.use(VueYoutube);
Vue.use(Buefy);
Vue.use(vueMoment);
Vue.use(VueMq, {
  breakpoints: {
    mobile: 450,
    tablet: 900,
    laptop: 1250
  }
});

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");
