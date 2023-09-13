<template>
    <div class="common-layout">
        <el-container style="height: 600px;">
            <el-header style="background-color: #bfcce2;">
                <h1>Neusoft&nbsp;&nbsp; 东软体检报告管理系统</h1>
                <span>
                    <p>医生:{{ doctor.realName }}</p>
                    <el-button type="danger" @click="exit" size="small">退出</el-button>
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
                                type="date" format="YYYY/MM/DD" value-format="YYYY-MM-DD" />
                        </el-form-item>
                        <el-form-item label="是否归档">
                            <el-radio-group v-model="selectForm.state">
                                <el-radio :label="1" border>未归档</el-radio>
                                <el-radio :label="2" border>已归档</el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="toQuery(1)">查询</el-button>
                            <el-button type="warning" @click="toReset">重置</el-button>
                        </el-form-item>
                    </el-form>
                </el-aside>

                <el-main style="background-color: #fdffff;">
                    <el-table :data="ordersPageResponseDto.orderResponseDTOBodyList" style="width: 100%">
                        <el-table-column v-for="item in tableHeader" :key="item.label" :label="item.label" :prop="item.prop"
                            :width="item.width">
                        </el-table-column>
                        <el-table-column fixed="right" label="操作" width="120">
                            <template #default>
                                <el-button link size="small" type="primary" >编辑体检报告
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <el-pagination background layout="prev, pager, next, total" :total="ordersPageResponseDto.totalCount"
                        :page-size="ordersPageResponseDto.maxLineNumberOfPage" 
                        @current-change="currentChange"
                        />

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
                state: 1 // ~
            },

            ordersPageResponseDto: {},

            mealList: [

            ],
            doctor: {
                realName: ''
            },

            //存储列表 表头数据
            tableHeader: [
            // {"orderId":100569001,"userId":"12345671111","realName":"叶文洁","sex":0
            // /"setmealName":"女士-基础套餐","hospitalName":"沈阳熙康云医院-和平院区","
            //orderDate":"2022-12-31"
                { label: '预约编号', prop: 'orderId', width: '100' },
                { label: '手机号码', prop: 'userId', width: '120' },
                { label: '真实姓名', prop: 'realName', width: '90' },
                { label: '性别', prop: 'sex', width: '60' },
                { label: '套餐类型', prop: 'setmealName', width: '200' },
                { label: '体检医院', prop: 'hospitalName', width: '120' },
                { label: '体检日期', prop: 'orderDate', width: '120' }
            ],
        })


        onBeforeMount(() => {
            //获取医生姓名
            getDoc();
            //获取套餐数据
            getMealList();
            //获取列表数据
            toQuery(1);
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
        function toQuery(currentPage) {
            //state.ordersPageResponseDto={};
            state.selectForm.currentPageNumber = currentPage;
            state.selectForm.maxLineNumberOfPage = 10;
            console.log(state.selectForm)
            //获取列表数据
            //orders/query
            axios.post('order/getOrdersByOrderRequestDTO', state.selectForm)
                .then(res => {
                    let data = res.data.data;
                    state.ordersPageResponseDto = data;
                    let list = state.ordersPageResponseDto.orderResponseDTOBodyList;
                    list.map((item, index) => {
                        item.sex = item.sex==1 ? '男' : '女'
                    })
                })
                .catch(err => {
                    console.log(err);
                })
        }
        function currentChange(currentPageNumber){
            toQuery(currentPageNumber);
        }
        function toReset(){
            state.selectForm= {
                userId: '',
                smId: -1,
                orderDate: '',
                realName: '',
                sex: -1, // -1表示没有限制
                state: 1 // ~
            }
        }
        return {
            ...toRefs(state),
            getDoc,
            getMealList,
            toQuery,
            currentChange,
            exit,
            toReset
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