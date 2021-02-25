import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueCookies from 'vue-cookies'
import '@/assets/css/style.css'
import '@/assets/css/chartist.css'

Vue.config.productionTip = false

Vue.use(VueCookies);

new Vue({
  router,
  store,
  VueCookies,
  render: h => h(App)
}).$mount('#app')
