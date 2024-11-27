import {ElMessage} from "element-plus";
//手动引入组件的样式文件
import 'element-plus/es/components/message/style/css'

export function showMessage(message = '提示内容',type='success',customClass =''){

    return ElMessage({
        type: type,
        message,
        customClass,
    })
}