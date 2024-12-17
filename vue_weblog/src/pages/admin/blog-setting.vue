<script setup>
import { reactive, ref } from 'vue'
import { Check, Close } from '@element-plus/icons-vue'
// 表单对象
const form = reactive({
  name: '',
  author: '',
  logo: '',
  avatar: '',
  introduction: '',
  githubHomepage: '',
  giteeHomepage: '',
  zhihuHomepage: '',
  csdnHomepage: '',
})

// 规则校验
const rules = {
  name: [{ required: true, message: '请输入博客名称', trigger: 'blur' }],
  author: [{ required: true, message: '请输入作者名', trigger: 'blur' }],
  logo: [{ required: true, message: '请上传博客 LOGO', trigger: 'blur' }],
  avatar: [{ required: true, message: '请上传作者头像', trigger: 'blur' }],
  introduction: [{ required: true, message: '请输入介绍语', trigger: 'blur' }],
}

// 是否开启 GitHub
const isGithubChecked = ref(false)
// 是否开启 Gitee
const isGiteeChecked = ref(false)
// 是否开启知乎
const isZhihuChecked = ref(false)
// 是否开启 CSDN
const isCSDNChecked = ref(false)



// 监听 Github Switch 改变事件
const githubSwitchChange = (checked) => {
  if (checked == false) {
    form.githubHomepage = ''
  }
}

// 监听 Gitee Switch 改变事件
const giteeSwitchChange = (checked) => {
  if (checked == false) {
    form.giteeHomepage = ''
  }
}

// 监听知乎 Switch 改变事件
const zhihuSwitchChange = (checked) => {
  if (checked == false) {
    form.zhihuHomepage = ''
  }
}

// 监听 CSDN Switch 改变事件
const csdnSwitchChange = (checked) => {
  if (checked == false) {
    form.csdnHomepage = ''
  }
}


</script>
<template>
  <div>
    <!-- 卡片组件， shadow="never" 指定 card 卡片组件没有阴影 -->
    <el-card shadow="never">
      <el-form :model="form" label-width="160px" :rules="rules">
        <el-form-item label="博客名称" prop="name">
          <el-input v-model="form.name" clearable />
        </el-form-item>
        <el-form-item label="作者名" prop="author">
          <el-input v-model="form.author" clearable />
        </el-form-item>

        <el-form-item label="博客 LOGO" prop="logo">
          <el-upload class="avatar-uploader" action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
                     :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
            <img v-if="form.logo" :src="form.logo" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon">
              <Plus />
            </el-icon>
          </el-upload>
        </el-form-item>
        <el-form-item label="作者头像" prop="avatar">
          <el-upload class="avatar-uploader" action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
                     :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
            <img v-if="form.avatar" :src="form.avatar" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon">
              <Plus />
            </el-icon>
          </el-upload>
        </el-form-item>

        <el-form-item label="介绍语" prop="introduction">
          <el-input v-model="form.introduction" type="textarea" />
        </el-form-item>

        <!-- 开启 Github 访问 -->
        <el-form-item label="开启 GihHub 访问">
          <el-switch v-model="isGithubChecked" inline-prompt :active-icon="Check" :inactive-icon="Close"
                     @change="githubSwitchChange" />
        </el-form-item>
        <el-form-item label="GitHub 主页访问地址" v-if="isGithubChecked">
          <el-input v-model="form.githubHomepage" clearable placeholder="请输入 GitHub 主页访问的 URL" />
        </el-form-item>

        <!-- 开启 Gitee 访问 -->
        <el-form-item label="开启 Gitee 访问">
          <el-switch v-model="isGiteeChecked" inline-prompt :active-icon="Check" :inactive-icon="Close"
                     @change="giteeSwitchChange" />
        </el-form-item>
        <el-form-item label="Gitee 主页访问地址" v-if="isGiteeChecked">
          <el-input v-model="form.giteeHomepage" clearable placeholder="请输入 Gitee 主页访问的 URL" />
        </el-form-item>

        <!-- 开启知乎访问 -->
        <el-form-item label="开启知乎访问">
          <el-switch v-model="isZhihuChecked" inline-prompt :active-icon="Check" :inactive-icon="Close"
                     @change="zhihuSwitchChange" />
        </el-form-item>
        <el-form-item label="知乎主页访问地址" v-if="isZhihuChecked">
          <el-input v-model="form.zhihuHomepage" clearable placeholder="请输入知乎主页访问的 URL" />
        </el-form-item>

        <!-- 开启 CSDN 访问 -->
        <el-form-item label="开启 CSDN 访问">
          <el-switch v-model="isCSDNChecked" inline-prompt :active-icon="Check" :inactive-icon="Close"
                     @change="csdnSwitchChange" />
        </el-form-item>
        <el-form-item label="CSDN 主页访问地址" v-if="isCSDNChecked">
          <el-input v-model="form.csdnHomepage" clearable placeholder="请输入 CSDN 主页访问的 URL" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">保存</el-button>
        </el-form-item>

      </el-form>
    </el-card>
  </div>
</template>





<style scoped>
.avatar-uploader .avatar {
  width: 100px;
  height: 100px;
  display: block;
}
</style>

<style>
/* 解决 textarea :focus 状态下，边框消失的问题 */
.el-textarea__inner:focus {
  outline: 0 !important;
  box-shadow: 0 0 0 1px var(--el-input-focus-border-color) inset !important;
}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  text-align: center;
}
</style>
