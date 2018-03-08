import Vue from 'vue'
import VueRouter from 'vue-router'
import 'core-js/fn/date/now';

//import IviewDemo from '../pages/demo/iviewDemo.vue'
//import QueryTableDemo from '../pages/demo/queryTableDemo.vue'
//import TableDemo from '../pages/demo/tableDemo.vue'
//import FormDemo from '../pages/demo/formDemo.vue'
//import omnirange from '../pages/omnirange/index'
//import testApi from '../pages/demo/testApiService'

const IviewDemo = () => import('../pages/demo/iviewDemo.vue');
const QueryTableDemo = () => import('../pages/demo/queryTableDemo.vue');
const TableDemo = () => import('../pages/demo/tableDemo.vue');
const FormDemo = () => import('../pages/demo/formDemo.vue');
const WaitPage = () => import('../pages/demo/waitPage.vue');
const omnirange = () => import('../pages/omnirange/index');
const testApi = () => import('../pages/demo/testApiService');
const SupcanDemo = () => import('../pages/demo/supcanDemo.vue');
const PluginDemo = () => import('../pages/demo/pluginDemo.vue');
const ShowDetail = () => import('../pages/demo/showDetail.vue');
const UserMessage = () => import('../pages/demo/userMessage.vue');
const HomePage = () => import('../pages/demo/homePage.vue');


Vue.use(VueRouter);
const routes = [
  {
    path: '/',
    component: HomePage
  },
  {
    path: '/',
    component: omnirange,
    name: '导航一',
    //iconCls: 'el-icon-message',//图标样式class
    children: [
      { path: '/wait', component: WaitPage, name: 'waitPage' },
      { path: '/QueryTableDemo', component: QueryTableDemo, name: 'QueryTableDemo', hidden: true },
      { path: '/tableDemo', component: TableDemo, name: 'tableDemo' },
      { path: '/testApi', component: testApi, name: 'testApi' },
      { path: '/formDemo', component: FormDemo, name: 'formDemo' },
      { path: '/supcanDemo', component: SupcanDemo, name: 'supcanDemo' },
      { path: '/pluginDemo', component: PluginDemo, name: 'pluginDemo' },
      { path: '/showDetail', component: ShowDetail, name: 'showDetail' },
      { path: '/userMessage', component: UserMessage, name: 'userMessage' }
    ]
  },
  {
    path: '/iviewDemo',
    component: IviewDemo
  }
];
export default new VueRouter({
  routes
})
