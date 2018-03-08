<template lang="html">
  <div class="head">
    <div class="header">
      <a href="javascript:;" class="go-demo" @click="goDemo">演示</a>
      <h4 class="header-cont">主页</h4>
    </div>
  </div>
</template>
<script>
  export default {
    methods: {
      goDemo() {
        this.$router.push({ path: '/iviewDemo' })
      }
    }
  }
</script>
<style lang="css" scoped>
  .head{
    width: 100%;
    height: 2rem;
  }
  .header{
    width: 100%;
    height: 2rem;
    position: fixed;
    left: 0;
    top: 0;
    background-color: #fff;
    border-bottom: 2px solid #ff8000;
  }
  .header h4 {
    width: 100%;
    text-align: center;
    line-height: 2rem;
    font-size: 1.4rem;
  }
  .header .go-demo {
    width: 2rem;
    height: 2rem;
    text-align: center;
    color: #ccc;
    font-size: 0.8rem;
    float: left;
    line-height: 2rem;
    margin-left: 1rem;
    position: absolute;
    left: 0;
    top: 0;
  }
</style>

