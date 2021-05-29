var Main = {
    data() {
        return {
            ruleForm: {
                userID: '',
                passWord: ''
            },
            rules: {
                userID: [{
                    required: true,
                    message: '请输入用户账号',
                    trigger: 'blur'
                }],
                passWord: [{
                    required: true,
                    message: '请输入密码',
                    trigger: 'blur'
                }]
            },
            dialogVisible: false,      //隐藏注册弹窗
        }
    },
    mounted(){
        this.ruleForm.userID=localStorage.getItem("id")
        this.ruleForm.passWord=localStorage.getItem("password")
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    //实现登录
                    this.login();

                } else {
                    return false;
                }
            });
        },
        //重置
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        //登录
        login(){
            axios.post('user?action=login',Qs.stringify(this.ruleForm))
                .then(resp=>{
                    if(resp.data.code==0){
                        //window.sessionStorage.removeItem("msg");//移除错误信息
                        localStorage.setItem("id",this.ruleForm.userID)
                        localStorage.setItem("password",this.ruleForm.passWord)
                        location.href='administration.html';
                    }else{
                        //设置错误信息
                        this.$message.error(resp.data.msg);
                    }
                })
        },
        //注册
        register(){
            this.dialogVisible = true
        },
        //关闭弹窗
        handleCloseDialog() {
            this.dialogVisible = false;
            location.reload();
        },
    },
}
var Ctor = Vue.extend(Main)
var vm = new Ctor().$mount('#app')