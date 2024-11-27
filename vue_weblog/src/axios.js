import axios from "axios";

//创建 Axios 实例
const instance = axios.create({
    baseURL: '/api', // API基础URL
    timeout: 7000, //请求超时时间
})

//暴露出去
export default instance;