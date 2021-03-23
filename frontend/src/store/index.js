import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export const store = new Vuex.Store({
  plugins: [
    // storage 에 넣어준당
    createPersistedState()
  ],
  state: {
    isLogin: false,
    uId: null,
    uName: null,
    uImage: null
  },
  mutations: {
    SET_USER_AUTH_DATA(state, payload) {
      state.isLogin = true;
      state.uId = payload["uId"];
      state.uName = payload["uName"];
      if (!payload["uImage"]) {
        state.uImage =
          "https://d2u3dcdbebyaiu.cloudfront.net/uploads/atch_img/436/8142f53e51d2ec31bc0fa4bec241a919_crop.jpeg";
      } else {
        state.uImage = payload["uImage"];
      }
      // console.log(state.uSalt)
    },
    SET_USER_AUTH_DATA_LOGOUT(state) {
      state.isLogin = false;
      state.uId = null;
      state.uName = null;
      state.uImage = null;
    },
    SET_USER_NAME(state, uName) {
      state.uName = uName;
    },
    SET_USER_IMAGE(state, uImage) {
      state.uImage = uImage;
    }
  },
  actions: {
    FETCH_USER_NAME(context, uName) {
      context.commit("SET_USER_NAME", uName);
    },
    FETCH_USER_IMAGE(context, uImage) {
      context.commit("SET_USER_IMAGE", uImage);
    },
    LOGOUT(context) {
      context.commit("SET_USER_AUTH_DATA_LOGOUT");
      localStorage.removeItem("auth-token");
    },
    DELETE_ACCOUNT(context) {
      context.commit("SET_USER_AUTH_DATA_LOGOUT");
      localStorage.removeItem("auth-token");
    }
  }
});

export default store;
