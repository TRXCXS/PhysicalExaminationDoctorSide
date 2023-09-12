<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <span>登录</span>
            </div>
        </template>
        <el-form :model="loginForm" label-width="120px">
            <el-form-item label="医生编码">
                <el-input v-model="loginForm.docCode"/>
            </el-form-item>
            <el-form-item label="登录密码">
                <el-input v-model="loginForm.password"/>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="login">登录</el-button>
                <el-button type="success" @click="toRegister">注册</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script>
import {reactive, toRefs} from 'vue'
import {useRouter} from 'vue-router'
import {cleanForm, isFormLack, setSessionStorage} from '@/common';
import axios from 'axios'
import {ElMessage} from 'element-plus'

axios.defaults.baseURL = 'http://localhost:9090/tijian'
export default {
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
                    if (doctor) {
                        setSessionStorage('doctor', doctor)
                        router.push('/orderslist')
                    } else {
                        ElMessage({
                            type: 'error',
                            message: '医生编码或者密码错误！'
                        });
                        cleanForm(state.loginForm)
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
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 480px;
  margin: auto;
  margin-top: 10%
}

.el-input {
  width: 70%
}
</style>