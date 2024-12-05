import { useCookies } from '@vueuse/integrations/useCookies'

// 存储在 Cookie 中的标签页数据的 key
const TAB_LIST_KEY = 'tabList'
const cookie = useCookies()

// 获取 TabList
export function getTabList() {
    return cookie.get(TAB_LIST_KEY)
}

// 存储 TabList 到 Cookie 中
export function setTabList(tabList) {
    return cookie.set(TAB_LIST_KEY, tabList)
}
