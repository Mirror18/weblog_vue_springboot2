<template>
  <div>
    <!-- 表头分页查询条件， shadow="never" 指定 card 卡片组件没有阴影 -->
    <el-card shadow="never" class="mb-5">
      <!-- flex 布局，内容垂直居中 -->
      <div class="flex items-center">
        <el-text>文章标题</el-text>
        <div class="ml-3 w-52 mr-5"><el-input v-model="searchArticleTitle" placeholder="请输入（模糊查询）" /></div>

        <el-text>创建日期</el-text>
        <div class="ml-3 w-30 mr-5">
          <!-- 日期选择组件（区间选择） -->
          <el-date-picker v-model="pickDate" type="daterange" range-separator="至" start-placeholder="开始时间"
                          end-placeholder="结束时间" size="default" :shortcuts="shortcuts" @change="datepickerChange"/>
        </div>

        <el-button type="primary" class="ml-3" :icon="Search" @click="getTableData">查询</el-button>
        <el-button class="ml-3" :icon="RefreshRight" @click="reset">重置</el-button>
      </div>
    </el-card>

    <el-card shadow="never">
      <!-- 写文章按钮 -->
      <div class="mb-5">
        <el-button type="primary" @click="isArticlePublishEditorShow = true">
          <el-icon class="mr-1">
            <EditPen />
          </el-icon>
          写文章</el-button>
      </div>


      <!-- 写博客 -->
      <el-dialog v-model="isArticlePublishEditorShow" :fullscreen="true" :show-close="false">
        <template #header="{ close, titleId, titleClass }">
          <!-- 固钉组件，固钉到顶部 -->
          <el-affix :offset="20" style="width: 100%;">
            <!-- 指定 flex 布局， 高度为 10， 背景色为白色 -->
            <div class="flex h-10 bg-white">
              <!-- 字体加粗 -->
              <h4 class="font-bold">写文章</h4>
              <!-- 靠右对齐 -->
              <div class="ml-auto flex">
                <el-button @click="isArticlePublishEditorShow = false">取消</el-button>
                <el-button type="primary">
                  <el-icon class="mr-1">
                    <Promotion />
                  </el-icon>
                  发布
                </el-button>
              </div>
            </div>
          </el-affix>
        </template>
        <!-- label-position="top" 用于指定 label 元素在上面 -->
        <el-form :model="form" ref="publishArticleFormRef" label-position="top" size="large" :rules="rules">
          <el-form-item label="标题" prop="title">
            <el-input v-model="form.title" autocomplete="off" size="large" maxlength="40" show-word-limit
                      clearable />
          </el-form-item>
          <el-form-item label="内容" prop="content">
            <!-- Markdown 编辑器 -->
            <MdEditor v-model="form.content" editorId="publishArticleEditor" />
          </el-form-item>
          <el-form-item label="封面" prop="cover">
            <el-upload class="avatar-uploader" action="#" :auto-upload="false" :show-file-list="false">
              <img v-if="form.cover" :src="form.cover" class="avatar" />
              <el-icon v-else class="avatar-uploader-icon">
                <Plus />
              </el-icon>
            </el-upload>
          </el-form-item>
          <el-form-item label="摘要" prop="summary">
            <!-- :rows="3" 指定 textarea 默认显示 3 行 -->
            <el-input v-model="form.summary" :rows="3" type="textarea" placeholder="请输入文章摘要" />
          </el-form-item>
          <el-form-item label="分类" prop="categoryId">
            <el-select v-model="form.categoryId" clearable placeholder="---请选择---" size="large">

            </el-select>
          </el-form-item>
          <el-form-item label="标签" prop="tags">
            <!-- 标签选择 -->
            <el-select v-model="form.tags" multiple filterable remote reserve-keyword placeholder="---请输入---"
                       remote-show-suffix :remote-method="remoteMethod" allow-create default-first-option
                       :loading="tagSelectLoading" size="large">

            </el-select>
          </el-form-item>
        </el-form>
      </el-dialog>

      <!-- 分页列表 -->
      <el-table :data="tableData" border stripe style="width: 100%" v-loading="tableLoading">
        <el-table-column prop="title" label="标题" width="180" />
        <el-table-column prop="cover" label="封面" width="180">
          <template #default="scope">
            <el-image style="width: 50px;" :src="scope.row.cover" />
          </template>
        </el-table-column>

        <el-table-column prop="createTime" label="发布时间" width="180" />
        <el-table-column label="操作" >
          <template #default="scope">
            <el-button size="small">
              <el-icon class="mr-1">
                <Edit />
              </el-icon>
              编辑</el-button>
            <el-button type="danger" size="small" @click="deleteArticleSubmit(scope.row)">
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

