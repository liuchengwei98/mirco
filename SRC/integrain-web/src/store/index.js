import Vue from 'vue';
import Vuex from 'vuex';
import menu from './modules/menu';
import userMessage from './modules/userMessage';

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    //菜单模块
    menu,
    //用户信息展示demo模块
    userMessage
  }
});

export default store
