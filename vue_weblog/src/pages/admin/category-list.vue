<script setup>
// 引入所需图标
import {Search, RefreshRight, Delete, Plus} from '@element-plus/icons-vue'
import { ref ,reactive} from 'vue';
import moment from "moment";

// 分页查询的分类名称
const searchCategoryName = ref('')
// 日期
const pickDate = ref('')

import { getCategoryPageList } from '@/api/admin/category'

// 表格数据
const tableData = ref([])
// 当前页码，给了一个默认值 1
const current = ref(1)
// 总数据量，给了个默认值 0
const total = ref(0)
// 每页显示的数据量，给了个默认值 10
const size = ref(10)
// 查询条件：开始结束时间
const startDate = reactive({})
const endDate = reactive({})

// 获取分页数据
function getTableData() {
  // 调用后台分页接口，并传入所需参数
  getCategoryPageList({current: current.value, size: size.value, startDate: startDate.value, endDate: endDate.value, name: searchCategoryName.value})
      .then((res) => {
        if (res.success === true) {
          tableData.value = res.data
          current.value = res.current
          size.value = res.size
          total.value = res.total
        }
      })
}
getTableData()





// 监听日期组件改变事件，并将开始结束时间设置到变量中
const datepickerChange = (e) => {
  startDate.value = moment(e[0]).format('YYYY-MM-DD')
  endDate.value = moment(e[1]).format('YYYY-MM-DD')

  console.log('开始时间：' + startDate.value + ', 结束时间：' + endDate.value)
}
//设置日期快捷选项
const shortcuts = [
  {
    text: '最近一周',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
      return [start, end]
    },
  },
  {
    text: '最近一个月',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
      return [start, end]
    },
  },
  {
    text: '最近三个月',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
      return [start, end]
    },
  },
]

// 每页展示数量变更事件
const handleSizeChange = (chooseSize) => {
  size.value = chooseSize
  getTableData()
}

// 重置查询条件
const reset = () => {
  searchCategoryName.value = ''
  pickDate.value = ''
  startDate.value = null
  endDate.value = null
}
</script>


<template>
  <div>
    <!-- 表头分页查询条件， shadow="never" 指定 card 卡片组件没有阴影 -->
    <el-card shadow="never" class="mb-5">
      <!-- flex 布局，内容垂直居中 -->
      <div class="flex items-center">
        <el-text>分类名称</el-text>
        <div class="ml-3 w-52 mr-5"><el-input v-model="searchCategoryName" placeholder="请输入（模糊查询）" /></div>

        <el-text>创建日期</el-text>
        <div class="ml-3 w-30 mr-5">
          <!-- 日期选择组件（区间选择） -->
          <el-date-picker style="top: 3px" v-model="pickDate" type="daterange" range-separator="至" start-placeholder="开始时间"
                          end-placeholder="结束时间" :shortcuts="shortcuts" size="default" @change="datepickerChange" />
        </div>

        <el-button type="primary" class="ml-3" :icon="Search" @click="getTableData">查询</el-button>
        <el-button class="ml-3" :icon="RefreshRight" @click="reset">重置</el-button>
      </div>


    </el-card>

    <el-card shadow="never">
      <!-- 新增按钮 -->
      <div class="mb-5">
        <el-button type="primary">
          <el-icon class="mr-1">
            <Plus />
          </el-icon>
          新增</el-button>
      </div>

      <!-- 分页列表 -->
      <el-table :data="tableData" border stripe style="width: 100%">
        <el-table-column prop="name" label="分类名称" width="180" />
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" >
          <template #default="scope">
            <el-button type="danger" size="small">
              <el-icon class="mr-1">
                <Delete />
              </el-icon>
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <div class="mt-10 flex justify-center">
        <el-pagination v-model:current-page="current" v-model:page-size="size" :page-sizes="[10, 20, 50]"
                       :small="false" :background="true" layout="total, sizes, prev, pager, next, jumper"
                       :total="total" @size-change="handleSizeChange" @current-change="getTableData" />
      </div>

    </el-card>


  </div>
</template>



<style scoped>

</style>