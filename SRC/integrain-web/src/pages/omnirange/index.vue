<template>
  <div class="layout">
    <Layout>
      <IHeader></IHeader>
      <Sider class="isider">
        <ISider></ISider>
      </Sider>
      <Layout class="layout_main">
        <div class="ibread-crumb">
          <IBreadCrumb></IBreadCrumb>
        </div>
        <Content class="icontent">
          <Card>
            <router-view></router-view>
          </Card>
        </Content>
      </Layout>
    </Layout>
  </div>
</template>

<script>
  import ISider from "../../components/ISider/index";
  import IHeader from "../../components/IHeader/index";
  import IBreadCrumb from "../../components/IBreadCrumb/index";

  export default {
    components: {
      IBreadCrumb,
      ISider,
      IHeader
    }
  }
</script>

<style scoped>
</style>
