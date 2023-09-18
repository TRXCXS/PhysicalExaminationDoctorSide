<template>
    <el-card class="my-card">
        <div class="container"> 
        <el-card class="box-card">
            <template #header>
                <div class="card-header">
                    <span class="text">欢迎登录</span>
                </div>
            </template>
            <el-form :model="loginForm" label-width="120px">
                <div class="centera">
                <el-form-item  label="医生编码">
                    <el-input v-model="loginForm.docCode"
                    class="fixed-length-input" />
                </el-form-item>
            </div>
                <div class="centera"> 
                <el-form-item  label="登录密码">
                    <el-input 
                    v-model="loginForm.password"
                    type="password"
                    show-password
                    class="fixed-length-input" 
                />
                    
                </el-form-item>
            </div>
                <el-form-item>
                    <el-button type="primary" style="margin-left: 15%;" @click="login">登录</el-button>
                    <!-- <el-button type="success" @click="toRegister">注册</el-button> -->
                </el-form-item>
            </el-form>
        </el-card>
        </div>  
    </el-card> 
    </template>
    
    <script>
    import {reactive, toRefs} from 'vue'
    import {useRouter} from 'vue-router'
    import {cleanForm, isFormLack, setSessionStorage} from '@/common';
    import axios from 'axios'
    import {ElMessage} from 'element-plus'
    
    
    axios.defaults.baseURL = 'http://localhost:9090'
    export default {
        data() {
          return {
            url: '../img/img1.jpg',
            urlList: [],
            timer: null
          }
        },
    
        setup() {
            //声明需要的数据变量
            const router = useRouter();
            const state = reactive({
                loginForm: {
                    docCode: '',
                    password: ''
                }
            });
            //配合注释路由守卫看页面用
            // function login() {
            //     console.log('here')
            //     router.push('/orderslist')
            // }
            function login() {
                let map = new Map([
                    ['docCode', '医生编码'],
                    ['password', '密码']
                ])
                let key = isFormLack(state.loginForm)
                if (key) {
                    ElMessage({
                        type: 'error',
                        message: map.get(key) + '不能为空'
                    });
                    return;
                }
    
                axios.post('doctor/login', state.loginForm)
                    .then(response => {
                        let doctor = response.data.data;
                        //console.log(doctor)
                        if (doctor) {
                            setSessionStorage('doctor', doctor)
                            router.push('/orderslist')
                        } else {
                            ElMessage({
                                type: 'error',
                                message: '医生编码或者密码错误！'
                            });
                            //cleanForm(state.loginForm)
                            state.loginForm.password='';
                        }
                    })
                    .catch(error => {
                        console.log(error)
                    })
            }
    
            function toRegister() {
                router.push('/register')
            }
    
            //把数据和函数暴露出去，不然，html访问不到
            return {
                ...toRefs(state),
                login,
                toRegister
            }
    
        }
    };
    </script>
    
    <style lang="scss" scoped>
    .card-header {
      display: flex;
      justify-content: center;
      text-align: center;  
    
    }
    
    .text {
      font-size: 25px;
      font-weight: bold;
    }
    
    .item {
      margin-bottom: 18px;
      
    }
    
    .box-card {
    max-width: 420px; //设置卡片的最大宽度
      margin: 0 auto; // 水平居中
      position:absolute; //设置绝对定位
      top: 50%; //垂直居中
      left: 50%; //水平居中  
      transform: translate(-50%, -50%); //通过位移使卡片居中
      width: 100%; // 设置卡片宽度为100%
    //   background-color: #369fa2a2;
    // background: linear-gradient(to right, rgb(36, 156, 142), rgba(88, 205, 207, 0.1));  
    }
    
    
    .el-input {
      width: 70%
    }
    
    .my-card {  
      background-color: #f5f5f5; //背景颜色
      background-image: url('src/img/img2.png '); //背景图片
      background-position: center center;
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-size: cover;
    } 
    
    
    
    .container {  
      display: flex;  
      justify-content: center;  
      align-items: center;  
      height: 100vh; //设置容器高度为视口高度
      position: relative; //设置相对定位
    }  
    
    .fixed-length-input {  
        width: 200px; //设置固定的宽度
    }
    
    // .texta {
    //     font-size: 16px;
    //   font-weight: bold;
    // }
    
    .custom-form-item  {  
        font-weight: bold; // 加粗  
        font-size: 18px; // 加大字体，你可以根据需要调整这个值
    }
    
    </style>