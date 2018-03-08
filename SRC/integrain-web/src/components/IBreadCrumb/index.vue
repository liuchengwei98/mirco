<template>
  <Breadcrumb :style="{margin: '10px 0 0 16px'}">
    <BreadcrumbItem v-for="menu in menus" :key="menu">{{menu}}</BreadcrumbItem>
  </Breadcrumb>
</template>

<script>
  export default {
    name: "IBreadCrumb",
    computed: {
      menus() {
        return this.$store.getters.breadcrumbs;
      }
    }
  }
</script>

<style scoped>
  .demo-breadcrumb-separator{
    color: #ff5500;
    padding: 0 5px;
  }
</style>
