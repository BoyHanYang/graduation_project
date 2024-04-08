import http from "@/http";
import { CourseType ,CourseListParm, SelectCoure,MyCourseListParm} from "./CourseModel";
//图片上传
export const uploadImageApi = (parm:object)=>{
    return http.upload("/api/upload/uploadImage",parm)
}
//获取课程教练
export const getTeacherApi = ()=>{
    return http.get("/api/user/getTeacher")
}
//新增
export const addApi = (parm:CourseType)=>{
    return http.post("/api/course",parm)
}
//列表
export const listApi = (parm:CourseListParm)=>{
    return http.get("/api/course/list",parm)
}
//编辑
export const editApi = (parm:CourseType)=>{
    return http.put("/api/course",parm)
}
//删除
export const deleteApi = (courseId:string)=>{
    return http.delete(`/api/course/${courseId}`)
}
//选课
export const joinCourseApi = (parm:SelectCoure)=>{
    return http.post("/api/course/joinCourse",parm)
}
//我的课程
export const getMyCourseListApi = (parm:MyCourseListParm)=>{
    return http.get("/api/course/getMyCourseList",parm)
}