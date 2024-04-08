import axios, {AxiosInstance, AxiosRequestConfig, AxiosResponse} from "axios";
import {ElMessage} from 'element-plus';

// axios配置
const config = {
    baseURL: process.env.BASE_API,
    timeout: 10000
}

// 定义返回值类型
export interface Result<T = any> {
    code: number,
    msg: string,
    data: T
}

// axios封装
class Http {
    // axios实例
    private instance: AxiosInstance;

    // 构造函数:初始化
    constructor(config: AxiosRequestConfig) {
        // 实例化axios
        this.instance = axios.create(config)
        // 配置请求拦截器
        this.interceptors()
    }

    // 拦截器
    private interceptors() {
        // 请求发送的处理
        this.instance.interceptors.request.use((config: AxiosRequestConfig) => {
            let token = '';
            if (token) {
                config.headers!['token'] = token
            }
            return config;
        },(error:any)=>{
            error.data={}
            error.data.msg='服务器异常，请联系管理员！'
            return error;
        }),
            this.instance.interceptors.response.use((res:AxiosResponse)=>{
                if (res.data.code !== 200){
                    ElMessage.error(res.data.msg || '服务器出错!')
                    return Promise.reject(res.data.msg || '服务器出错')
                } else {
                    return res.data
                }
            },(error :any)=>{
                error.data = {};
                if (error && error.response) {
                    switch (error.response.status) {
                        case 400:
                            error.data.msg = '错误请求';
                            ElMessage.error(error.data.msg)
                            break
                        case 401:
                            error.data.msg = '未授权，请重新登录';
                            ElMessage.error(error.data.msg)
                            break
                        case 403:
                            error.data.msg = '拒绝访问';
                            ElMessage.error(error.data.msg)
                            break
                        case 404:
                            error.data.msg = '请求错误,未找到该资源';
                            ElMessage.error(error.data.msg)
                            break
                        case 405:
                            error.data.msg = '请求方法未允许';
                            ElMessage.error(error.data.msg)
                            break
                        case 408:
                            error.data.msg = '请求超时';
                            ElMessage.error(error.data.msg)
                            break
                        case 500:
                            error.data.msg = '服务器端出错';
                            ElMessage.error(error.data.msg)
                            break
                        case 501:
                            error.data.msg = '网络未实现';
                            ElMessage.error(error.data.msg)
                            break
                        case 502:
                            error.data.msg = '网络错误';
                            ElMessage.error(error.data.msg)
                            break
                        case 503:
                            error.data.msg = '服务不可用';
                            ElMessage.error(error.data.msg)
                            break
                        case 504:
                            error.data.msg = '网络超时';
                            ElMessage.error(error.data.msg)
                            break
                        case 505:
                            error.data.msg = 'http版本不支持该请求';
                            ElMessage.error(error.data.msg)
                            break
                        default:
                            error.data.msg = `连接错误${error.response.status}`;
                            ElMessage.error(error.data.msg)
                    }
                } else {
                    error.data.msg = "连接到服务器失败";
                    ElMessage.error(error.data.msg)
                }
                return Promise.reject(error)
        })
    }
    // resful api 封装
    get<T=Result>(url:string,params?:object):Promise<T>{
        return this.instance.get(url,{params})
    }
    // post请求
    post<T=Result>(url:string,data?:object):Promise<T>{
        return this.instance.post(url,data)
    }
    // put请求
    put<T=Result>(url:string,data?:object):Promise<T>{
        return this.instance.put(url,data)
    }
    // delete请求
    delete<T=Result>(url:string):Promise<T>{
        return this.instance.delete(url)
    }
    //图片上传
    upload<T = Result>(url: string, params?: object): Promise<T> {
        return this.instance.post(url, params, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
    }
}
// 导出工具类
export default new Http(config)