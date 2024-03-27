<template>
  <div>
    <el-button type="primary" @click="addBtn" size="default">新增</el-button>
    <SysDialog :visible="dialog.visible"
               @onClose="onClose"
               @onConfirm="onConfirm"
    >
      <template v-slot:content>
        弹框内容
      </template>
    </SysDialog>
  </div>
</template>

<script setup lang="ts">
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog.ts"

const {dialog,onClose,onConfirm} = useDialog()

const addBtn = () => {
  dialog.visible = true;
};
</script>

<style scoped></style>