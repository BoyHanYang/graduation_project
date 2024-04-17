<template>
  <SysDialog
      :title="dialog.title"
      :width="dialog.width"
      :height="dialog.height"
      :visible="dialog.visible"
      @onClose="onClose"
      @onConfirm="onConfirm"
  >
    <template v-slot:content>
      <el-tree
          ref="assignTree"
          :data="assignTreeData.list"
          node-key="menuId"
          :props="defaultProps"
          empty-text="暂无数据"
          :show-checkbox="true"
          default-expand-all
          :highlight-current="true"
          :default-checked-keys="assignTreeData.assignTreeChecked"
      ></el-tree>
    </template>
  </SysDialog>
</template>

<script setup lang="ts">
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import useAssign from "@/composables/role/useAssign";
import { userStore } from "@/store/user";
const store = userStore();
//获取权限树
const { defaultProps, assignTreeData, getMenuTree } = useAssign();
//弹框属性
const { dialog, onClose, onConfirm, onShow } = useDialog();
//显示弹框
const show = (roleId: string, name: string) => {
  let parm = {
    roleId: roleId,
    userId: store.getUserId,
  };
  getMenuTree(parm);
  //设置弹框属性
  dialog.width = 300;
  dialog.height = 450;
  dialog.title = "为【" + name + "】分配权限";
  onShow();
};
defineExpose({
  show,
});
</script>

<style scoped></style>