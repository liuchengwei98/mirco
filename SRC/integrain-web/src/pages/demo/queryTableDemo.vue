<template>
  <div>
    <Row>
      <Col span="6" offset="10">
      <Input v-model="name" placeholder="Enter your name" style="width:200px;"></Input>
      </Col>
      <Col span="6">
      <Select v-model="city" placeholder="Select your city" style="width:200px;">
        <Option value="New York">New York</Option>
        <Option value="London">London</Option>
        <Option value="Sydney">Sydney</Option>
      </Select>
      </Col>
      <Col span="2" >
      <Button @click="query(name, city)">查询</Button>
      </Col>
    </Row>
    <Row>
      <Col span="24">
      <Table border ref="selection" :columns="columns" :data="table_data" style="margin-top: 25px"></Table>
      </Col>
    </Row>
    <Row>
      <Col span="12" offset="12">
      </br>
      <Page :total="total" :page-size="10" @on-change="handleChange" show-total show-elevator></Page>
      </Col>
    </Row>
  </div>
</template>

<script>
  import "lodash"
  import {getZiHuNews} from '../../api/demo/zhihu/index';

  export default {
    data () {
      return {
        columns: [
          { title: 'id', key: 'id', width: 100 },
          { title: 'ga_prefix', key: 'ga_prefix', width: 100 },
          { title: 'title', key: 'title' },
          { title: 'Action', key: 'action', width: 150, align: 'center',
            //此处用渲染函数增加按钮
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: { type: 'primary', size: 'small' },
                  style: { marginRight: '5px' },
                  on: {
                    click: () => {
                      this.show(params.index)
                    }
                  }
                }, 'View'),
                h('Button', {
                  props: { type: 'error', size: 'small' },
                  on: {
                    click: () => {
                      this.remove(params.index)
                    }
                  }
                }, 'Delete')
              ]);
            }
          }
        ],
        table_data: [],
        all_table: [],
        total: '',
        name: '',
        city: ''
      }
    },
    methods: {
      //展示详情
      show (index) {
        this.$Modal.info({
          title: 'User Info',
          content: `id：${this.table_data[index].id}<br>ga_prefix：${this.table_data[index].ga_prefix}<br>title：${this.table_data[index].title}`
        })
      },
      //移除
      remove (index) {
        this.table_data.splice(index, 1);
      },
      query(name, city) {
        console.log(name, city)
      },
      //切换分页
      handleChange(page) {
        this.table_data.splice(0, this.table_data.length);
        this.table_data=this.all_table.slice((page - 1) * 10,page * 10);
      }
    },
    created () {   /* 这个是vue的钩子函数，当new Vue()实例创建完毕后执行的函数 */
      getZiHuNews({}).then((response) => {
        console.log(response.data.stories);//,,
        //this.table_data = _.drop(response.data.stories, response.data.stories.length - 5);
        this.all_table = response.data.stories;
        //默认展示第一页
        this.handleChange(1)
        //赋值总页数
        this.total = response.data.stories.length;
      });
    }
  }

</script>
<style scoped>
</style>
