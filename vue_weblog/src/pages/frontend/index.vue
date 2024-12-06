<template>
  <!-- 导航栏组件 -->
  <nav class="bg-white border-gray-200 border-b dark:bg-gray-900">
    <!-- 最外层的容器，定义最大宽度、内容的对齐方式 -->
    <div class="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
      <!-- 左上角图标及博客名称，点击后返回首页 -->
      <a href="/" class="flex items-center">
        <img src="https://flowbite.com/docs/images/logo.svg" class="h-8 mr-3" alt="Flowbite Logo"/>
        <!-- 博客标题 -->
        <span class="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">mirror的博客</span>
      </a>

      <!-- 右侧区域，包含搜索按钮、登录按钮和折叠菜单按钮 -->
      <div class="flex items-center md:order-2">
        <!-- 搜索按钮，在小屏幕设备显示 -->
        <button type="button" data-collapse-toggle="navbar-search" aria-controls="navbar-search" aria-expanded="false"
                class="md:hidden text-gray-500 dark:text-gray-400 hover:bg-gray-100 dark:hover:bg-gray-700 focus:outline-none focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 rounded-lg text-sm p-2.5 mr-1">
          <!-- 搜索图标 -->
          <svg class="w-5 h-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                  d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
          </svg>
          <span class="sr-only">Search</span> <!-- 辅助文本，用于屏幕阅读器 -->
        </button>

        <!-- 搜索输入框，在中等及以上屏幕设备显示 -->
        <div class="relative hidden mr-2 md:block">
          <!-- 搜索图标，位于输入框内左侧 -->
          <div class="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
            <svg class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
              <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
            </svg>
          </div>
          <!-- 搜索输入框 -->
          <input type="text" id="search-navbar"
                 class="block w-full p-2 pl-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                 placeholder="请输入关键词...">
        </div>

        <!-- 登录按钮，点击跳转到登录页面 -->
        <div class="text-gray-900 ml-1 mr-1 hover:text-blue-700" @click="router.push('/login')">登录</div>

        <!-- 折叠菜单按钮，在小屏幕设备显示 -->
        <button data-collapse-toggle="navbar-search" type="button"
                class="inline-flex items-center p-2 w-10 h-10 justify-center text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
                aria-controls="navbar-search" aria-expanded="false">
          <span class="sr-only">Open main menu</span> <!-- 辅助文本，用于屏幕阅读器 -->
          <!-- 折叠菜单图标 -->
          <svg class="w-5 h-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 17 14">
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 1h15M1 7h15M1 13h15"/>
          </svg>
        </button>
      </div>

      <!-- 菜单项，PC端显示 -->
      <div class="items-center justify-between hidden  md:flex w-full md:w-auto md:order-1" id="navbar-search">
        <!-- 搜索框（移动设备） -->
        <div class="relative mt-3 md:hidden">
          <div class="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
            <svg class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
              <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
            </svg>
          </div>
          <input type="text" id="search-navbar"
                 class="block w-full p-2 pl-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                 placeholder="Search...">
        </div>

        <!-- 菜单项列表 -->
        <ul class="flex flex-col p-4 md:p-0 mt-4 font-medium border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 md:mt-0 md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
          <!-- 首页菜单项 -->
          <li>
            <a href="#" class="block py-2 pl-3 pr-4 text-white bg-blue-700 rounded md:bg-transparent md:text-blue-700 md:p-0 md:dark:text-blue-500" aria-current="page">首页</a>
          </li>
          <!-- 分类菜单项 -->
          <li>
            <a href="#" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">分类</a>
          </li>
          <!-- 标签菜单项 -->
          <li>
            <a href="#" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">标签</a>
          </li>
          <!-- 归档菜单项 -->
          <li>
            <a href="#" class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">归档</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { onMounted } from 'vue'
import { initCollapses } from 'flowbite'
import router from "@/router/index.js";


/* 使用 onMounted 钩子，当组件挂载时初始化 Flowbite 折叠组件 */
onMounted(() => {
  console.log('组件挂载，初始化 Flowbite');
  initCollapses();  // 初始化折叠菜单功能
})
</script>
