var Main = {
    data() {
        //表单自定义验证
        var validateDiseaseOthers = (rule, value, callback) => {
            if (value === '' && this.ruleForm.disease === '其他') {
                callback(new Error('所患疾病不能为空'));
            }
            if (value.length > 20) {
                callback(new Error('最多输入20个字符'));
            }
            callback();
        };
        var validateOthersFundRaisingChannel = (rule, value, callback) => {
            if (value === '' && this.ruleForm.fundRaisingChannel === '其他') {
                callback(new Error('筹款渠道不能为空'));
            }
            if (value.length > 20) {
                callback(new Error('最多输入20个字符'));
            }
            callback();
        };
        var validateOthersRelation = (rule, value, callback) => {
            if (value === '' && this.ruleForm.relation === '其他') {
                callback(new Error('联系人不能为空'));
            }
            if (value.length > 20) {
                callback(new Error('最多输入20个字符'));
            }
            callback();
        };
        var validateAge = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('患者年龄不能为空'));
            }
            if (!/^[0-9]*[1-9][0-9]*$/.test(value)) {
                callback(new Error('请输入正整数'));
            } else {
                callback();
            }
        };
        var validateFamilySize = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('家庭成员数量不能为空'));
            }
            if (!/^[0-9]*[1-9][0-9]*$/.test(value)) {
                callback(new Error('请输入正整数'));
            } else {
                callback();
            }
        }
        var validateIsRead = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请确认是否已阅读'));
            } else if (value === '否') {

                callback(new Error('不同意申请规定无法提交'));
            } else {
                callback();
            }
        };
        var validateEconomicSituation = (rule, value, callback) => {
            if (this.getCategoryNum('economicSituation') == 0) {
                callback("图片图能为空")
            }
            callback();
        };
        var validateProofOfDiagnosis = (rule, value, callback) => {
            if (this.getCategoryNum('proofOfDiagnosis') == 0) {
                callback("图片图能为空")
            }
            callback();
        };
        var validatePaymentReceipt = (rule, value, callback) => {
            if (this.getCategoryNum('paymentReceipt') == 0) {
                callback("图片图能为空")
            }
            callback();
        };
        var validatePatientPhotos = (rule, value, callback) => {
            if (this.getCategoryNum('patientPhotos') == 0) {
                callback("图片图能为空")
            }
            callback();
        };
        var validateMemberOfFamily = (rule, value, callback) => {
            if (this.getCategoryNum('memberOfFamily') == 0) {
                callback("图片图能为空")
            }
            callback();
        };
        return {
            fileList: [],           //图片列表
            dialogImageUrl: '',     //图片预览路径
            dialogVisible: false,   //图片预览是否可见
            disabled: false,        //上传框内图标是否可见
            uploadDisabled: {       //上传组件是否可用
                economicSituation: false,
                proofOfDiagnosis: false,
                paymentReceipt: false,
                patientPhotos: false,
                memberOfFamily: false,
            },
            fileUploadPercent: {    //上传进度
                economicSituation: 0,
                proofOfDiagnosis: 0,
                paymentReceipt: 0,
                patientPhotos: 0,
                memberOfFamily: 0,
            },
            fileFlag: {              //上传进度是否可见
                economicSituation: false,
                proofOfDiagnosis: false,
                paymentReceipt: false,
                patientPhotos: false,
                memberOfFamily: false,
            },
            ruleForm: {
                fileListString: '',      //图片列表转化为字符串
                isRead: '',              //是否已阅读须知
                name: '',               //患者姓名
                sex: '',               //患者性别
                age: '',               //患者年龄
                job: '',               //患者职业
                disease: '',           //所患疾病
                diseaseOthers: '',     //其他所患疾病
                date: '',              //确诊日期
                hospital: '',          //目前所在医院
                doctor: '',            //主治医生
                doctorPhoneNum: '',    //主治医生联系电话
                background: '',        //家庭背景
                illnessExperience: '', //患病经历
                insurance: '',         //保险
                reimbursementRatios: '',//报销比例
                fundRaisingChannel: '',//筹款渠道
                othersFundRaisingChannel: '',//其他筹款渠道
                amountReceived: '',    //目前已获得资助的金额
                fixedAssets: '',       //固定资产
                familySize: '',        //家庭人口数量
                costGap: '',           //后续治疗费用缺口
                homeAddress: '',       //家庭住址
                contactName: '',       //联系人姓名
                helpLetter: '',        //求助信
                contactPhoneNum: '',   //联系人电话
                contactWechat: '',     //联系人微信
                relation: '',          //联系人与患者关系
                othersRelation: '',    //其他联系人与患者关系
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

            rules: {
                "economicSituation": [
                    {validator: validateEconomicSituation, trigger: 'blur'}
                ],
                "proofOfDiagnosis": [
                    {validator: validateProofOfDiagnosis, trigger: 'blur'}
                ],
                "paymentReceipt": [
                    {validator: validatePaymentReceipt, trigger: 'blur'}
                ],
                "patientPhotos": [
                    {validator: validatePatientPhotos, trigger: 'blur'}
                ],
                "memberOfFamily": [
                    {validator: validateMemberOfFamily, trigger: 'blur'}
                ],
                "diseaseOthers": [
                    {validator: validateDiseaseOthers, trigger: 'blur'},
                ],
                "othersFundRaisingChannel": [
                    {validator: validateOthersFundRaisingChannel, trigger: 'blur'}
                ],
                "othersRelation": [
                    {validator: validateOthersRelation, trigger: 'blur'}
                ],
                "isRead": [
                    {validator: validateIsRead, "required": true, trigger: 'change'}
                ],
                "name": [
                    {"required": true, message: '请输入患者姓名', trigger: 'blur'},
                    {min: 0, max: 10, message: '最多只能输入10个字符', trigger: 'blur'}
                ],
                "sex": [
                    {"required": true, message: '请输入患者性别', trigger: 'change'}
                ],
                "age": [
                    {validator: validateAge, "required": true, trigger: 'blur'}
                ],
                "job": [
                    {"required": true, message: '请输入患者职业', trigger: 'blur'},
                    {min: 0, max: 20, message: '最多只能输入20个字符', trigger: 'blur'}
                ],
                "disease": [
                    {"required": true, message: '请选择所患疾病', trigger: 'change'},
                    {min: 0, max: 20, message: '最多只能输入20个字符', trigger: 'blur'}
                ],
                "date": [
                    {type: 'string', "required": true, message: '请输入确诊日期', trigger: 'change'}
                ],
                "hospital": [
                    {"required": true, message: '请输入目前所在医院', trigger: 'blur'},
                    {min: 0, max: 40, message: '最多只能输入40个字符', trigger: 'blur'}
                ],
                "doctor": [
                    {"required": true, message: '请输入主治医生', trigger: 'blur'},
                    {min: 0, max: 20, message: '最多只能输入20个字符', trigger: 'blur'}
                ],
                "doctorPhoneNum": [
                    {"required": true, message: '请输入主治医生联系电话', trigger: 'blur'},
                    {min: 0, max: 20, message: '最多只能输入20个字符', trigger: 'blur'}
                ],
                "background": [
                    {"required": true, message: '请输入家庭背景', trigger: 'blur'},
                    {min: 0, max: 1000, message: '最多只能输入1000个字符', trigger: 'blur'}
                ],
                "illnessExperience": [
                    {"required": true, message: '请输入患病经历', trigger: 'blur'},
                    {min: 0, max: 1000, message: '最多只能输入1000个字符', trigger: 'blur'}
                ],
                "insurance": [
                    {"required": true, message: '请选择保险名称', trigger: 'change'},
                    {min: 0, max: 20, message: '最多只能输入20个字符', trigger: 'blur'}
                ],
                "reimbursementRatios": [
                    {"required": true, message: '请输入报销比例', trigger: 'blur'},
                    {min: 0, max: 20, message: '最多只能输入20个字符', trigger: 'blur'}
                ],
                "fundRaisingChannel": [
                    {"required": true, message: '请选择筹款渠道', trigger: 'change'},
                    {min: 0, max: 20, message: '最多只能输入20个字符', trigger: 'blur'}
                ],
                "amountReceived": [
                    {"required": true, message: '请输入目前已获得资助的金额', trigger: 'blur'},
                    {min: 0, max: 20, message: '最多只能输入20个字符', trigger: 'blur'}
                ],
                "fixedAssets": [
                    {"required": true, message: '请输入固定资产', trigger: 'blur'},
                    {min: 0, max: 1000, message: '最多只能输入1000个字符', trigger: 'blur'}
                ],
                "familySize": [
                    {validator: validateFamilySize, "required": true, trigger: 'blur'}
                ],
                "costGap": [
                    {"required": true, message: '后续治疗费用缺口', trigger: 'blur'},
                    {min: 0, max: 20, message: '最多只能输入20个字符', trigger: 'blur'}
                ],
                "homeAddress": [
                    {"required": true, message: '请输入家庭住址', trigger: 'blur'},
                    {min: 0, max: 100, message: '最多只能输入100个字符', trigger: 'blur'}
                ],
                "contactName": [
                    {"required": true, message: '请输入联系人姓名', trigger: 'blur'},
                    {min: 0, max: 10, message: '最多只能输入10个字符', trigger: 'blur'}
                ],
                "helpLetter": [
                    {"required": true, message: '请输入求助信', trigger: 'blur'},
                    {min: 0, max: 1000, message: '最多只能输入1000个字符', trigger: 'blur'}
                ],
                "contactPhoneNum": [
                    {"required": true, message: '请输入联系人电话', trigger: 'blur'},
                    {min: 0, max: 20, message: '最多只能输入20个字符', trigger: 'blur'}
                ],
                "contactWechat": [
                    {"required": true, message: '请输入联系人微信', trigger: 'blur'},
                    {min: 0, max: 20, message: '最多只能输入20个字符', trigger: 'blur'}
                ],
                "relation": [
                    {"required": true, message: '请选择联系人与患者关系', trigger: 'change'},
                    {min: 0, max: 20, message: '最多只能输入20个字符', trigger: 'blur'}
                ],
            },
        };
    },
    mounted() {
        //this.$refs['economicSituationUpload'].uploadFiles=$.cookie("economicSituation")
        if($.cookie("isRead")!='undefined'&&$.cookie("isRead")!=''&&$.cookie("isRead")!=null)
            this.ruleForm.isRead=$.cookie("isRead")
            this.ruleForm.name = $.cookie("name")
        if($.cookie("sex")!='undefined'&&$.cookie("sex")!=''&&$.cookie("sex")!=null)
            this.ruleForm.sex = $.cookie("sex")              //患者性别
            this.ruleForm.age = $.cookie("age")               //患者年龄
            this.ruleForm.job = $.cookie("job")               //患者职业
        if($.cookie("disease")!='undefined'&&$.cookie("disease")!=''&&$.cookie("disease")!=null)
            this.ruleForm.disease = $.cookie("disease")           //所患疾病
            this.ruleForm.diseaseOthers = $.cookie("diseaseOthers")     //其他所患疾病
        if($.cookie("date")!='undefined'&&$.cookie("date")!=''&&$.cookie("date")!=null)
            this.ruleForm.date = $.cookie("date")              //确诊日期
            this.ruleForm.hospital = $.cookie("hospital")          //目前所在医院
            this.ruleForm.doctor = $.cookie("doctor")            //主治医生
            this.ruleForm.doctorPhoneNum = $.cookie("doctorPhoneNum")    //主治医生联系电话
            this.ruleForm.background = $.cookie("background")        //家庭背景
            this.ruleForm.illnessExperience = $.cookie("illnessExperience") //患病经历
        if($.cookie("insurance")!='undefined'&&$.cookie("insurance")!=''&&$.cookie("insurance")!=null)
            this.ruleForm.insurance = $.cookie("insurance")        //保险
            this.ruleForm.reimbursementRatios = $.cookie("reimbursementRatios")//报销比例
        if($.cookie("fundRaisingChannel")!='undefined'&&$.cookie("fundRaisingChannel")!=''&&$.cookie("fundRaisingChannel")!=null)
            this.ruleForm.fundRaisingChannel = $.cookie("fundRaisingChannel")//筹款渠道
            this.ruleForm.othersFundRaisingChannel = $.cookie("othersFundRaisingChannel"),//其他筹款渠道
            this.ruleForm.amountReceived = $.cookie("amountReceived")    //目前已获得资助的金额
            this.ruleForm.fixedAssets = $.cookie("fixedAssets")       //固定资产
            this.ruleForm.familySize = $.cookie("familySize")        //家庭人口数量
            this.ruleForm.costGap = $.cookie("costGap")           //后续治疗费用缺口
            this.ruleForm.homeAddress = $.cookie("homeAddress")       //家庭住址
            this.ruleForm.contactName = $.cookie("contactName")       //联系人姓名
            this.ruleForm.helpLetter = $.cookie("helpLetter")        //求助信
            this.ruleForm.contactPhoneNum = $.cookie("contactPhoneNum")   //联系人电话
            this.ruleForm.contactWechat = $.cookie("contactWechat")     //联系人微信
        if($.cookie("relation")!='undefined'&&$.cookie("relation")!=''&&$.cookie("relation")!=null)
            this.ruleForm.relation = $.cookie("relation")          //联系人与患者关系
            this.ruleForm.othersRelation = $.cookie("othersRelation")    //其他联系人与患者关系
    },
    methods: {
        //input框设置cookie
        saveCookie(id) {
            let value = $('#' + id).val()
            if (value != "") {
                $.cookie(id, value, {expires: 7})
                console.log(id+"   "+$.cookie(id))
            }
        },
        //日期变化时设置cookie
        dateChange(value){
            $.cookie("date", value, {expires: 7})
        },
        //单选框变化时设置cookie
        isReadChange(value){
            $.cookie("isRead", value, {expires: 7})
        },
        sexChange(value){
            $.cookie("sex", value, {expires: 7})
        },
        //下拉框改变时清除提示信息以及输入框内信息,同时设置cookie
        diseaseChange(value) {
            this.$refs['ruleForm'].clearValidate(['diseaseOthers']);
            $.cookie("disease", value, {expires: 7})
            this.ruleForm.diseaseOthers = ''
        },
        fundRaisingChannelChange(value) {
            this.$refs['ruleForm'].clearValidate(['othersFundRaisingChannel']);
            $.cookie("fundRaisingChannel", value, {expires: 7})
            this.ruleForm.othersFundRaisingChannel = ''
        },
        relationChange(value) {
            this.$refs['ruleForm'].clearValidate(['othersRelation']);
            $.cookie("relation", value, {expires: 7})
            this.ruleForm.othersRelation = ''
        },
        insuranceChange(value){
            $.cookie("insurance", value, {expires: 7})
        },
        submitForm(formName) {
            const loading = this.$loading({
                lock: true,
                text: '提交中，请稍等',
                spinner: 'el-icon-loading',
                background: 'rgba(0, 0, 0, 0.7)'
            });

            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.clearOthers()
                    this.clearSpace()
                    this.fileListToString()
                    axios.post("patientList?action=save", Qs.stringify(this.ruleForm))
                        .then(res => {
                            loading.close();
                            window.location.href="submitSuccess.html"
                        }).catch((error) => {
                        this.$message.error("提交失败！请重试")
                        loading.close();
                        return false;
                    })
                } else {
                    this.$message.error("提交失败！请检查表单是否漏填、错填")
                    loading.close();
                    return false;
                }
            });
        },
        //将关键数据去除空格
        clearSpace() {
            this.ruleForm.name = this.ruleForm.name.trim()
            this.ruleForm.disease = this.ruleForm.disease.trim()
        },
        //将表单中的‘其他’去除
        clearOthers() {
            if (this.ruleForm.relation == '其他') {
                this.ruleForm.relation = this.ruleForm.othersRelation
            }
            if (this.ruleForm.fundRaisingChannel == '其他') {
                this.ruleForm.fundRaisingChannel = this.ruleForm.othersFundRaisingChannel
            }
            if (this.ruleForm.disease == '其他') {
                this.ruleForm.disease = this.ruleForm.diseaseOthers
            }
        },
        //经济状况文件上传时钩子
        economicSituationUploadProcess(event, file, fileList) {
            this.uploadDisabled.economicSituation = true	//有文件上传时禁用上传按钮
            this.fileFlag.economicSituation = true
            this.fileUploadPercent.economicSituation = parseInt(file.percentage)
        },
        //经济状况文件上传成功钩子
        economicSituationSuccess(response, file, fileList) {
            this.$refs['ruleForm'].clearValidate(['economicSituation']);
            //移除上传进度条
            let process = document.getElementById("dialogVisibleProcess")
            process.remove()
            this.fileFlag.economicSituation = true
            this.fileUploadPercent.economicSituation = 0
            this.fileList.push({            //添加上传数据记录
                fileName: response.data.fileName,
                fileLocalUrl: response.data.fileUrl,
                category: "economicSituation"
            })
            // console.log(this.$refs['economicSituationUpload'].uploadFiles)
            // $.cookie("economicSituation", this.$refs['economicSituationUpload'].uploadFiles, {expires: 7})
            // console.log($.cookie("economicSituation"))
            // console.log("经济状况"+$.cookie("economicSituation"))
            this.uploadDisabled.economicSituation = false	//解除上传按钮禁用
        },
        //经济状况文件上传失败钩子
        economicSituationError(response, file, fileList) {
            this.$message.error("图片上传失败，请重新上传")
            this.uploadDisabled.economicSituation = false	//解除上传按钮禁用
        },

        //诊断证明文件上传时钩子
        proofOfDiagnosisUploadProcess(event, file, fileList) {
            this.uploadDisabled.proofOfDiagnosis = true	//有文件上传时禁用上传按钮
            this.fileFlag.proofOfDiagnosis = true
            this.fileUploadPercent.proofOfDiagnosis = parseInt(file.percentage)
        },
        //诊断证明文件上传成功钩子
        proofOfDiagnosisSuccess(response, file, fileList) {
            this.$refs['ruleForm'].clearValidate(['proofOfDiagnosis']);
            //移除上传进度条
            let process = document.getElementById("dialogVisibleProcess")
            process.remove()
            this.fileFlag.proofOfDiagnosis = true
            this.fileUploadPercent.proofOfDiagnosis = 0
            this.fileList.push({            //添加上传数据记录
                fileName: response.data.fileName,
                fileLocalUrl: response.data.fileUrl,
                category: "proofOfDiagnosis"
            })
            console.log(this.fileList)
            this.uploadDisabled.proofOfDiagnosis = false	//解除上传按钮禁用
        },
        //诊断证明文件上传失败钩子
        proofOfDiagnosisError(response, file, fileList) {
            this.$message.error("图片上传失败，请重新上传")
            this.uploadDisabled.proofOfDiagnosis = false	//解除上传按钮禁用
        },

        //缴费单据文件上传时钩子
        paymentReceiptUploadProcess(event, file, fileList) {
            this.uploadDisabled.paymentReceipt = true	//有文件上传时禁用上传按钮
            this.fileFlag.paymentReceipt = true
            this.fileUploadPercent.paymentReceipt = parseInt(file.percentage)
        },
        //缴费单据文件上传成功钩子
        paymentReceiptSuccess(response, file, fileList) {
            this.$refs['ruleForm'].clearValidate(['paymentReceipt']);
            //移除上传进度条
            let process = document.getElementById("dialogVisibleProcess")
            process.remove()
            this.fileFlag.paymentReceipt = true
            this.fileUploadPercent.paymentReceipt = 0
            this.fileList.push({            //添加上传数据记录
                fileName: response.data.fileName,
                fileLocalUrl: response.data.fileUrl,
                category: "paymentReceipt"
            })
            console.log(this.fileList)
            this.uploadDisabled.paymentReceipt = false	//解除上传按钮禁用
        },
        //缴费单据文件上传失败钩子
        paymentReceiptError(response, file, fileList) {
            this.$message.error("图片上传失败，请重新上传")
            this.uploadDisabled.paymentReceipt = false	//解除上传按钮禁用
        },

        //患者宣传照片文件上传时钩子
        patientPhotosUploadProcess(event, file, fileList) {
            this.uploadDisabled.patientPhotos = true	//有文件上传时禁用上传按钮
            this.fileFlag.patientPhotos = true
            this.fileUploadPercent.patientPhotos = parseInt(file.percentage)
        },
        //患者宣传照片文件上传成功钩子
        patientPhotosSuccess(response, file, fileList) {
            this.$refs['ruleForm'].clearValidate(['patientPhotos']);
            //移除上传进度条
            let process = document.getElementById("dialogVisibleProcess")
            process.remove()
            this.fileFlag.patientPhotos = true
            this.fileUploadPercent.patientPhotos = 0
            this.fileList.push({            //添加上传数据记录
                fileName: response.data.fileName,
                fileLocalUrl: response.data.fileUrl,
                category: "patientPhotos"
            })
            console.log(this.fileList)
            this.uploadDisabled.patientPhotos = false	//解除上传按钮禁用
        },
        //患者宣传照片文件上传失败钩子
        patientPhotosError(response, file, fileList) {
            this.$message.error("图片上传失败，请重新上传")
            this.uploadDisabled.patientPhotos = false	//解除上传按钮禁用
        },

        //家庭成员信息文件上传时钩子
        memberOfFamilyUploadProcess(event, file, fileList) {
            this.uploadDisabled.memberOfFamily = true	//有文件上传时禁用上传按钮
            this.fileFlag.memberOfFamily = true
            this.fileUploadPercent.memberOfFamily = parseInt(file.percentage)
        },
        //家庭成员信息文件上传成功钩子
        memberOfFamilySuccess(response, file, fileList) {
            this.$refs['ruleForm'].clearValidate(['memberOfFamily']);
            //移除上传进度条
            let process = document.getElementById("dialogVisibleProcess")
            process.remove()
            this.fileFlag.memberOfFamily = true
            this.fileUploadPercent.memberOfFamily = 0
            this.fileList.push({            //添加上传数据记录
                fileName: response.data.fileName,
                fileLocalUrl: response.data.fileUrl,
                category: "memberOfFamily"
            })
            console.log(this.fileList)
            this.uploadDisabled.memberOfFamily = false	//解除上传按钮禁用
        },
        //家庭成员信息文件上传失败钩子
        memberOfFamilyError(response, file, fileList) {
            this.$message.error("图片上传失败，请重新上传")
            this.uploadDisabled.memberOfFamily = false	//解除上传按钮禁用
        },

        //删除图片钩子
        handleRemove(file) {
            //文件上传了才能删除
            if (!(typeof (file.response) === 'undefined')) {

                //删除上传列表文件中的照片数据
                let index2 = this.fileList.findIndex(fileItem => {
                    return fileItem.fileName === file.response.data.fileName
                })
                let oldFile = this.fileList.splice(index2, 1)
                // 实现缩略图模板时删除文件
                let fileList = this.$refs[oldFile[0].category + 'Upload'].uploadFiles;
                let index1 = fileList.findIndex(fileItem => {
                    return fileItem.uid === file.uid
                })
                fileList.splice(index1, 1)
                axios.get("file?action=delect", {
                    params: {
                        url: file.response.data.fileUrl
                    }
                })
                    .then((resp) => {
                        let result = resp.data; //获取控制器回传的数据
                        if (result.code === 0 && result.data != null) {
                        }
                    })
                    .catch((error) => {
                        this.$message.error("图片删除失败失败")
                    })
            }
        },

        //文件上传前钩子
        beforeUploadFile(file, fileList) {
            if (file.size / (1024 * 1024) > 10) {   // 限制文件大小
                this.$message.warning(`图片大小不能超过10M`)
                return false
            }
            let suffix = this.getFileType(file.name) //获取文件后缀名
            let suffixArray = ['jpg', 'png', 'jpeg']  //限制的文件类型，根据情况自己定义
            if (suffixArray.indexOf(suffix) === -1) {
                this.$message({
                    message: '文件格式错误',
                    type: 'error',
                    duration: 2000
                })
            }
            return suffixArray
        },
        //获取文件类型
        getFileType(name) {
            let startIndex = name.lastIndexOf('.')
            if (startIndex !== -1) {
                return name.slice(startIndex + 1).toLowerCase()
            } else {
                return ''
            }
        },

        //文件预览钩子
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },

        //文件下载钩子
        handleDownload(file) {
            let param = new URLSearchParams()
            param.append('fileName', file.response.data.fileName),
                axios.post("file?action=download",
                    param,
                    {responseType: 'blob'},
                )
                    .then((res) => {
                        console.log(res)
                        var blob = res.data;
                        // FileReader主要用于将文件内容读入内存
                        var reader = new FileReader();
                        reader.readAsDataURL(blob);
                        // onload当读取操作成功完成时调用
                        reader.onload = function (e) {
                            var a = document.createElement('a');
                            // 获取文件名fileName
                            a.download = file.response.data.fileName;
                            a.href = e.target.result;
                            document.body.appendChild(a);
                            a.click();
                            document.body.removeChild(a);
                        }
                    }).catch(error => {
                    console.log(error)
                    this.$message.error("下载失败")
                })
        },

        //获取fileList中指定类别文件的数量
        getCategoryNum(category) {
            let num = 0
            for (let i = 0; i < this.fileList.length; i++) {
                if (this.fileList[i].category == category) {
                    num++
                }
            }
            return num
        },

        //将fileList转化为字符串
        fileListToString() {
            let str = ""
            for (let i = 0; i < this.fileList.length; i++) {
                str += "category:" + this.fileList[i].category + ",fileLocalUrl:" + this.fileList[i].fileLocalUrl + ",fileName:" + this.fileList[i].fileName + "#"
            }
            this.ruleForm.fileListString = str
        }
    }
};
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')