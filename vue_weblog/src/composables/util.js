import {ElMessage, ElMessageBox} from "element-plus";
//手动引入组件的样式文件
import 'element-plus/es/components/message/style/css'

import nprogress from 'nprogress'
import 'element-plus/es/components/message-box/style/css'

export function showMessage(message = '提示内容',type='success',customClass =''){

    return ElMessage({
        type: type,
        message,
        customClass,
    })
}

//显示页面加载loading
export function showPageLoading(){
    nprogress.start();
}

//隐藏页面加载 loading
export function hidePageLoading(){
    nprogress.done();
}

// 弹出确认框
export function showModel(content = '提示内容', type = 'warning', title = '') {
    return ElMessageBox.confirm(
        content,
        title,
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type,
        }
    )
}
