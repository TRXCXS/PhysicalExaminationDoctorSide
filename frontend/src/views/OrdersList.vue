<template>
    <div class="common-layout">
        <el-container style="height: 600px;">
            <el-header style="background-color: #bfcce2;">
                <h1>Neusoft&nbsp;&nbsp; 东软体检报告管理系统</h1>
                <p>医生:</p>
            </el-header>
            <el-container>
                <el-aside style="background-color: #e5edf9;" width="249px">
                    <el-form :model="selectForm" label-width="80px">
                        <h4>体检用户查询</h4>
                        <el-form-item label="手机号码">
                            <el-input v-model="selectForm.userId"/>
                        </el-form-item>
                        <el-form-item label="用户姓名">
                            <el-input v-model="selectForm.realName"/>
                        </el-form-item>
                        <el-form-item label="性别">
                            <el-radio-group v-model="selectForm.sex">
                                <el-radio :label="1">男</el-radio>
                                <el-radio :label="0">女</el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-form-item label="套餐类型">
                            <el-select v-model="selectForm.smId">
                                <el-option :value="0" label="全部"/>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="体检日期">
                            <el-date-picker v-model="selectForm.orderDate" placeholder="选择体检日期"
                                            style="width: 100%"
                                            type="date"/>
                        </el-form-item>
                        <el-form-item label="是否归档">
                            <el-radio-group v-model="selectForm.state">
                                <el-radio :label="1" border>未归档</el-radio>
                                <el-radio :label="2" border>已归档</el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="select">查询</el-button>
                            <el-button type="warning">重置</el-button>
                        </el-form-item>
                    </el-form>
                </el-aside>

                <el-main style="background-color: #fdffff;">
                    <el-table :data="tableData" style="width: 100%">
                        <el-table-column fixed label="预约编号" prop="date" width="90"/>
                        <el-table-column label="手机号码" prop="name" width="120"/>
                        <el-table-column label="姓名" prop="state" width="120"/>
                        <el-table-column label="性别" prop="city" width="80"/>
                        <el-table-column label="套餐类型" prop="address" width="150"/>
                        <el-table-column label="体检医院" prop="zip" width="auto"/>
                        <el-table-column label="体检日期" prop="zip" width="120"/>
                        <el-table-column fixed="right" label="操作" width="120">
                            <template #default>
                                <el-button link size="small" type="primary" @click="handleClick">编辑体检报告
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <el-pagination :page-size="10" :total="200" background layout="prev, pager, next"/>

                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import {reactive, toRefs} from 'vue';
import {useRoute} from 'vue-router';
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:9090'
export default {
    setup() {
        const router = useRoute();
        const state = reactive({
            selectForm: {
                userId: '',
                smId: 0,
                orderDate: '',
                realName: '',
                sex: 1,
                state: 1
            },
            tableData: []
        })

        function select() {
            console.log(state.selectForm)
        }

        return {
            ...toRefs(state),
            select
        }
    }
};
</script>

<style scoped>
.el-header {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.el-header h1 {
    color: blue;

}

.el-header p {
    color: cornflowerblue;
}

.el-form {
    margin-left: 10px;
    margin-right: 10px;
}

.el-form h4 {
    margin-top: 20px;
    margin-bottom: 20px;
    color: darkgray;
}

.el-table {
    margin-bottom: 10px;
}

.el-pagination {
    margin-left: 30%;
}
</style>