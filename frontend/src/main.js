

import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'

createApp(App).use(ElementPlus).use(router).mount('#app')

router.beforeEach(function(to,from,next){  //这个函数会在路由跳转之前执行

    //确认是否放行
    //1.从sessionStorage中取出users
    let users=sessionStorage.getItem('doctor');
        //你的访问路径  /     /login  /register    这三个要放行
        if(!(to.path=='/'||to.path=='/login'||to.path=='/register')){
           if(users===null){
              router.push('/login');
           }else{
            next();
           }
        }else{
            next();
        }

   
    });