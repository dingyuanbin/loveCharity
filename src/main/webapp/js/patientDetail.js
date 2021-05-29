var Main = {
    data() {
        return{
            ip:'http://121.5.165.247/images/',//图片存储ip地址
            info:{},                          //表格数据
            patientId:'',                     //患者id
            title:'',                         //标题
            economicSituation:[],             //图片url
            proofOfDiagnosis:[],
            paymentReceipt:[],
            patientPhotos:[],
            memberOfFamily:[],
        }
    },
    mounted() {
        const loading = this.$loading({
            lock: true,
            text: '拼命加载中，请稍等',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
        });
        let request = this.GetRequest()
        this.patientId = request['patientId']
        axios.get("patientList?action=getPatientInfo",{params:{patientId:this.patientId}})
            .then((resp)=>{
                let result = resp.data; //获取控制器回传的数据
                if (result.code == 0 && result.data != null) {
                    this.info = result.data; //绑定表格数据
                    this.title=this.info.name+"的详细内容"
                }
            }).catch((error)=>{
            this.$message.error('查看失败，请重试');
        })
        axios.get("imageList?action=getImageInfo",{params:{patientId:this.patientId}})
            .then((resp)=>{
                let result=resp.data.data;
                this.splitImageList(result)
            }).catch((error)=>{
            this.$message.error('查看失败，请重试');
        })
        loading.close()
    },
    methods: {
        //拆分返回数据，并合成图片完整的url
        splitImageList(result){
            for(let i=0;i<result.length;i++){
                if(result[i].category=='economicSituation'){
                    this.economicSituation.push(this.ip+result[i].fileLocalUrl)
                }
                else if(result[i].category=='proofOfDiagnosis'){
                    this.proofOfDiagnosis.push(this.ip+result[i].fileLocalUrl)
                }
                else if(result[i].category=='paymentReceipt'){
                    this.paymentReceipt.push(this.ip+result[i].fileLocalUrl)
                }
                else if(result[i].category=='patientPhotos'){
                    this.patientPhotos.push(this.ip+result[i].fileLocalUrl)
                }
                else if(result[i].category=='memberOfFamily'){
                    this.memberOfFamily.push(this.ip+result[i].fileLocalUrl)
                }
            }
        },
        //返回上一页
        goBack() {
            window.history.go(-1);
        },
        //获取url中"?"符后的字串
        GetRequest() {
            var url = location.search;
            var theRequest = new Object();
            if (url.indexOf("?") != -1) {
                var str = url.substr(1);
                strs = str.split("&");
                for (var i = 0; i < strs.length; i++) {
                    theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
                }
            }
            return theRequest;
        },
        editForm(){
            window.location.href=`editForm.html?patientId=${this.patientId}`
        }
    }

}

var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')