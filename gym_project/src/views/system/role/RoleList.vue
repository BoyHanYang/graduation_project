<template>
  <el-main>
<!--    搜索栏-->
    <el-form model="listParm" :inline="true" size="default">
      <el-form-item>
        <el-input v-model="listParm.roleName" placeholder="请输入角色名称">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="Search" @click="searchBtn">查询</el-button>
        <el-button type="danger" icon="RefreshLeft"  @click="resetBtn" plain>重置</el-button>
        <el-button type="primary" icon="Plus" @click="addBtn">新增</el-button>
      </el-form-item>
    </el-form>
<!--    表格-->
    <el-table :height="tableHeight" :data="tableList.list" border stripe>
      <el-table-column prop="roleName" label="角色名称"></el-table-column>
      <el-table-column prop="remark" label="角色描述"></el-table-column>
      <el-table-column label="操作" width="220px" align="center">
        <template #default="scope">
          <el-button type="primary" icon="Edit" @click="editBtn(scope.row)">编辑</el-button>
          <el-button type="danger" icon="Delete" @click="deleteBtn(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
<!--    分页-->
    <el-pagination
    @size-change="sizeChange"
    @current-change="currentChange"
    :current-page.sync="listParm.currentPage"
    :page-sizes="[10,20,30,40]"
    :page-size="listParm.pageSize"
    layout="total, sizes, prev, pager, next, jumper"
    :total="listParm.total" background>
    </el-pagination>
<!--    新增编辑弹框-->
    <role-add ref="addRef" @refresh="refresh"></role-add>
  </el-main>
</template>

<script setup lang="ts">
import RoleAdd from "@/views/system/role/RoleAdd.vue";
import {Plus,Edit,Delete,Search,Close,RefreshLeft} from "@element-plus/icons-vue";
import useTable from "@/composables/role/useTable.ts";
import useRole from "@/composables/role/useRole.ts";
const {listParm,getList,searchBtn,resetBtn,tableList,sizeChange,currentChange,tableHeight,refresh} =useTable();
const {addBtn,editBtn,deleteBtn,addRef} =useRole(getList);
</script>

<style lang="scss" scoped>

</style>