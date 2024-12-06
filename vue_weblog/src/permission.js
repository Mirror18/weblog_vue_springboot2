import router from "@/router/index.js";
import {getToken} from "@/composables/cookie.js";
import {hidePageLoading, showMessage, showPageLoading} from "@/composables/util.js";

//全局路由前置守卫
//to:即将进入的目标
//from 当前导航正要离开的路由
//next 可额外添加的参数，手动控制跳转那个页面
router.beforeEach((to, from, next) => {
    //必须设置，且只被调用一次
    // next()

    console.log('==> 全局路由前置守卫');

    //展示页面加载loading
    showPageLoading()

    //若用户想要访问后台
    //未登录，则强制跳转登录页
    let token = getToken()
    if(!token && to.path.startsWith('/admin')) {
        showMessage('请先登录','warning')
        next({path:'/login'})
    }else if(token && to.path==='/login'){
        //若用户已经登陆，且重复访问登陆页面
        showMessage('请勿重复登陆','warning')
        //跳转到后台页面
        next({path:'/admin/index'})
    } else{
        next();
    }
})

//后置守卫，不会改变导航本身
router.afterEach((to, from, next) => {

    //动态设置页面 title
    let title = (to.meta.title ? to.meta.title : "") + '- weblog';
    document.title = title;

    //隐藏页面加载 loading
    hidePageLoading();
})