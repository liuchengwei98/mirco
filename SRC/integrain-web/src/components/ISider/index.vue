<template>
  <div>
      <div v-for="item in items[activeHeadMenu]">
        <Menu :active-name="activeMenu" theme="light" width="auto" :open-names="openMenus" @on-select="changeItem">
          <Submenu :name="item.opt">
            <template slot="title">
              {{item.name}}
            </template>
            <MenuItem v-if="item.menus" v-for="menu in item.menus" :name="menu.path" :key="menu.path">{{menu.name}}</MenuItem>
          </Submenu>
        </Menu>
      </div>
  </div>
</template>

<script>
  export default {
    name: "ISider",
    data() {
      return {
        items: {
          '/actionMenus': [
            {
              name: '演示',
              opt: 'OptionA',
              menus: [
                {
                  name: '查询列表',
                  path: '/queryTableDemo'
                },
                {
                  name: '列表',
                  path: '/tableDemo'
                },
                {
                  name: '表单',
                  path: '/formDemo'
                },
                {
                  name: '插件演示',
                  path: '/pluginDemo'
                },
                {
                  name: '硕正演示',
                  path: '/supcanDemo'
                },
                {
                  name: '展示详情',
                  path: '/showDetail'
                },
                {
                  name: '测试接口页',
                  path: '/testApi'
                }
              ]
            },
            {
              name: '应用',
              opt: 'OptionB'
            }
          ],
          '/userManage': [
            {
              name: '用户管理',
              opt: 'OptionUser'
            },
            {
              name: '角色管理',
              opt: 'OptionRole'
            }
          ]
        }
      }
    },
    computed: {
      activeMenu() {
        return this.$store.getters.activeMenu;
      },
      openMenus() {
        return this.$store.getters.openMenus;
      },
      activeHeadMenu() {
        return this.$store.getters.activeHeadMenu;
      }
    },
    methods: {
      changeItem(path) {
        //改变面包屑
        let obj = {'path': path, 'ifMenu': false}
        this.$store.dispatch('changeMenu', obj)
        //修改卡片
        this.$router.push(path);
      }
    },
    created() {
      //设置菜单
      this.$store.commit('SET_MENUS', this.items[this.activeHeadMenu]);
      //改变导航菜单栏的展开和选中状态
      if (this.$route.path) {
        let obj = {'path': this.$route.path, 'ifMenu': true}
        this.$store.dispatch('changeMenu', obj);
      }
    }
  }
</script>

<style scoped>
</style>
