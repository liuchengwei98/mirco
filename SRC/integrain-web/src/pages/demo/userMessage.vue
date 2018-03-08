<template>
  <div>
    <Row>
      <Col span="6">
      用户名：<Input v-model="userId" style="width:200px;"></Input>
      </Col>
      <Col span="6">
      姓名：<Input v-model="userName" style="width:200px;"></Input>
      </Col>
      <Col span="6">
      状态：<Input v-model="accountStatus" style="width:200px;"></Input>
      </Col>
      <Col span="6">
      公司编码：<Input v-model="corporationId" style="width:200px;"></Input>
      </Col>
    </Row>
    <Row>
      <Col span="24">
      <Table border ref="role_selection" :columns="role_columns" :data="role_data" style="margin-top:20px;"></Table>
      </Col>
    </Row>
    <Row>
      <Col span="24">
      <Table border ref="resource_selection" :columns="resource_columns" :data="resource_data" style="margin-top:20px;"></Table>
      </Col>
    </Row>
    <Row>
      <Col span="4" offset="20">
      <Button @click="goBack()" style="margin-top:20px;">返回</Button>
      </Col>
    </Row>
  </div>
</template>

<script>
  import {getUsers} from '../../api/demo/testApi/index'

  export default {
      name: "user-message",
      data() {
        return {
          //表格格式信息
          role_columns: [
            { title: '用户名', key: 'userId' },
            { title: '角色标识', key: 'roleId' },
            { title: '角色名', key: 'roleName' },
            { title: '角色别名', key: 'roleAlias' }
          ],
          //表格格式信息
          resource_columns: [
            { title: '用户名', key: 'userId' },
            { title: '资源标识', key: 'resourceId' },
            { title: '资源名', key: 'resourceName' },
            { title: '资源别名', key: 'resourceAlias' }
          ],
          //用户名
          userId: '',
          //姓名
          userName: '',
          //状态
          accountStatus: '',
          //公司编码
          corporationId: '',
          //角色列表信息
          role_data: [],
          //资源列表信息
          resource_data: []
        }
      },
      methods: {
        //返回上一页
        goBack() {
          history.back(-1);
        }
      },
      created() {
        //获取用户ID
        let userId = this.$store.getters.userId;

        //获取用户详情
        getUsers(userId).then((response) => {
          this.role_data = response.data.data.roleList;
          this.resource_data = response.data.data.resourceList;
          this.userId = response.data.data.userId;
          this.userName = response.data.data.userName;
          this.accountStatus = response.data.data.accountStatus;
          this.corporationId = response.data.data.corporationId;
        });
      }
    }
</script>

<style scoped>

</style>
