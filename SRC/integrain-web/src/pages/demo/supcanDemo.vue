<template>
  <div>
    <div>
      <a href="../../../supcan_demo.html">跳转</a>
      <button @click="opsup()">
        连接2
      </button>
    </div>
    <div id="vhtml" v-html="datas">
      <div>{{datas}}</div>
    </div>
  </div>
</template>

<script>
    export default {
      name: "supcan-demo",
      data() {
        return {
          datas: ``,
          url1: ''
        }
      },
      methods: {
        opsup() {
          window.open("http://localhost:8080/integrain-web/supcan_demo.html");
        }
      },
      mounted () {
        this.url1 = 'http://file.xxx.com/group1/M00/0C/F5/xxxxxxxx.html'
      }

    }
</script>

<style scoped>

</style>
