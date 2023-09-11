<template>
    <el-card class="box-card">
        <template #header>
            <div class="card-header">
                <span>注册</span>
            </div>
        </template>
        <el-form :model="doctor" label-width="120px">

            <el-form-item label="医生编码">
                <el-input v-model="doctor.docCode" />
            </el-form-item>
            <el-form-item label="真实姓名">
                <el-input v-model="doctor.realName" />
            </el-form-item>
            <el-form-item label="性别">
                <el-radio-group v-model="doctor.sex">
                    <el-radio :label="1">男性</el-radio>
                    <el-radio :label="0">女性</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="所属科室">
                <el-radio-group v-model="doctor.deptno">
                    <el-radio :label="1" >检验科</el-radio>
                    <el-radio :label="2">内科</el-radio>
                    <el-radio :label="3">外科</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="doctor.password" />
            </el-form-item>
            <el-form-item label="确认密码">
                <el-input v-model="confirmPassword" @blur="confirmPwd" />
            </el-form-item>
            <el-form-item>
                <el-button type="success" @click="register">注册</el-button>
                <el-button type="danger" @click="exit">返回</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>
  
<script>
import { reactive, toRefs } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import { isFormLack } from '../common'
import { ElMessage } from 'element-plus'
axios.defaults.baseURL = 'http://localhost:9090/tijian'
export default {
    setup() {
        const router = useRouter();
        const state = reactive({
            doctor: {
                docCode: '',
                realName: '',
                password: '',
                sex: 1,
                deptno: 1
            },
            confirmPassword: ''
        })
        function register() {
            let map = new Map([
                ['docCode', '医生编码'],
                ['realName', '真实姓名'],
                ['password', '密码'],
            ])
            let key = isFormLack(state.doctor)
            if (key&&map.get(key)) {
                ElMessage({
                    type: 'error',
                    message: map.get(key) + '不能为空'
                })
                return;
            }
            if (!confirmPwd()) return;

            state.doctor.docId= '';
            console.log(state.doctor);
            axios.post('doctor/register', state.doctor)
                .then(res => {
                    if (!res.data.isSuccess) {
                        ElMessage({
                            type: 'danger',
                            message: '注册失败'
                        })
                    } else {
                        ElMessage({
                            type: 'success',
                            message: '注册成功'
                        })
                        router.push('/login');
                    }
                })
                .catch(err => {
                    console.log(err)
                })
        }
        function confirmPwd() {
            if (state.doctor.password != state.confirmPassword) {
                ElMessage({
                    type: 'error',
                    message: '两次密码不一致'
                })
                return false;
            }
            return true;
        }
        function exit(){
            router.push('/login')
        }
        return {
            ...toRefs(state),
            register,
            confirmPwd,
            exit
        }
    }
}
</script>
<style>
.text {
    font-size: 14px;
}

.item {
    margin-bottom: 18px;
}

.box-card {
    margin:auto;
    margin-top:10%;
    width: 480px;
}
</style>