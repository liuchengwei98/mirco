<template>
  <div>
    <ISider></ISider>
    <div :style="{marginLeft: '200px'}">
      <Header :style="{background: '#fff', boxShadow: '0 2px 3px 2px rgba(0,0,0,.1)'}"></Header>
      <Content :style="{padding: '0 16px 16px'}">
        <IBreadCrumb></IBreadCrumb>
        <Card>
          <div style="height: 600px">
          </div>
        </Card>
      </Content>
    </div>
  </div>
</template>

<script>
  import ISider from "../../components/ISider/index";
  import IBreadCrumb from "../../components/IBreadCrumb/index";

    export default {
      name: "iview-demo",
      components: {
        IBreadCrumb,
        ISider
      }
    }
</script>

<style scoped>


</style>
