<template>
  <el-dialog
    :model-value="visible"
    :title="title"
    :width="width + 'px'"
    :before-close="onClose"
    append-to-body
    :close-on-click-modal="false"
  >
    <div class="container" :style="{ height: height + 'px' }">
      <!-- 内容展示：插槽方式显示 -->
      <slot name="content"></slot>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="danger" @click="onClose">取消</el-button>
        <el-button type="primary" @click="onConfirm">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
//定义弹框参数类型
interface DialogProps {
  title: string;
  visible: boolean;
  width: number;
  height: number;
}
//接收父组件传递数据
//withDefaults:设置参数默认值
const props = withDefaults(defineProps<DialogProps>(), {
  title: "标题",
  visible: false,
  height: 250,
  width: 630,
});
//注册事件
const emit = defineEmits(['onClose','onConfirm'])
//弹框关闭
const onClose = () => {
    emit('onClose')
};
//弹框确定
const onConfirm = () => {
    emit('onConfirm')
};
</script>
<style lang="scss" scope>
.container {
  overflow-x: initial;
  overflow-y: auto;
}
.el-dialog {
  border-top-left-radius: 7px !important;
  border-top-right-radius: 7px !important;
  .el-dialog__header {
    margin-right: 0px;
    border-top-left-radius: 7px !important;
    border-top-right-radius: 7px !important;
    background-color: #009688 !important;
    .el-dialog__title {
      color: #fff;
      font-size: 16px;
      font-weight: 600;
    }
  }
  .el-dialog__headerbtn {
    .el-dialog__close {
      color: #fff;
    }
  }
  .el-dialog__body {
    padding: 10px;
  }
  .el-dialog__footer {
    border-top: 1px solid #e8eaec !important;
    padding: 10px;
  }
}
</style>
