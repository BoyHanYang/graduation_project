<template>
  <el-main>
    <!--搜索栏 -->
    <el-form v-if="store.getUserTyp != '1'" :model="listParm" :inline="true" size="default">
      <el-form-item>
        <el-input v-model="listParm.name" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="listParm.phone" placeholder="请输入电话"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input
          v-model="listParm.username"
          placeholder="请输入会员卡号"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button :icon="Search" @click="searchBtn">搜索</el-button>
        <el-button type="danger" plain :icon="Close" @click="resetBtn"
          >重置</el-button
        >
        <el-button
          v-permission="['sys:memberList:add']"
          :icon="Plus"
          type="primary"
          @click="addBtn"
          >新增</el-button
        >
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table :height="tableHeight" :data="tableList.list" border stripe>
      <el-table-column prop="username" label="会员卡号"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="cardType" label="会员类型"></el-table-column>
      <el-table-column prop="price" label="会员价格"></el-table-column>
      <el-table-column prop="money" label="充值金额"></el-table-column>
      <el-table-column
        prop="endTime"
        width="150"
        label="到期时间"
      ></el-table-column>
      <el-table-column prop="sex" label="性别">
        <template #default="scope">
          <el-tag v-if="scope.row.sex == '0'" type="success" size="default"
            >男</el-tag
          >
          <el-tag v-if="scope.row.sex == '1'" type="danger" size="default"
            >女</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="birthday" label="生日"></el-table-column>
      <el-table-column prop="joinTime" label="加入时间"></el-table-column>
      <el-table-column prop="height" label="身高"></el-table-column>
      <el-table-column prop="weight" label="体重"></el-table-column>
      <el-table-column prop="waist" label="腰围"></el-table-column>
      <el-table-column prop="status" label="状态">
        <template #default="scope">
          <el-tag v-if="scope.row.status == '1'" type="success" size="default"
            >启用</el-tag
          >
          <el-tag v-if="scope.row.status == '0'" type="danger" size="default"
            >停用</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column v-if="global.$checkPermission(['sys:memberList:setCard','sys:memberList:edit','sys:memberList:delete','sys:memberList:rechart'])" label="操作" width="200" align="center">
        <template #default="scope">
          <el-button
            v-if="global.$checkPermission(['sys:memberList:setCard'])"
            type="success"
            :icon="Edit"
            size="default"
            @click="joinBtn(scope.row)"
            >办卡</el-button
          >
          <el-button type="primary" size="default">
            <el-dropdown placement="bottom-start" @visible-change="vChange" style="color: #fff">
              <span class="el-dropdown-link">
                更多
                <el-icon class="el-icon--right">
                  <arrow-down />
                </el-icon>
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item
                    v-if="rechartFlg"
                    @click="rechargeBtn(scope.row)"
                    :icon="ChatLineSquare"
                    >充值</el-dropdown-item
                  >
                  <el-dropdown-item
                    v-if="editFlg"
                    :icon="Edit"
                    @click="editBtn(scope.row)"
                    >编辑</el-dropdown-item
                  >
                  <el-dropdown-item
                    v-if="deleteFlg"
                    :icon="Delete"
                    @click="deleteBtn(scope.row)"
                    >删除</el-dropdown-item
                  >
                  <el-dropdown-item
                    v-if="resetFlg"
                    :icon="Edit"
                    @click="resetPasBtn(scope.row)"
                    >重置密码</el-dropdown-item
                  >
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </el-button>
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

    <!-- 新增、编辑 -->
    <AddMember ref="addRef" @refresh="refresh"></AddMember>
    <!-- 办卡 -->
    <JoinApply ref="joinRef" @refresh="refresh"></JoinApply>
    <!-- 充值 -->
    <Recharge ref="rechargeRef" @refresh="refresh"></Recharge>
  </el-main>
</template>

<script setup lang="ts">
import AddMember from "./AddMember.vue";
import JoinApply from "./JoinApply.vue";
import Recharge from "./Recharge.vue";
import {
  Plus,
  Edit,
  Delete,
  Search,
  Close,
  ChatLineSquare,
} from "@element-plus/icons-vue";
import useTable from "@/composables/member/useTable";
import useMember from "@/composables/member/useMember";
import useJoin from "@/composables/member/useJoin";
import useRecharge from "@/composables/member/useRecharge";
import useInstance from "@/hooks/useInstance";
import { ref } from "vue";
const { global } = useInstance();
//表格相关
const {
  listParm,
  getList,
  resetBtn,
  searchBtn,
  tableList,
  sizeChange,
  currentChange,
  tableHeight,
  refresh,
  store
} = useTable();
//新增、编辑操作
const { addBtn, editBtn, deleteBtn, addRef,resetPasBtn } = useMember(getList);
//办卡
const { joinRef, joinBtn } = useJoin();
//充值
const { rechargeRef, rechargeBtn } = useRecharge();
//按钮的初始值
const rechartFlg = ref(false);
const editFlg = ref(false);
const deleteFlg = ref(false);
const resetFlg = ref(false);
const vChange = ()=>{
  rechartFlg.value = global.$checkPermission(['sys:memberList:rechart'])
  editFlg.value = global.$checkPermission(['sys:memberList:edit'])
  deleteFlg.value = global.$checkPermission(['sys:memberList:delete'])
  resetFlg.value = global.$checkPermission(['sys:member:resetPassword'])
}
</script>

<style scoped></style>