<script setup>
import { ref, reactive } from 'vue'
import { Search, RefreshRight } from '@element-plus/icons-vue'
import moment from 'moment'

// 模糊搜索的文章标题
const searchArticleTitle = ref('')
// 日期
const pickDate = ref('')

// 查询条件：开始结束时间
const startDate = reactive({})
const endDate = reactive({})

// 监听日期组件改变事件，并将开始结束时间设置到变量中
const datepickerChange = (e) => {
  startDate.value = moment(e[0]).format('YYYY-MM-DD')
  endDate.value = moment(e[1]).format('YYYY-MM-DD')

  console.log('开始时间：' + startDate.value + ', 结束时间：' + endDate.value)
}

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

// 重置
const reset = () => {
  pickDate.value = ''
  startDate.value = null
  endDate.value = null
  searchArticleTitle.value = ''
}

// 表格加载 Loading
const tableLoading = ref(false)
// 表格数据
const tableData = ref([])
// 当前页码，给了一个默认值 1
const current = ref(1)
// 总数据量，给了个默认值 0
const total = ref(0)
// 每页显示的数据量，给了个默认值 10
const size = ref(10)


import { getArticlePageList } from '@/api/admin/article'
// 获取分页数据
function getTableData() {
  // 显示表格 loading
  tableLoading.value = true
  // 调用后台分页接口，并传入所需参数
  getArticlePageList({current: current.value, size: size.value, startDate: startDate.value, endDate: endDate.value, title: searchArticleTitle.value})
      .then((res) => {
        if (res.success == true) {
          tableData.value = res.data
          current.value = res.current
          size.value = res.size
          total.value = res.total
        }
      })
      .finally(() => tableLoading.value = false) // 隐藏表格 loading
}
getTableData()


// 每页展示数量变更事件
const handleSizeChange = (chooseSize) => {
  console.log('选择的页码' + chooseSize)
  size.value = chooseSize
  getTableData()
}

import {  deleteArticle } from '@/api/admin/article'
import { showMessage, showModel } from '@/composables/util'

// 删除文章
const deleteArticleSubmit = (row) => {
  console.log(row)
  showModel('是否确定要删除该文章？').then(() => {
    deleteArticle(row.id).then((res) => {
      if (res.success == false) {
        // 获取服务端返回的错误消息
        let message = res.message
        // 提示错误消息
        showMessage(message, 'error')
        return
      }

      showMessage('删除成功')
      // 重新请求分页接口，渲染数据
      getTableData()
    })
  }).catch(() => {
    console.log('取消了')
  })
}
// 是否显示文章发布对话框
const isArticlePublishEditorShow = ref(false)

// 发布文章表单引用
const publishArticleFormRef = ref(null)

// 表单对象
const form = reactive({
  id: null,
  title: '',
  content: '请输入内容',
  cover: '',
  categoryId: null,
  tags: [],
  summary: ""
})

// 表单校验规则
const rules = {
  title: [
    { required: true, message: '请输入文章标题', trigger: 'blur' },
    { min: 1, max: 40, message: '文章标题要求大于1个字符，小于40个字符', trigger: 'blur' },
  ],
  content: [{ required: true }],
  cover: [{ required: true }],
  categoryId: [{ required: true, message: '请选择文章分类', trigger: 'blur' }],
  tags: [{ required: true, message: '请选择文章标签', trigger: 'blur' }],
}

import { MdEditor } from 'md-editor-v3'
import 'md-editor-v3/lib/style.css'
</script>


<style scoped>
/* 封面图片样式 */
.avatar-uploader .avatar {
  width: 200px;
  height: 100px;
  display: block;
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 200px;
  height: 100px;
  text-align: center;
}
</style>