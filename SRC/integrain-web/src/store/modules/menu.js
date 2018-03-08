const menu = {
  state: {
    //侧边菜单数组
    menus: [],
    //面包屑数组
    breadcrumbs: [],
    //顶部菜单数组
    headMenu: [],
    //活跃的侧边菜单
    activeMenu: '',
    //打开的侧边副菜单
    openMenus: [],
    //活跃的顶部菜单
    activeHeadMenu: ''
  },
  getters: {
    menus: state => state.menus,
    breadcrumbs: state => state.breadcrumbs,
    activeMenu: state => state.activeMenu,
    openMenus: state => state.openMenus,
    headMenu: state => state.headMenu,
    activeHeadMenu: state => state.activeHeadMenu
  },
  mutations: {
    SET_MENUS: (state, menus) => {
      state.menus = menus;
      state.breadcrumbs = [];
      state.activeMenu = '';
      state.openMenus = [];
    },
    SET_HEADMENUS: (state, headMenu) => {
      state.headMenu = headMenu;
      state.menus = [];
      state.breadcrumbs = [];
      state.activeMenu = '';
      state.openMenus = [];
    },
    SET_BREADCRUMBS: (state, breadcrumbs) => {
      state.breadcrumbs = breadcrumbs;
    },
    SET_ACTIVEMENU: (state, activeMenu) => {
      state.activeMenu = activeMenu;
    },
    SET_OPENMENUS: (state, openMenus) => {
      state.openMenus = openMenus;
    },
    PUSH_OPENMENUS: (state, menu) => {
      state.openMenus.push(menu);
    },
    SET_ACTIVEHEADMENU: (state, activeHeadMenu) => {
      let oldActiveHeadMenu = state.activeHeadMenu;

      if (oldActiveHeadMenu !== activeHeadMenu) {
        state.activeHeadMenu = activeHeadMenu;

        state.breadcrumbs = [];
        state.activeMenu = '';
        state.openMenus = [];
      }
    }
  },
  actions: {
    //动态修改导航菜单和面包屑
    changeMenu ({commit, state}, obj) {
      let breadcrumb = [];
      let items = state.menus;
      for (let i = 0; i < items.length; i++) {
        let item = items[i];
        if (item.menus) {
          let menus = item.menus;
          for (let j = 0; j < menus.length; j++) {
            if (menus[j].path === obj.path) {
              if (obj.ifMenu) {
                //修改菜单的展开和选中状态
                commit('SET_ACTIVEMENU', menus[j].path);
                commit('PUSH_OPENMENUS', item.opt);
              }

              //修改面包屑的菜单
              breadcrumb.push(item.name);
              breadcrumb.push(menus[j].name);
              break
            }
          }
        }
      }

      //设置面包屑
      commit('SET_BREADCRUMBS', breadcrumb);
    }
  }
};

export default menu;
