<template>
  <div style="text-align: center;">
    <img :src="imgUrl">
  </div>
</template>

<script>
  import logoPng from '../../assets/image/logo.png';

    export default {
      name: "wait",
      data() {
        return {imgUrl: logoPng}
      }
    }
</script>

<style scoped>

</style>
