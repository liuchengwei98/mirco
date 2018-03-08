<template>
  <div>
    <Row>
      <Col span="8" >
        <Input v-model="value1" placeholder="Enter something..." style="width: 300px"></Input>
      </Col>
      <Col span="4" >
        <Button type="primary" shape="circle" icon="ios-search" @click="query()"></Button>
      </Col>
    </Row>
    <Row>
      <Col span="8">
        </br>
        <Input v-model="value2" placeholder="Enter something..." style="width: 300px"></Input>
      </Col>
    </Row>
  </div>
</template>

<script>
  import "lodash"
  import {getServiceApi, getUserInfo} from '../../api/demo/testApi/index'

  export default {
    data () {
      return {
        value1: '',
        value2: ''
      }
    },
    methods: {
      query() {
        // getServiceApi({}).then((response) => {
        //   console.log('response', response);
        //   this.value1 = response.data.message;
        //   this.value2 = '123';
        // })

        getUserInfo({}).then((response) => {
          console.log('response', response);
          this.value1 = response.data.name;
          this.value2 = '123';
        })
      }
    },
    components: {

    },
    created () {   /* 这个是vue的钩子函数，当new Vue()实例创建完毕后执行的函数 */
      // getZiHuNews({}).then((response) => {
      //   console.log(response.data.stories);//,,
      //   this.table_data = _.drop(response.data.stories, response.data.stories.length - 5);
      // });
    }
  }

</script>
<style scoped>
</style>
