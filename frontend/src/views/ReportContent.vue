<template>
  <div class="common-layout">
    <el-container style="height: 800px">
      <el-header>
        <h1>Neusoft&nbsp;&nbsp;东软体检报告管理系统</h1>
        <p>医生：{{ doctor.realName }}</p>
      </el-header>
      <el-container>
        <el-aside width="260px"
          style="background-color: #e5edf9; padding: 10px">
          <el-descriptions class="margin-top"
            title="预约客户信息"
            :column="1"
            :size="size"
            border>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">预约编号</div>
              </template>
              {{ users.orderId }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">手机号码</div>
              </template>
              {{ users.userId }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">真实姓名</div>
              </template>
              {{ users.realName }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">性别</div>
              </template>
              {{ users.sex }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">套餐类型</div>
              </template>
              {{ users.setmealName }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">体检日期</div>
              </template>
              {{ users.orderDate }}
            </el-descriptions-item>
          </el-descriptions>
          <el-button type="primary"
            style="margin-top: 16px; margin-left:20%"
            @click="toOrdersList">返回用户列表</el-button>
        </el-aside>
        <el-main style="background-color: #fdffff">
          <div class="main-box">
            <el-collapse v-model="activeNames"
              @change="handleChange">
              <el-collapse-item :title="report.checkItem.ciName"
                v-for=" (report, ciIndex) in cireportList"
                :key="ciIndex">
                <el-row style="color:#888;">
                  <el-col :span="12"
                    v-for="(detailedreport, cidrIndex) in report.ciDetailedReportList"
                    :key="cidrIndex"
                    style="box-sizing: border-box;padding: 6px 10px;">
                    <span style="
                          background-color: #ba634e;
                          color: #fff;
                          box-sizing: border-box;
                          padding: 1px 3px;
                          border-radius: 3px;
                          margin-right: 5px;
                        "
                      v-if="detailedreport.isError == 1">异</span><span style="margin-right: 5px; vertical-align: top">{{
                        detailedreport.name }}</span>
                    <el-input style="width: 26%; margin-right: 2px"
                      size="small"
                      v-model="cireportList[ciIndex].ciDetailedReportList[cidrIndex].value"
                      :placeholder="detailedreport.name"
                      @blur="cidrCheckByBlur(ciIndex, cidrIndex)"
                      v-if="detailedreport.type != 4" />

                    <el-input style="width: 50%; margin-right: 2px"
                      size="small"
                      type="textarea"
                      rows="2"
                      v-model="cireportList[ciIndex].ciDetailedReportList[cidrIndex].value"
                      :placeholder="detailedreport.name"
                      @blur="cidrCheckByBlur(ciIndex, cidrIndex)"
                      v-else />
                    <span style="margin-right:5px;">{{ detailedreport.unit }}</span>
                    <span v-if="detailedreport.normalValueString">正常值范围：{{ detailedreport.normalValueString }}</span>
                  </el-col>

                </el-row>
                <el-button type="primary"
                  size="small"
                  v-if="reportState == 1"
                  @click="updateCiDetailedReport(ciIndex)">{{ report.checkItem.ciName }}项保存</el-button>
              </el-collapse-item>


            </el-collapse>
            <!-- 总检内容查询结果列表数据--->
            <el-card class="box-card"
              style="margin-top: 20px; width: 100%">
              <template #header>
                <div class="card-header">
                  <span>总检结论</span>
                  <el-button type="danger"
                    v-if="reportState == 1"
                    @click="updateOrdersState">体检套餐总检结果报告归档</el-button>
                </div>
              </template>
              <el-table :data="overallresultList"
                style="width: 100%">
                <el-table-column fixed
                  prop="orId"
                  label="总检结论编号"
                  width="150" />
                <el-table-column prop="title"
                  label="标题"
                  width="320" />
                <el-table-column prop="content"
                  label="内容"
                  width="320" />

                <el-table-column fixed="right"
                  label="操作"
                  width="120">

                  <template #default="scope">
                    <div style="display: flex;">
                      <el-button type="primary"
                        size="small"
                        @click="toUpdateOverallresult(scope.row)"
                        v-if="reportState == 1">编辑</el-button>
                      <el-button type="danger"
                        size="small"
                        @click="deleteOverallresult(scope.row)"
                        v-if="reportState == 1">删除</el-button>
                    </div>
                  </template>
                </el-table-column>
              </el-table>
            </el-card>
            <el-form label-width="100px"
              style="margin-top: 20px"
              :form="saveOverallresultForm"
              v-if="reportState == 1">
              <el-form-item label="总检结论标题">
                <el-input style="width: 40%; margin-right: 2px"
                  placeholder="总检结论标题"
                  v-model="saveOverallresultForm.title"></el-input>
              </el-form-item>
              <el-form-item label="总检结论内容">
                <el-input :rows="2"
                  type="textarea"
                  placeholder="总检结论内容"
                  v-model="saveOverallresultForm.content"
                  style="width: 40%; margin-right: 2px"></el-input>
              </el-form-item>


              <el-form-item>
                <el-button type="primary"
                  v-if="reportState == 1"
                  @click="saveOverallresult">添加</el-button>
                <el-button v-if="reportState == 1"
                  @click="clear">清空</el-button>
              </el-form-item>
            </el-form>
            <!--修改总检结论时使用的对话框-->
            <el-dialog v-model="dialogVisible"
              title="总检结论"
              width="40%"
              :before-close="handleClose">
              <span>
                <el-form label-width="120px"
                  :model="updateOverallResultForm">
                  <el-form-item label="标题">
                    <el-input v-model="updateOverallResultForm.title" />
                  </el-form-item>
                  <el-form-item label="内容">
                    <el-input v-model="updateOverallResultForm.content" />
                  </el-form-item>

                  <el-form-item>
                    <el-button type="primary"
                      @click="updateOverallResult">更新</el-button>
                    <el-button @click="dialogVisible = false">退出</el-button>
                  </el-form-item>
                </el-form>
              </span>

            </el-dialog>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
    
<script>
//导入需要的基本工具
import { onBeforeMount, reactive, toRefs } from "vue";
import { useRouter, useRoute } from "vue-router";
import { getSessionStorage, cleanForm } from "@/common";
import { ElMessage, ElMessageBox } from 'element-plus';
import axios from "axios";
axios.defaults.baseURL = "http://localhost:9090";

export default {
  setup() {
    const router = useRouter();
    const route = useRoute();
    const state = reactive({
      updateOverallResultForm: {
        orId: "",
        title: "",
        content: ""
      },
      saveOverallresultForm: {
        title: '',
        content: ''
      },//保存总检结论
      overallresultList: [],//存放当前订单的总检结论
      cireportList: [],   //存放查询的报告模板数据
      users: {},
      // orders: {
      //   users: {},
      //   setmeal: {},
      //   hospital: {}
      // },
      dialogVisible: false,
      doctor: getSessionStorage("doctor"),
      tableData: [

      ],
      reportState: 2
    });
    //去更新总检结论的对话框
    function toUpdateOverallresult(row) {
      //这里不能直接赋值为row，必须使用深拷贝。否则更新数据与原数据是绑定的
      state.updateOverallResultForm = JSON.parse(JSON.stringify(row));

      state.dialogVisible = true;
    }

    //更新总检结论
    function updateOverallResult() {
      state.updateOverallResultForm.orderId = state.users.orderId;
      axios
        .post("overallresult/updateOverallResult", state.updateOverallResultForm)
        .then((response) => {
          if (response.data) {
            ElMessage({
              type: 'success',
              message: '总检结论项更新成功！'
            });
            initOverallresult();
          } else {
            ElMessage({
              type: 'error',
              message: '总检结论项更新失败！'
            });
          }
          state.dialogVisible = false;
        })
        .catch((error) => {
          console.error(error);
        });
    }

    //删除总检结论
    function deleteOverallresult(row) {
      axios
        .get("overallresult/deleteOverallResult?orId=" + row.orId)
        .then((response) => {
          if (response.data) {
            ElMessage({
              type: 'success',
              message: '删除成功'
            });

            initOverallresult();
          } else {
            ElMessage({
              type: 'error',
              message: '删除失败！'
            });
          }
        })
        .catch((error) => {
          console.log(error);
        });
    }
    //体检结果归档
    function updateOrdersState() {
      ElMessageBox.confirm(
        '您确认所有检查项都填好了吗？',
        '',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          draggable: true,
        }
      )
        .then(() => {
          axios
            .get("order/archiveOrder?orderId=" + state.users.orderId)
            .then((response) => {
              if (response.data) {

                ElMessage({
                  type: 'success',
                  message: '该订单已经归档'
                });
                router.push('/ordersList');
              } else {
                ElMessage({
                  type: 'error',
                  message: '该订单归档失败！'
                });
              }
            })
            .catch((error) => {
              console.error(error);
            });
        })
        .catch(() => {

        })




    }
    function clear() {
      cleanForm(state.saveOverallresultForm)
    }
    //跳转到订单列表界面
    function toOrdersList() {
      router.push("/orderslist");
    }
    onBeforeMount(() => {
      initUsers();
      initCireportTemplate();
      initOverallresult();
    })
    //初始化方法一：获取用户基本信息
    function initUsers() {
      state.users = JSON.parse(route.query.users);
      state.reportState = route.query.reportState;
    }

    //初始化方法二：获取报告模板数据

    function initCireportTemplate() {
      axios
        .get("cidetailedreport/getAllCheckitemAndCheckitemDetailedReportByOrderId?orderId=" + state.users.orderId)
        .then((response) => {
          //console.log(response)
          state.cireportList = response.data;
          //console.log(state.cireportList);
        })
        .catch((error) => {
          console.log(error);
        });
    }

    //初始化方法三：获取总检结论数据
    function initOverallresult() {
      axios
        .get("overallresult/getOverallResultListByOrderId?orderId=" + state.users.orderId)
        .then((response) => {
          //console.log(response)
          //console.log(response.data)
          state.overallresultList = response.data;

        })
        .catch((error) => {
          console.log(error);
        });
    }

    //检查项保存事件
    function updateCiDetailedReport(ciIndex) {
      //表单验证（1：非空；2：当type==1时验证是否为数字）
      var numPattern = /^-?\d*\.?\d+$/;
      let cidrArr = state.cireportList[ciIndex].ciDetailedReportList;  //某个大项对应的小项集合
      for (let i = 0; i < cidrArr.length; i++) {
        if (cidrArr[i].value == "") {
          ElMessage({
            type: 'error',
            message: cidrArr[i].name + '不能为空！'
          });
          return;
        }
        if (
          cidrArr[i].type == 1 &&
          !numPattern.test(cidrArr[i].value)  // not a number
        ) {
          ElMessage({
            type: 'error',
            message: cidrArr[i].name + "必须为数字！"
          });
          return;
        }
      }
      axios
        .post("cidetailedreport/updateCiDetailedReportList", cidrArr)
        .then((response) => {
          if (response.data.isSuccess) {
            ElMessage({
              type: 'success',
              message: "保存成功！"
            });
            initCireportTemplate();
          } else {
            ElMessage({
              type: 'error',
              message: '保存失败！'
            });
          }
        })
        .catch((error) => {
          console.error(error);
        });




    }

    function cidrCheckByBlur(ciIndex, cidrIndex) {
      //获取当前选中的检查项明细
      //state.cireportList[ciIndex].detailedreportList;
      let cidr = state.cireportList[ciIndex].ciDetailedReportList[cidrIndex];
      //判断type属性（1：数值范围验证型；2：数值相等验证型；）
      if (cidr.type == 1) {
        if (
          !(cidr.value == null || cidr.value == "") &&
          (cidr.value < cidr.minrange || cidr.value > cidr.maxrange)
        ) {
          cidr.isError = 1;
        } else {
          cidr.isError = 0;
        }
      } else if (cidr.type == 2) {
        if (
          !(cidr.value == null || cidr.value == "") &&
          cidr.value != cidr.normalValue
        ) {
          cidr.isError = 1;
        } else {
          cidr.isError = 0;
        }
      }
    }
    //保存总检结论
    function saveOverallresult() {
      state.saveOverallresultForm.orderId = state.users.orderId;
      axios.post('overallresult/createOverallResult', state.saveOverallresultForm)
        .then((res => {
          if (res.data) {
            ElMessage({
              type: 'success',
              message: "保存总检结论成功！"
            });
            initOverallresult();
          } else {
            ElMessage({
              type: 'error',
              message: '保存总检结论失败！'
            });
          }
          cleanForm(state.saveOverallresultForm)
        }))
        .catch(err => {
          console.log(err)
        })

    }

    //把数据和函数暴露出去，不然，html访问不到
    return {
      ...toRefs(state),
      toOrdersList,
      cidrCheckByBlur,
      updateCiDetailedReport,
      saveOverallresult,
      toUpdateOverallresult,
      updateOverallResult,
      deleteOverallresult,
      clear,
      updateOrdersState
    };
  },
};
</script>
    
<style scoped>
.el-header {
  background-color: #b3c0d1;
  /*设置header的背景色*/
  text-align: center;
  /*line-height: 60px;*/
  color: #1c51a3;
  /*设置header的字体颜色*/
  display: flex;
  align-items: center;
  /**竖直方向居中*/
  justify-content: space-between;
}

.el-header h1 {
  font-size: 22px;
  font-weight: 700;
}

.el-header p {
  font-size: 16px;
}

/****************************  侧边栏中描述列表的样式 ***************************/
.el-aside {}

.el-descriptions {
  margin-top: 20px;
}

.cell-item {
  display: flex;
  align-items: center;
}

.margin-top {
  margin-top: 20px;
}

/****************************  侧边栏中描述列表的样式*****************************/

/****************************  主体部分的样式*****************************/
.main-box {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  padding: 10px;
}

/****************************  两列的样式*****************************/
.el-row {
  margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

/**************************** 卡片的样式 **/

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
}
</style>