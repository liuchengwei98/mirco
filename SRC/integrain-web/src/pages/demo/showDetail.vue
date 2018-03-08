<template>
  <div>
    <Table border ref="selection" :columns="columns" :data="table_data" style="margin-top:10px;"></Table>
    <Button @click="handleSelectAll(true)" style="margin-top: 15px">全选</Button>
    <Button @click="handleSelectAll(false)" style="margin-top: 15px">取消全选</Button>
  </div>
</template>

<script>
  import {getUserList} from '../../api/demo/testApi/index'

    export default {
      name: "show-detail",
      data () {
        return {
          //表格格式信息
          columns: [
            { type: 'selection', width: 60, align: 'center' },
            { title: '用户名', key: 'userId' },
            { title: '姓名', key: 'userName' },
            { title: '状态', key: 'accountStatus' },
            { title: '公司编码', key: 'corporationId' },
            { title: 'Action', key: 'action',  width: 150, align: 'center',
              //此处用渲染函数增加按钮
              render: (h, params) => {
                return h('div', [
                  h('Button', {
                    props: { type: 'primary', size: 'small' },
                    style: { marginRight: '5px' },
                    on: {
                      click: () => {this.show(params.index) }
                    }
                  }, 'View'),
                  h('Button', {
                    props: { type: 'error', size: 'small' },
                    on: { click: () => { this.remove(params.index) }
                    }
                  }, 'Delete')
                ]);
              }
            }
          ],
          //列表信息
          table_data: []
        }
      },
      methods: {
        //展示用户信息
        show (index) {
          //设置选择的用户ID
          this.$store.commit('SET_USERID', this.table_data[index].userId);

          //跳转到详情页
          this.$router.push('/userMessage');
        },
        //移除当前行用户信息
        remove (index) {
          this.table_data.splice(index, 1);
        },
        //设置是否全选
        handleSelectAll (status) {
          this.$refs.selection.selectAll(status);
        }
      },
      created() {
        //获取用户列表
        getUserList({}).then((response) => {
          this.table_data = response.data.data;
        });
      }
    }
</script>

<style scoped>

</style>
