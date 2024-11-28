
//存储在cookie 中的 token 的key
import {useCookies} from "@vueuse/integrations";

const TOKEN_KEY = 'Authorization'
const cookie = useCookies();

//获取 token 值
export function getToken(){
    return cookie.get(TOKEN_KEY);
}

//设置 token 到 cookie 中
export function setToken(token){
    return cookie.set(TOKEN_KEY, token);
}

//删除 token
export function removeToken(){
    return cookie.remove(TOKEN_KEY);
}
