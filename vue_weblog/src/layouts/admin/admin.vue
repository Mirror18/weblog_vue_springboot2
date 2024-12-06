<script setup>

import AdminMenu from "@/layouts/admin/components/AdminMenu.vue";
import AdminHeader from "@/layouts/admin/components/AdminHeader.vue";
import AdminTagList from "@/layouts/admin/components/AdminTagList.vue";
import AdminFooter from "@/layouts/admin/components/AdminFooter.vue";
import { useMenuStore } from '@/stores/menu'

const menuStore = useMenuStore()
</script>

<template>
<!--  外部容器-->
  <el-container>
    <!-- 左边侧边栏 -->
    <el-aside :width='menuStore.menuWidth' class="transition-all">
      <AdminMenu></AdminMenu>
    </el-aside>
<!--    右边主内容区域-->
    <el-container>
<!--      顶部容器-->
      <el-header>
        <AdminHeader></AdminHeader>
      </el-header>

      <el-main>
<!--        标签导航栏-->
        <AdminTagList></AdminTagList>
        <!-- 主内容（根据路由动态展示不同页面） -->
        <router-view v-slot="{ Component }">
          <!-- max 指定最多缓存 10 个组件 -->
          <KeepAlive :max="10">
            <component :is="Component"></component>
          </KeepAlive>
        </router-view>

      </el-main>
<!--      底栏容器-->
      <el-footer>
        <AdminFooter></AdminFooter>
      </el-footer>
    </el-container>
  </el-container>
</template>

<style scoped>

.el-header{
  padding: 0 !important;
}

.el-footer {
  padding: 0 !important;
}
</style>