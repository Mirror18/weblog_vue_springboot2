import axios from "@/axios";

// 获取分类分页数据
export function getCategoryPageList(data) {
    return axios.post("/admin/category/list", data)
}
