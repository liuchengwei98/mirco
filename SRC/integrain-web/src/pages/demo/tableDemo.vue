<template>
  <div>
    <Row type="flex" justify="end" class="code-row-bg" :gutter="16">
      <Col>
      <Button @click="add()" style="margin-top: 15px">增加</Button>
      </Col>
    </Row>
    <Table border ref="selection" :columns="columns" :data="table_data" style="margin-top:10px;"></Table>
    <Button @click="handleSelectAll(true)" style="margin-top: 15px">全选</Button>
    <Button @click="handleSelectAll(false)" style="margin-top: 15px">取消全选</Button>

    <Modal
      v-model="formModal"
      title="信息维护"
      @on-ok="onOk()"
      loading="loading">
      <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
        <FormItem label="Name" prop="name">
          <Input v-model="formValidate.name" placeholder="Enter your name" style="width: 200px;"></Input>
        </FormItem>
        <FormItem label="City" prop="city">
          <Select v-model="formValidate.city" placeholder="Select your city" style="width: 200px;">
            <Option value="New York">New York</Option>
            <Option value="London">London</Option>
            <Option value="Sydney">Sydney</Option>
          </Select>
        </FormItem>
        <FormItem label="Desc" prop="desc">
          <Input v-model="formValidate.desc" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="Enter something..." style="width: 300px;"></Input>
        </FormItem>
      </Form>
      <div slot="footer">
        <Button @click="formModal = false">取消</Button>
        <Button @click="onOk()">确定</Button>
      </div>
    </Modal>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        //对话框中填入的数据
        formValidate: {
          name: '',
          city: '',
          desc: ''
        },
        //对话框中表单内容校验标准
        ruleValidate: {
          name: [
            { required: true, message: 'The name cannot be empty', trigger: 'blur' }
          ],
          city: [
            { required: true, message: 'Please select the city', trigger: 'change' }
          ],
          desc: [
            { required: true, message: 'Please enter a personal introduction', trigger: 'blur' },
            { type: 'string', min: 20, message: 'Introduce no less than 20 words', trigger: 'blur' }
          ]
        },
        //控制对话框是否显示
        formModal: false,
        //表格格式信息
        columns: [
          { type: 'selection', width: 60, align: 'center' },
          { title: 'Name', key: 'name',
            render: (h, params) => {
              return h('div', [
                h('Icon', { props: { type: 'person' } }),
                h('strong', params.row.name)
              ]);
            }
          },
          { title: 'City', key: 'city' },
          { title: 'Desc', key: 'desc' },
          { title: 'Action', key: 'action',  width: 180, align: 'center',
            //此处用渲染函数增加按钮
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: { type: 'primary', size: 'small' },
                  style: { marginRight: '5px' },
                  on: { click: () => { this.show(params.index) } }
                }, 'View'),
                h('Button', {
                  props: { type: 'primary', size: 'small' },
                  style: { marginRight: '5px' },
                  on: {
                    click: () => {
                      //给对话框中的信息赋初始值
                      this.formValidate.name = this.table_data[params.index]?this.table_data[params.index].name:'';
                      this.formValidate.city = this.table_data[params.index]?this.table_data[params.index].city:'';
                      this.formValidate.desc = this.table_data[params.index]?this.table_data[params.index].desc:'';
                      this.data_index = params.index;
                      //打开对话框
                      this.formModal = true;
                    }
                  }
                }, 'Edit'),
                h('Button', {
                  props: { type: 'error', size: 'small' },
                  on: { click: () => { this.remove(params.index) }
                  }
                }, 'Delete')
              ]);
            }
          }
        ],
        //初始信息
        table_data: [{ name: 'Joe Black', city: 'Bei Jing', desc: 'Sydney No. 1 Lake Park' },
                     { name: 'Jon Snow', city: 'New York', desc: 'Ottawa No. 2 Lake Park' }],
        //指向当前行的指针
        data_index: -1
      }
    },
    methods: {
      //显示用户信息
      show (index) {
        this.$Modal.info({
          title: 'User Info',
          content: `Name：${this.table_data[index].name}<br>city：${this.table_data[index].city}<br>desc：${this.table_data[index].desc}`
        })
      },
      //移除当前行用户信息
      remove (index) {
        this.table_data.splice(index, 1);
      },
      //设置是否全选
      handleSelectAll (status) {
        this.$refs.selection.selectAll(status);
      },
      //新增按钮响应
      add () {
        this.formValidate.name = '';
        this.formValidate.city = '';
        this.formValidate.desc = '';

        let length = this.$refs.selection.data.length;
        this.data_index = length;
        this.formModal = true;
      },
      //对话框中的确定按钮
      onOk() {
        //判断信息校验是否通过
        let validateResult = this.$refs.formValidate.validate();
        validateResult.then(function (result) {
          let index = this.data_index;
          if (result) {
            //指针大于等于信息数组的长度时，表示是新增
            if (index >= this.table_data.length) {
              this.table_data.push({
                name: this.formValidate.name,
                city: this.formValidate.city,
                desc: this.formValidate.desc
              })
            } else {
              //指针小于信息数组的长度时，表示是修改
              this.table_data[index].name = this.formValidate.name || this.table_data[index].name;
              this.table_data[index].city = this.formValidate.city || this.table_data[index].city;
              this.table_data[index].desc = this.formValidate.desc || this.table_data[index].desc;
            }

            //关闭对话框
            this.formModal = false
          }
        }.bind(this));
      }
    }
  }
</script>

<style scoped>

</style>
