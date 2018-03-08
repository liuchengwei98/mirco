<template>
  <Header class="iheader">
    <Menu mode="horizontal" theme="dark" :active-name="activeHeadMenu" @on-select="changeHeadMenu">
      <div class="layout-logo" @click="goHome()"></div>
      <div class="layout-nav">
        <MenuItem v-for="headMenu in heandMenus" :name="headMenu.name" :key="headMenu.name">
          <Icon type="ios-navigate"></Icon>
          {{headMenu.des}}
        </MenuItem>
      </div>
      <div class="layout-user">
        <Row style="height:20px;">
          <Col>
            <Icon type="person" />{{userName}}
          </Col>
        </Row>
      </div>
    </Menu>
  </Header>
</template>
<script>
  export default {
    name: "IHeader",
    data() {
      return {
        heandMenus: [
          {
            des: '用户管理',
            name: '/userManage'
          },
          {
            des: '功能菜单',
            name: '/actionMenus'
          },
          {
            des: '功能管理',
            name: '/actionManage'
          }
        ],
        userName: '系统管理员'
      }
    },
    computed: {
      activeHeadMenu() {
        return this.$store.getters.activeHeadMenu;
      }
    },
    methods: {
      changeHeadMenu(path) {
        let activeHeadMenu = this.$store.getters.activeHeadMenu;

        if (activeHeadMenu !== path) {
          this.$store.commit('SET_ACTIVEHEADMENU', path);

          //卡片指向等待页面
          this.$router.push('/wait');
        }
      },
      logout() {
        console.log('logout');
      },
      goHome() {
        //跳转到首页
        this.$router.push('/');
      }
    },
    created() {
      //设置顶部菜单
      let heandMenus = this.$store.getters.heandMenus;

      if (!heandMenus) {
        this.$store.commit('SET_HEADMENUS', this.heandMenus);
      }

      //默认选中功能菜单
      let activeHeadMenu = this.$store.getters.activeHeadMenu;

      if (!activeHeadMenu) {
        this.$store.commit('SET_ACTIVEHEADMENU', '/actionMenus');
      }
    }
  }
</script>
