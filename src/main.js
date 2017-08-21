//引入资源文件
import './assets/lib/bootstrap/css/bootstrap.css';
import './assets/lib/bootstrap/js/bootstrap.min.js';
import Vue from 'vue'
import App from './App'
import router from './router'

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})

