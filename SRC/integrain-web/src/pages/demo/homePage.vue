<template>
  <div class="layout">
    <Layout>
      <IHeader></IHeader>
      <Layout>
        <div class="home_page">
          <Row>
            <div>
              <Carousel autoplay v-model="value2" loop>
                <CarouselItem>
                  <div class="demo-carousel" style="background: #160065;"></div>
                </CarouselItem>
                <CarouselItem>
                  <div class="demo-carousel" style="background: #650717;"></div>
                </CarouselItem>
                <CarouselItem>
                  <div class="demo-carousel" style="background: #386515;"></div>
                </CarouselItem>
                <CarouselItem>
                  <div class="demo-carousel" style="background: #650161;"></div>
                </CarouselItem>
              </Carousel>
            </div>
          </Row>
          <br>
          <Row :gutter="16">
            <Col span="12">
            <div>
              <Table :columns="columns1" :data="data1"></Table>
            </div>
            </Col>
            <Col span="12">
            <div>
              <Collapse v-model="value1">
                <Panel name="1">
                  史蒂夫·乔布斯
                  <p slot="content">史蒂夫·乔布斯（Steve Jobs），1955年2月24日生于美国加利福尼亚州旧金山，美国发明家、企业家、美国苹果公司联合创办人。</p>
                </Panel>
                <Panel name="2">
                  斯蒂夫·盖瑞·沃兹尼亚克
                  <p slot="content">斯蒂夫·盖瑞·沃兹尼亚克（Stephen Gary Wozniak），美国电脑工程师，曾与史蒂夫·乔布斯合伙创立苹果电脑（今之苹果公司）。斯蒂夫·盖瑞·沃兹尼亚克曾就读于美国科罗拉多大学，后转学入美国著名高等学府加州大学伯克利分校（UC Berkeley）并获得电机工程及计算机（EECS）本科学位（1987年）。</p>
                </Panel>
                <Panel name="3">
                  乔纳森·伊夫
                  <p slot="content">乔纳森·伊夫是一位工业设计师，现任Apple公司设计师兼资深副总裁，英国爵士。他曾参与设计了iPod，iMac，iPhone，iPad等众多苹果产品。除了乔布斯，他是对苹果那些著名的产品最有影响力的人。</p>
                </Panel>
              </Collapse>
            </div>
            </Col>
          </Row>
          <br>
          <Row :gutter="16">
            <Col span="8">
            <div style="height: 80px; background: #90fdf7;">
            </div>
            </Col>
            <Col span="8">
            <div style="height: 80px; background: #90fdf7;">
            </div>
            </Col>
            <Col span="8">
            <div style="height: 80px; background: #90fdf7;">
            </div>
            </Col>
          </Row>
          <br>
        </div>
      </Layout>
    </Layout>
  </div>
</template>

<script>
  import IHeader from "../../components/IHeader/index";

    export default {
      name: "home-page",
      data () {
        return {
          value1: '1',
          value2: 0,
          columns1: [
            {
              title: 'Name',
              key: 'name'
            },
            {
              title: 'Age',
              key: 'age'
            },
            {
              title: 'Address',
              key: 'address'
            }
          ],
          data1: [
            {
              name: 'John Brown',
              age: 18,
              address: 'New York No. 1 Lake Park',
              date: '2016-10-03'
            },
            {
              name: 'Jim Green',
              age: 24,
              address: 'London No. 1 Lake Park',
              date: '2016-10-01'
            },
            {
              name: 'Joe Black',
              age: 30,
              address: 'Sydney No. 1 Lake Park',
              date: '2016-10-02'
            },
            {
              name: 'Jon Snow',
              age: 26,
              address: 'Ottawa No. 2 Lake Park',
              date: '2016-10-04'
            }
          ]
        }
      },
      components: {
        IHeader
      },
      created() {
        this.$store.commit('SET_ACTIVEHEADMENU', '/');
      }
    }
</script>

<style scoped>
  .home_page{
    position: absolute;
    left: 0;
    top: 65px;
    right: 0;
    bottom: 0;
    padding: 20px 150px 20px 150px;
    overflow-y: scroll;
  }
  .demo-carousel{
    height: 150px;
  }
</style>
