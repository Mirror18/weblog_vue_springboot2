import {ElMessage} from "element-plus";
//手动引入组件的样式文件
import 'element-plus/es/components/message/style/css'

import nprogress from 'nprogress'

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