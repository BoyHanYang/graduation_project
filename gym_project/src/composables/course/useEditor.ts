import { onBeforeUnmount, ref, shallowRef, onMounted } from 'vue'
import { IEditorConfig } from '@wangeditor/editor'
import { userStore } from '@/store/user'
export default function useEditor() {
  const ustore = userStore()
  type InsertFnType = (url: string, alt?: string, href?: string) => void
  // 编辑器实例，必须用 shallowRef
  const editorRef = shallowRef()

  // 内容 HTML
  const valueHtml = ref('')

  const toolbarConfig = {}
  // const editorConfig = { placeholder: '请输入内容...' }
  const editorConfig: Partial<IEditorConfig> = { MENU_CONF: {}, placeholder: '请输入内容...' }
  //图片上传配置
  editorConfig.MENU_CONF!['uploadImage'] = {
    // form-data fieldName ，默认值 'wangeditor-uploaded-image'
    fieldName: 'file',
    server: process.env.BASE_API + '/api/upload/uploadImage?token='+ustore.getToken,
    // 自定义插入图片
    customInsert(res: any, insertFn: InsertFnType) {
      // res 即服务端的返回结果
      console.log(res)
      // 从 res 中找到 url alt href ，然后插图图片
      insertFn(process.env.BASE_API + res.data)
    },
  }
  // 组件销毁时，也及时销毁编辑器
  onBeforeUnmount(() => {
    const editor = editorRef.value
    if (editor == null) return
    editor.destroy()
  })
  const handleCreated = (editor: any) => {
    editorRef.value = editor // 记录 editor 实例，重要！
  }

  return {
    editorRef,
    valueHtml,
    mode: 'default', // 或 'simple'
    toolbarConfig,
    editorConfig,
    handleCreated
  };
}