<template>
    <div class="common-layout">
      <el-container style="height: 800px">
        <el-header>
          <h1>Neusoft&nbsp;&nbsp;东软体检报告管理系统</h1>
          <p>医生：{{ doctor.realName }}</p>
        </el-header>
        <el-container>
          <el-aside
            width="260px"
            style="background-color: #e5edf9; padding: 10px"
          >
            <el-descriptions
              class="margin-top"
              title="预约客户信息"
              :column="1"
              :size="size"
              border
            >
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
            <el-button
              type="primary"
              style="margin-top: 16px"
              @click="toOrdersList"
              >查询体检用户</el-button
            >
          </el-aside>
          <el-main style="background-color: #fdffff">
            <div class="main-box">
              <el-collapse v-model="activeNames" @change="handleChange">
                <el-collapse-item :title="report.ciName"  v-for=" (report,ciIndex) in cireportList">
                  <el-row style="box-sizing: border-box;padding: 1px 5px;">
                    <el-col :span="12" v-for="(detailedreport,cidrIndex) in report.detailedreportList"
                    style="box-sizing: border-box;padding: 1px 5px;">
                      <span
                        style="
                          background-color: #ba634e;
                          color: #fff;
                          box-sizing: border-box;
                          padding: 1px 3px;
                          border-radius: 3px;
                          margin-right: 5px;
                        "
                        v-if="detailedreport.isError==1"
                        >异</span
                      ><span style="margin-right: 5px; vertical-align: top"
                        >{{ detailedreport.name }}</span
                      >
                      <el-input
                        style="width: 26%; margin-right: 2px"
                        size="small"
                        v-model="cireportList[ciIndex].detailedreportList[cidrIndex].value"
                        :placeholder="detailedreport.name"
                        @blur="cidrCheckByBlur(ciIndex, cidrIndex)"
  
                      />
                      <span style="margin-right:5px;">{{detailedreport.unit}}</span>
                      <span v-if="detailedreport.normalValueString">正常值范围：{{detailedreport.normalValueString}}</span>
                    </el-col>
                   
                  </el-row>
                  <el-button type="primary" size="small"
                   @click="updateCiDetailedReport(ciIndex)"
                  >{{report.ciName}}项保存</el-button>
                </el-collapse-item>
                
                
              </el-collapse>
              <!-- 总检内容查询结果列表数据--->
              <el-card class="box-card" style="margin-top: 20px; width: 100%">
                <template #header>
                  <div class="card-header">
                    <span>总检结论</span>
                    <el-button type="danger">体检套餐总检结果报告归档</el-button>
                  </div>
                </template>
                <el-table :data="overallresultList" style="width: 100%">
                  <el-table-column fixed prop="orId" label="总检结论编号" width="150" />
                  <el-table-column prop="title" label="标题" width="320" />
                  <el-table-column prop="content" label="内容" width="320" />
  
                  <el-table-column fixed="right" label="操作" width="120">
                    <template #default>
                      <el-button
                        link
                        type="primary"
                        size="small"
                        @click="dialogVisible = true"
                        >编辑</el-button
                      >
                      <el-button link type="primary" size="small">清空</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-card>
              <el-form label-width="100px" style="margin-top: 20px">
                    <el-form-item label="总检结论标题">
                    <el-input
                    style="width: 40%; margin-right: 2px"
                      placeholder="总检结论标题"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="总检结论内容">
                    <el-input
                      :rows="2"
                      type="textarea"
                      placeholder="总检结论内容"
                      style="width: 40%; margin-right: 2px"
                    ></el-input>
                  </el-form-item>
  
  
                    <el-form-item>
                      <el-button type="primary">添加</el-button
                      >
                      <el-button>清空</el-button>
                    </el-form-item>
                  </el-form>
              <!--修改总检结论时使用的对话框-->
              <el-dialog
                v-model="dialogVisible"
                title="总检结论"
                width="40%"
                :before-close="handleClose"
              >
                <span>
                  <el-form label-width="120px">
                    <el-form-item label="标题">
                      <el-input />
                    </el-form-item>
                    <el-form-item label="内容">
                      <el-input />
                    </el-form-item>
  
                    <el-form-item>
                      <el-button type="primary"
                        @click="dialogVisible=false">更新</el-button
                      >
                      <el-button @click="dialogVisible=false">退出</el-button>
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
  import { reactive, toRefs } from "vue";
  import { useRouter,useRoute} from "vue-router";
  import { getSessionStorage } from "@/common";
  import axios from "axios";
  axios.defaults.baseURL = "http://localhost:9090";
  
  export default {
    setup() {
      const router = useRouter();
      const route=useRoute();
      const state = reactive({
        overallresultList:[],//存放当前订单的总检结论
        cireportList:[],   //存放查询的报告模板数据
        users:{},
        orders:{
          users:{},
          setmeal:{},
          hospital:{}
        },  
        dialogVisible: false,
        doctor: getSessionStorage("doctor"),
        tableData: [
          {
            date: "2016-05-03",
            name: "Tom",
            state: "California",
            city: "Los Angeles",
            address: "No. 189, Grove St, Los Angeles",
            zip: "CA 90036",
            tag: "Home",
          },
          {
            date: "2016-05-02",
            name: "Tom",
            state: "California",
            city: "Los Angeles",
            address: "No. 189, Grove St, Los Angeles",
            zip: "CA 90036",
            tag: "Office",
          },
          {
            date: "2016-05-04",
            name: "Tom",
            state: "California",
            city: "Los Angeles",
            address: "No. 189, Grove St, Los Angeles",
            zip: "CA 90036",
            tag: "Home",
          },
          {
            date: "2016-05-01",
            name: "Tom",
            state: "California",
            city: "Los Angeles",
            address: "No. 189, Grove St, Los Angeles",
            zip: "CA 90036",
            tag: "Office",
          },
        ],
      });
  
      //跳转到订单列表界面
      function toOrdersList() {
        router.push("/orderslist");
      }
  
      //初始化方法一：获取用户基本信息
      initUsers();
      function initUsers(){
        state.users=JSON.parse(route.query.users);
        // axios
        //   .post("orders/getOrdersByOrderId",{orderId:route.query.orderId})
        //   .then((response) => {
        //     state.orders=response.data;
        //   })
        //   .catch((error) => {
        //     console.log(error);
        //   });
      }
  
      //初始化方法二：获取报告模板数据
      initCireportTemplate();
      function initCireportTemplate(){
        axios
          .post("cireport/getReportTemplate",{orderId:route.query.orderId})
          .then((response) => {
            state.cireportList=response.data.data;
            console.log(state.cireportList);
          })
          .catch((error) => {
            console.log(error);
          });
      }
  
       //初始化方法三：获取总检结论数据
      initOverallresult();
      function initOverallresult(){
        axios
          .post("overallResult/getOverallResultByOrderId",{orderId:route.query.orderId})
          .then((response) => {
            state.overallresultList=response.data.data;
            
          })
          .catch((error) => {
            console.log(error);
          });
      }
  
      //检查项保存事件
      function updateCiDetailedReport(ciIndex){
           //表单验证（1：非空；2：当type==1时验证是否为数字）
        let cidrArr = state.cireportList[ciIndex].detailedreportList;  //某个大项对应的小项集合
        for (let i = 0; i < cidrArr.length; i++) {
          if (cidrArr[i].value == "") {
            alert(cidrArr[i].name + "不能为空！");
            return;
          }
          if (
            cidrArr[i].type == 1 &&
            parseFloat(cidrArr[i].value).toString() == "NaN"  // not a number
          ) {
            alert(cidrArr[i].name + "必须为数字！");
            return;
          }
        }
  
        //压缩传给服务端的参数   //更新操作只需要 orderId  detailedReportId  value  isError
   //封装提交参数（压缩提交参数）
        let arr = [];
        for (let i = 0; i < cidrArr.length; i++) {
          arr.push({
            cidrId: cidrArr[i].cidrId,
            value: cidrArr[i].value,
            isError: cidrArr[i].isError,
          });
        }
        axios
          .post("cidetailedReport/updateCidetailedreportList", arr)
          .then((response) => {
            if (response.data.data > 0) {
              alert("保存成功！");
              initCireportTemplate();
            } else {
              alert("保存失败！");
            }
          })
          .catch((error) => {
            console.error(error);
          });
  
  
  
  
    }
  
    function cidrCheckByBlur(ciIndex, cidrIndex) {
        //获取当前选中的检查项明细
        //state.cireportList[ciIndex].detailedreportList;
        let cidr = state.cireportList[ciIndex].detailedreportList[cidrIndex];
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
  
  
      //把数据和函数暴露出去，不然，html访问不到
      return {
        ...toRefs(state),
        toOrdersList,
        cidrCheckByBlur,
        updateCiDetailedReport
      };
    },
  };
  </script>
    
    <style scoped>
  .el-header {
    background-color: #b3c0d1; /*设置header的背景色*/
    text-align: center;
    /*line-height: 60px;*/
    color: #1c51a3; /*设置header的字体颜色*/
    display: flex;
    align-items: center; /**竖直方向居中*/
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
  .el-aside {
  }
  
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