//列表查询的参数类型
export type CourseListParm = {
    teacherName:string,
    courseName:string,
    currentPage:number,
    pageSize:number,
    total:number
}
//课程数据类型
export type CourseType = {
    type:string,  //区分 新增 编辑
    courseId:string,
    courseName:string,
    image:string,
    teacherId:string,
    teacherName:string,
    courseHour:number,
    courseDetails:string,
    coursePrice:number
}
//选课参数类型
export type SelectCoure = {
    courseId:string,
    memberId:string
}

export type MyCourseListParm = {
    userType:string,
    userId:string,
    currentPage:number,
    pageSize:number,
    total:number
}