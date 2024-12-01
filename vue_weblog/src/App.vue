<!-- html 代码 -->
<template>
<!--  <h1>Hello, Vue 3 !</h1>-->
  <router-view></router-view>
</template>

<!--js 代码, setup 标识通常和组合式 API 搭配使用, 用于告诉 Vue 需要在编译时进行一些处理，让我们可以更简洁地使用组合式 API-->
<script setup>
</script>

<!--css 代码， scoped 表示节点内 css 样式只针对此组件有效，不影响其他组件-->
<style scoped>
#nprogress .bar{
  background: #409eff !important;
}
</style>
