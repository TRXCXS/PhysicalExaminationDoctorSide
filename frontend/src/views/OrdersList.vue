<template>
    <div class="common-layout">
        <el-container style="height: 600px;">
            <el-header style="background-color: #bfcce2;">
                <h1>Neusoft&nbsp;&nbsp; 东软体检报告管理系统</h1>
                <span>
                    <p>医生:{{ doctor.realName }}</p>
                    <el-button type="danger" @click="exit">退出</el-button>
                </span>
            </el-header>
            <el-container>
                <el-aside style="background-color: #e5edf9;" width="249px">
                    <el-form :model="selectForm" label-width="80px">
                        <h4>体检用户查询</h4>
                        <el-form-item label="手机号码">
                            <el-input v-model="selectForm.userId" />
                        </el-form-item>
                        <el-form-item label="用户姓名">
                            <el-input v-model="selectForm.realName" />
                        </el-form-item>
                        <el-form-item label="性别">
                            <el-radio-group v-model="selectForm.sex">
                                <el-radio :label="1">男</el-radio>
                                <el-radio :label="0">女</el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-form-item label="套餐类型">
                            <el-select v-model="selectForm.smId">
                                <el-option label="全部" :value="-1" />
                                <el-option v-for="item in mealList" :key="item.smId" :label="item.name"
                                    :value="item.smId" />
                            </el-select>
                        </el-form-item>
                        <el-form-item label="体检日期">
                            <el-date-picker v-model="selectForm.orderDate" placeholder="选择体检日期" style="width: 100%"
                                type="date" />
                        </el-form-item>
                        <el-form-item label="是否归档">
                            <el-radio-group v-model="selectForm.state">
                                <el-radio :label="1" border>未归档</el-radio>
                                <el-radio :label="2" border>已归档</el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="getTableData">查询</el-button>
                            <el-button type="warning">重置</el-button>
                        </el-form-item>
                    </el-form>
                </el-aside>

                <el-main style="background-color: #fdffff;">
                    <el-table :data="tableData" style="width: 100%"> 
                        <el-table-column v-for="item in tableHeader" :key="item.label" 
                            :label="item.label" :width="item.width">
                        </el-table-column>    
                        <el-table-column fixed="right" label="操作" width="120">
                            <template #default>
                                <el-button link size="small" type="primary" @click="handleClick">编辑体检报告
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <el-pagination background layout="prev, pager, next" :total="200" :page-size="10" />

                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import { onBeforeMount, reactive, toRefs } from 'vue';
import { getSessionStorage, removeSessionStorage } from '@/common'
import { useRouter } from 'vue-router';
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:9090'
export default {
    setup() {
        const router = useRouter();
        const state = reactive({
            selectForm: {
                userId: '',
                smId: -1,
                orderDate: '',
                realName: '',
                sex: -1, // -1表示没有限制
                state: -1 // ~
            },
            tableData: [

            ],
            mealList: [

            ],
            doctor: {
                realName: ''
            },

            //存储列表 表头数据
            tableHeader: [
                { label: '预约编号', prop: 'orderId', width: '90' },
                { label: '手机号码', prop: 'userId', width: '120' },
                { label: '姓名', prop: 'realName', width: '120' },
                { label: '性别', prop: 'sex', width: '80' },
                { label: '套餐类型', prop: 'smId', width: '150' },
                { label: '体检医院', prop: 'hpId', width: 'auto' },
                { label: '体检日期', prop: 'orderDate', width: '120' }
            ],
        })


        onBeforeMount(() => {
            //获取医生姓名
            getDoc();
            //获取套餐数据
            getMealList();
            //获取列表数据
            getTableData();
        })
        function exit() {
            removeSessionStorage('doctor');
            router.push('/login');
        }
        function getDoc() {
            //获取医生姓名
            let doctor = getSessionStorage('doctor');
            if (doctor) {
                state.doctor = doctor;
            } else {
                state.doctor = {
                    realName: ''
                }
            }
        }
        function getMealList() {
            //获取套餐数据
            axios.post('setmeal/getAllSetmeal')
                .then(res => {
                    let data = res.data.data;
                    state.mealList = data;
                })
                .catch(err => {
                    console.log(err);
                })
        }
        function getTableData() {
            console.log(state.selectForm)
            //获取列表数据
            axios.post('orders/query', state.selectForm, state.selectForm)
                .then(res => {
                    let data = res.data.data;
                    state.tableData = data;
                    //遍历修改每个元素的sex
                })
                .catch(err => {
                    console.log(err);
                })
        }
        return {
            ...toRefs(state),
            getDoc,
            getMealList,
            getTableData,
            exit
        }
    },
};
</script>

<style scoped>
.el-header {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.el-header span {
    display: flex;
    align-items: center;
}

.el-header h1 {
    color: blue;

}

.el-header p {
    color: cornflowerblue;
    margin-right: 5px;
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