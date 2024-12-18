import axios from "@/axios";

// 获取文章分页数据
export function getArticlePageList(data) {
    return axios.post("/admin/article/list", data)
}


// 删除文章
export function deleteArticle(id) {
    return axios.post("/admin/article/delete", {id})
}
