<template>
  <el-main>
    <!-- 表格 -->
    <el-table :height="tableHeight" :data="tableDate.list" border stripe>
      <el-table-column prop="image" width="90" label="课程封面" align="center">
        <template #default="scope">
          <el-image
            style="width: 60px; height: 60px; border-radius: 50%"
            :src="scope.row.image"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column prop="courseName" label="课程名称"></el-table-column>
      <el-table-column prop="courseHour" label="课时"></el-table-column>
      <el-table-column prop="coursePrice" label="价格"></el-table-column>
      <el-table-column prop="teacherName" label="授课教师"></el-table-column>
      <el-table-column
        v-if="global.$checkPermission(['sys:mycourse:export'])"
        label="操作"
        align="center"
        width="290"
      >
        <template #default="scope">
          <el-button
            v-permission="['sys:mycourse:export']"
            type="primary"
            :icon="Edit"
            size="default"
            @click="exportBtn(scope.row)"
            >导出学生</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      @size-change="sizeChange"
      @current-change="currentChange"
      :current-page.sync="listParm.currentPage"
      :page-sizes="[10, 20, 40, 80, 100]"
      :page-size="listParm.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="listParm.total"
      background
    >
    </el-pagination>
  </el-main>
</template>

<script setup lang="ts">
import { userStore } from "@/store/user";
import { Edit } from "@element-plus/icons-vue";
import useMyCourseTable from "../../composables/mycourse/useMyCourseTable";
import {  CourseType } from "@/api/course/CourseModel";
import useInstance from "@/hooks/useInstance";
const store  = userStore()
const { global } = useInstance();
//列表
const { listParm, tableDate, sizeChange, currentChange, tableHeight } =
  useMyCourseTable();
//导出
const exportBtn = (row: CourseType) => {
  const abtn = document.createElement("a");
  abtn.href =
    process.env.BASE_API +
    "/api/course/exportStuInfo?teacherId=" +
    store.getUserId+"&courseId="+row.courseId+"&token="+store.getToken;
  abtn.click();
};
</script>

<style scoped></style>
