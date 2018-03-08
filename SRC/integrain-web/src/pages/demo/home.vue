<template lang="html">
  <div class="container">
    <home-header></home-header>   <!--  展示引入的header组件 -->
    <div class="content">
      <ul class="cont-ul">
        <!-- list组件展示区，并用v-for来将数据遍历，:xx="xxx" 是用来给子组件传递数据的 -->
        <list v-for="(item, index) in items" :price="item.price" :title="item.title" :img="item.img" v-bind:key="index"></list>
      </ul>
    </div>
    <common-footer></common-footer>  <!--  展示引入的footer组件 -->
  </div>
</template>
<script>
  import HomeHeader from '../../components/Test/HomeHeader'   /* 本页面中用到了HomeHeader组件，所以就需要在这里引入一下 */
  import CommonFooter from '../../components/Test/commonFooter'
  import List from '../../components/Test/list'

  export default {
    data () {
      return {
        items: []   /* 定义一个空数组数据items */
      }
    },
    components: {
      HomeHeader,
      CommonFooter,
      List
    }
  }
</script>

<style lang="css" scoped>
  .container {
    width: 100%;
    margin: 0 auto;
  }
  .content {
    margin-bottom: 1.8rem;
  }
  .cont-ul {
    padding-top: 0.5rem;
    background-color: #ccc;
  }
  .cont-ul::after {
    content: '';
    display: block;
    clear: both;
    width: 0;
    height: 0;
  }
</style>
