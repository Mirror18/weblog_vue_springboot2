import axios from "@/axios";

// 获取分类分页数据
export function getTagPageList(data) {
    return axios.post("/admin/tag/list", data)
}

// 添加分类
export function addTag(data) {
    return axios.post("/admin/tag/add", data)
}

// 删除分类
export function deleteTag(id) {
    return axios.post("/admin/tag/delete", {id})
}

// 根据标签名模糊查询
export function searchTags(key) {
    return axios.post("/admin/tag/search", {key})
}
