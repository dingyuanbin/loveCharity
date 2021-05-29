var Main = {
    data() {
        return {
            name:'',            //管理员昵称
            loading:false,   //是否在加载
            selections:[],//多选框值
            searchValue:'',//搜索类型
            assistance: true, //是否显示报修
            completeAssistance: false, //是否显示维护完成
            restartAssistance: false, //是否显示进入维护
            dataShow: false, //是否显示日期框
            activeIndex: '1', //导航栏默认值
            selectType: '1', //选择搜索类型
            tableData: [
                {patientId:'1001',
                    patientName:'jack',
                    disease:'666',
                    submitDate:'2021-5-3'}
            ],//所有数据
            search:{
                stateValue:'1',//当前状态值
                patientId:'',   //患者id
                patientName:'', //患者姓名
                disease:'',     //所患疾病
                submitDate:'',  //提交时
                currentPage: 1, //当前页
                pageSize: 10    //页面显示数量
            },
            pagination: { //分页参数
                currentPage: 1,
                pageSize: 10,
                total: 0
            },
            pickerOptions: {
                disabledDate(time) {
                    return time.getTime() > Date.now();
                },
                shortcuts: [{
                    text: '今天',
                    onClick(picker) {
                        picker.$emit('pick', new Date());
                    }
                }, {
                    text: '昨天',
                    onClick(picker) {
                        const date = new Date();
                        date.setTime(date.getTime() - 3600 * 1000 * 24);
                        picker.$emit('pick', date);
                    }
                }, {
                    text: '一周前',
                    onClick(picker) {
                        const date = new Date();
                        date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', date);
                    }
                }]
            },

        };
    },
    mounted() {
        //初始化数据
        this.getData()
        axios.post('user?action=getUserName')
            .then((resp)=>{
                let name=resp.data
                this.name=name
            })
    },
    methods: {
        //注销
        handleCommand(command) {
            axios.post('user?action=cancellation')
                .then(()=>{
                    this.$message('注销成功！' );
                    location.reload();
                })
        },
        //不同按钮操作的响应
        action(row,index){
            let value=this.search.stateValue*1
            let buttonName
            if(this.search.stateValue==1){
                buttonName='进行援助'
            }else if(this.search.stateValue==2){
                buttonName='完成援助'
            }else if(this.search.stateValue==3){
                buttonName='重新援助'
            }

            this.$confirm('确定'+buttonName+'吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                axios.get("patientList?action=changeState",
                    {params:{stateValue:value,patientId:row.patientId}
                    }).then((res)=>{
                    this.tableData.splice(index, 1)
                    this.$message({
                        type: 'success',
                        message: '操作成功!'
                    });
                }).catch((error)=>{
                    this.$message({
                        type: 'error',
                        message: '操作失败'
                    });
                })

            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消操作'
                });
            });
        },
        //显示不同按钮
        isShow(key) {
            if (key == 1) {
                this.assistance = true
                this.completeAssistance = false
                this.restartAssistance = false
            } else if (key == 2) {
                this.assistance = false
                this.completeAssistance = true
                this.restartAssistance = false
            } else {
                this.assistance = false
                this.completeAssistance = false
                this.restartAssistance = true
            }
        },
        //不同状态显示
        handleSelect(key) {
            this.search.stateValue=key
            this.searchValue=''
            this.search.submitDate=''
            this.getData()
            this.isShow(key)
        },
        //改变输入框
        changeShow(value) {
            if (value == 3) {
                this.dataShow = true
            } else {
                this.dataShow = false
            }
        },
        //查询
        handleQuery() {
            if(this.selectType==1){
                this.search.patientName=this.searchValue
                this.search.disease=''
                this.search.submitDate=''
            }
            else if(this.selectType==2){
                this.search.patientName=''
                this.search.disease=this.searchValue
                this.search.submitDate=''
            }
            else{
                this.search.patientName=''
                this.search.disease=''
            }
            this.getData();
        },
        //页面大小改变事件
        handleSizeChange(val) {
            this.pagination.pageSize = val;
            this.getData();
        },
        //当页面改变事件
        handleCurrentChange(val) {
            this.pagination.currentPage = val;
            this.getData();
        },

        //获取列表参数
        getData() {
            this.loading=true
            this.tableData=[]//清空数组（表格数据）
            //改变search对的当前和页面大小
            this.search.currentPage = this.pagination.currentPage;
            this.search.pageSize = this.pagination.pageSize;
            axios.get("patientList?action=list", {params: this.search})
                .then((resp) => {
                    var result = resp.data; //获取控制器回传的数据
                    console.log(result)
                    if (result.code == 0 && result.data != null) {
                        this.tableData = result.data.data; //绑定表格数据
                        this.pagination = result.data; //绑定分页参数
                    }
                    this.loading=false
                }).catch((error)=>{
                this.$message.error('加载失败，请刷新重试');
                this.loading=false
            })

        },
        //删除所选项
        deleteSelection(){
            if(this.selections.length==0){
                this.$notify({
                    title: '警告',
                    message: '请先勾选需要删除的行',
                    type: 'warning'
                });
                return
            }
            this.$confirm('此操作将删除所选患者信息，无法恢复, 是否继续?', '危险操作', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                for(let i=0;i<this.selections.length;i++){
                    console.log(this.selections)
                    axios.get("patientList?action=delete", {params: {patientId:this.selections[i].patientId}})
                        .then(()=>{
                            location.reload();
                        }).catch((error)=>{
                        this.$message.error('删除失败');
                    })
                }
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        //存储多选框的值
        allSelect(value){
            this.selections=value
        },
        //显示患者详细信息
        detail(row) {
            window.location.href=`patientDetail.html?patientId=${row.patientId}`
        },
    }
}
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')