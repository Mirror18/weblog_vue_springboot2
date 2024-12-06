import axios from "axios";
import {getToken} from "@/composables/cookie.js";

//创建 Axios 实例
const instance = axios.create({
    baseURL: '/api', // API基础URL
    timeout: 7000, //请求超时时间
})
//添加请求拦截器
instance.interceptors.request.use(function(config) {
    //在发送请求之前做些什么
    // return config;
    const  token = getToken();
    console.log('统一添加请求头中的token: '+token);

    //当 token 不为空时
    if(token){
        //添加请求头，key  为 Authorization, value 值 的前缀为 Bearer
        config.headers['Authorization'] = 'Bearer ' + token;
    }
    return  config;
},function(error){
    //对请求错误做些什么
    return Promise.reject(error);
    }
);

// 添加响应拦截器
instance.interceptors.response.use(function (response) {
        // 2xx 范围内的状态码都会触发该函数。
        // 对响应数据做点什么
        return response.data
}, function(error){
    //超出 2xx 范围的状态码都会出发该函数
    //对响应 错误做些什么
    return Promise.reject(error);
    }

)
//暴露出去
export default instance;