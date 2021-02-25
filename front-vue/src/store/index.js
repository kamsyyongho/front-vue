import Vue from "vue";
import Vuex from "vuex";
// import axios from 'axios'
import constants from "@/constants";
import createPersistedState from "vuex-persistedstate";
import router from "@/router/index";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState({ storage: window.sessionStorage })],
  state: {
    constants: constants,
    isLoggedIn: false,
    dossId: ''
  },
  getters: {
    CONSTANTS: (state) => {
      return state.constants;
    },
  },
  mutations: {
    LOGGEDIN(state, dossId) {
      state.isLoggedIn = true;
      state.dossId = dossId;
    },
    LOGGEDOUT(state) {
      state.isLoggedIn = false;
      router.push("/login");
    },
  },
  actions: {
    LOGGEDIN({ commit }, payload) {
      commit("LOGGEDIN", payload.dossId);
    },
    LOGGEDOUT({ commit }) {
      commit("LOGGEDOUT");
    },
  },
  modules: {},
});
