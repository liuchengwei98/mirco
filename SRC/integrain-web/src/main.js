import Vue from 'vue';
import iView from 'iview';
import 'babel-polyfill';
import 'iview/dist/styles/iview.css';
import './styles/index.css'
import 'core-js/fn/date/now';
import App from './App';
import VTree from 'vue-tree-halower';

Vue.use(iView);
Vue.use(VTree);

import router from './router/index'
import store from './store/index';

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',   /* 给Vue实例初始一个App组件作为template 相当于默认组件 */
  components: { App }  /* 注册引入的组件App.vue */
});

Vue.filter('dCurrency', function(value) {
  return '￥'+value
});
Vue.filter('dTofixed', function(value) {

});
