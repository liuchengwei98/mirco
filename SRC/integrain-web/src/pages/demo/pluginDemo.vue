<template>
  <div>
    <div style="margin-top:20px;">
      <masked-input v-model="date" mask="11 / 11 / 1111" placeholder="Date" />
    </div>
    <div style="margin-top:20px;">
      <masked-input v-model="phone" mask="\+\1 (111) 1111-11" placeholder="Phone" />
    </div>
    <div style="margin-top:20px;">
      <v-tree ref='tree' :data='treeData' :multiple='true' :tpl='tpl' :halfcheck='true'/>
      <input type="text" v-model="searchword" />
      <button type="button" @click="search">GO</button>
    </div>
  </div>
</template>

<script>
  import MaskedInput from 'vue-masked-input'

  export default {
      name: "plugin-demo",
      data () {
        return {
          searchword: '',
          treeData: [{
            title: 'node1',
            expanded: true,
            children: [{
              title: 'node 1-1',
              expanded: true,
              children: [{
                title: 'node 1-1-1'
              }, {
                title: 'node 1-1-2'
              }, {
                title: 'node 1-1-3'
              }]
            }, {
              title: 'node 1-2',
              children: [{
                title: "<span style='color: red'>node 1-2-1</span>"
              }, {
                title: "<span style='color: red'>node 1-2-2</span>"
              }]
            }]
          }]
        }
      },
      methods: {
        tpl (node, ctx) {
          let titleClass = node.selected ? 'node-title node-selected' : 'node-title'
          if (node.searched) titleClass += ' node-searched'
          return <span>
          <button style='color:blue; background-color:pink' onClick={() => this.$refs.tree.addNode(node, {title: 'sync loading'})}>+</button>
          <span class={titleClass} domPropsInnerHTML={node.title} onClick={() => {
            ctx.parent.nodeSelected(ctx.props.node)
            console.log(ctx.parent.getSelectedNodes())
          }}></span>
          <button style='color:red; background-color:pink' onClick={() => this.$refs.tree.delNode(node.parent, node)}>delete</button>
          </span>
        },
        search () {
          this.$refs.tree.searchNodes(this.searchword)
        }
      },
      components: {
        MaskedInput
      }
    }
</script>

<style scoped>

</style>
