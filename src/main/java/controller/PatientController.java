package controller;

import com.alibaba.fastjson.JSON;
import dto.PatientQueryDto;
import entity.ImageInfo;
import entity.Patient;
import service.ImageInfoService;
import service.PatientService;
import service.impl.ImageInfoServiceImpl;
import service.impl.PatientServiceImpl;
import utils.PageUtil;
import utils.StringUtil;
import vo.ResultVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/patientList")
public class PatientController  extends BaseController {
    // 注入service
    PatientService patientService = new PatientServiceImpl();
    ImageInfoService imageInfoService=new ImageInfoServiceImpl();
    /**
     * 获取患者信息
     * @param request
     * @param response
     * @throws Exception
     */
    private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 接收请求
        Integer stateValue = StringUtil.toInt(StringUtil.toNull(request.getParameter("stateValue")));
        Integer patientId = StringUtil.toInt(StringUtil.toNull(request.getParameter("patientId")));
        String patientName = StringUtil.toNull(request.getParameter("patientName"));
        String disease = StringUtil.toNull(request.getParameter("disease"));
        String submitDate = StringUtil.toNull(request.getParameter("submitDate"));
        Integer currentPage = StringUtil.toInt(StringUtil.toNull(request.getParameter("currentPage")));
        Integer pageSize = StringUtil.toInt(StringUtil.toNull(request.getParameter("pageSize")));
        // 封装对象
        PatientQueryDto patientQueryDto=new PatientQueryDto();

        patientQueryDto.setProcessState(stateValue);
        patientQueryDto.setPatientId(patientId);
        patientQueryDto.setPatientName(patientName);
        patientQueryDto.setDisease(disease);
        patientQueryDto.setSubmitDate(submitDate);
        patientQueryDto.setPageSize(pageSize);
        patientQueryDto.setCurrentPage(currentPage);
        //处理结果
        PageUtil pageUtil;
        ResultVo resultVo;
        pageUtil=patientService.pageList(patientQueryDto);
        resultVo=new ResultVo<PageUtil>(pageUtil);
        String json = JSON.toJSONString(resultVo);
        PrintWriter out = response.getWriter();
        out.print(json);
    }
    /**
     * 改变维护状态
     * @param request
     * @param response
     * @throws Exception
     */
    private void changeState(HttpServletRequest request, HttpServletResponse response) throws Exception{

        // 接收请求
        Integer patientId = StringUtil.toInt(StringUtil.toNull(request.getParameter("patientId")));
        Integer stateValue = StringUtil.toInt(StringUtil.toNull(request.getParameter("stateValue")));
        //改变状态
        if(stateValue==3){
            stateValue=1;
        }
        else {
            stateValue++;
        }
        // 封装对象
        Patient patient=new Patient();
        patient.setPatientId(patientId);
        patient.setProcessingStatus(stateValue);

        //处理结果
        patientService.edit(patient);
    }


    /**
     * 删除车辆信息
     * @param request
     * @param response
     * @throws Exception
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 接收请求
        int patientId = StringUtil.toInt(StringUtil.toNull(request.getParameter("patientId")));
        List<ImageInfo> imageInfos= imageInfoService.findById(patientId);
        /*
         * 文件保存在tomcat/upload目录下
         * 从保存位置删除文件
         */
        String tomcat=System.getProperty("catalina.home");
        for(int i=0;i<imageInfos.size();i++){
            String path=tomcat+ File.separator+"upload"+File.separator+imageInfos.get(i).getFileLocalUrl();
            File file=new File(path);
            //判断文件是否存在
            if(file.exists()==true) {
                file.delete();
            }
        }
        // 处理结果
        patientService.deleteById(patientId);
    }

    /**
     * 根据id获取申请表全部基本信息
     * @param request
     * @param response
     * @throws Exception
     */
    private void getPatientInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 接收请求
        int patientId = StringUtil.toInt(StringUtil.toNull(request.getParameter("patientId")));
        // 处理结果
        Patient patient=patientService.findById(patientId);
        // 处理结果
        PageUtil pageUtil;
        ResultVo resultVo;
        resultVo = new ResultVo<Patient>(patient);
        String json = JSON.toJSONString(resultVo);
        PrintWriter out = response.getWriter();
        out.print(json);

    }

    /**
     * 保存患者信息
     * @param request
     * @param response
     * @throws Exception
     */
    private void save(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //获取当前时间并格式化
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String submitDate=simpleDateFormat.format(new Date());
        // 接收请求
        String name = StringUtil.toNull(request.getParameter("name"));
        String sex = StringUtil.toNull(request.getParameter("sex"));
        Integer age = StringUtil.toInt(StringUtil.toNull(request.getParameter("age")));
        String job = StringUtil.toNull(request.getParameter("job"));
        String disease = StringUtil.toNull(request.getParameter("disease"));
        String date = StringUtil.toNull(request.getParameter("date"));
        String hospital = StringUtil.toNull(request.getParameter("hospital"));
        String doctor = StringUtil.toNull(request.getParameter("doctor"));
        String doctorPhoneNum = StringUtil.toNull(request.getParameter("doctorPhoneNum"));
        String background = StringUtil.toNull(request.getParameter("background"));
        String illnessExperience = StringUtil.toNull(request.getParameter("illnessExperience"));
        String insurance = StringUtil.toNull(request.getParameter("insurance"));
        String reimbursementRatios = StringUtil.toNull(request.getParameter("reimbursementRatios"));
        String fundRaisingChannel = StringUtil.toNull(request.getParameter("fundRaisingChannel"));
        String amountReceived = StringUtil.toNull(request.getParameter("amountReceived"));
        String fixedAssets = StringUtil.toNull(request.getParameter("fixedAssets"));
        Integer familySize = StringUtil.toInt(StringUtil.toNull(request.getParameter("familySize")));
        String costGap = StringUtil.toNull(request.getParameter("costGap"));
        String homeAddress = StringUtil.toNull(request.getParameter("homeAddress"));
        String contactName = StringUtil.toNull(request.getParameter("contactName"));
        String helpLetter = StringUtil.toNull(request.getParameter("helpLetter"));
        String contactPhoneNum = StringUtil.toNull(request.getParameter("contactPhoneNum"));
        String contactWechat = StringUtil.toNull(request.getParameter("contactWechat"));
        String relation = StringUtil.toNull(request.getParameter("relation"));

        // 封装对象
        Patient patient = new Patient();
        patient.setName(name);
        patient.setDisease(disease);
        patient.setSubmitDate(submitDate);
        patient.setSex(sex);
        patient.setAge(age);
        patient.setJob(job);
        patient.setDate(date);
        patient.setHospital(hospital);
        patient.setDoctor(doctor);
        patient.setDoctorPhoneNum(doctorPhoneNum);
        patient.setBackground(background);
        patient.setIllnessExperience(illnessExperience);
        patient.setInsurance(insurance);
        patient.setReimbursementRatios(reimbursementRatios);
        patient.setFundRaisingChannel(fundRaisingChannel);
        patient.setAmountReceived(amountReceived);
        patient.setFixedAssets(fixedAssets);
        patient.setFamilySize(familySize);
        patient.setCostGap(costGap);
        patient.setHomeAddress(homeAddress);
        patient.setContactName(contactName);
        patient.setHelpLetter(helpLetter);
        patient.setContactPhoneNum(contactPhoneNum);
        patient.setContactWechat(contactWechat);
        patient.setRelation(relation);

        // 处理结果
        int key=patientService.save(patient);

        String fileListString = StringUtil.toNull(request.getParameter("fileListString"));

        List<ImageInfo> imageInfos= imageInfoService.getImageInfoList(fileListString,key);
        imageInfoService.save(imageInfos);  //保存照片信息
    }

    /**
     * 修改患者信息
     * @param request
     * @param response
     * @throws Exception
     */
    private void editInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 接收请求
        Integer patientId=StringUtil.toInt(StringUtil.toNull(request.getParameter("patientId")));
        String name = StringUtil.toNull(request.getParameter("name"));
        String sex = StringUtil.toNull(request.getParameter("sex"));
        Integer age = StringUtil.toInt(StringUtil.toNull(request.getParameter("age")));
        String job = StringUtil.toNull(request.getParameter("job"));
        String disease = StringUtil.toNull(request.getParameter("disease"));
        String date = StringUtil.toNull(request.getParameter("date"));
        String hospital = StringUtil.toNull(request.getParameter("hospital"));
        String doctor = StringUtil.toNull(request.getParameter("doctor"));
        String doctorPhoneNum = StringUtil.toNull(request.getParameter("doctorPhoneNum"));
        String background = StringUtil.toNull(request.getParameter("background"));
        String illnessExperience = StringUtil.toNull(request.getParameter("illnessExperience"));
        String insurance = StringUtil.toNull(request.getParameter("insurance"));
        String reimbursementRatios = StringUtil.toNull(request.getParameter("reimbursementRatios"));
        String fundRaisingChannel = StringUtil.toNull(request.getParameter("fundRaisingChannel"));
        String amountReceived = StringUtil.toNull(request.getParameter("amountReceived"));
        String fixedAssets = StringUtil.toNull(request.getParameter("fixedAssets"));
        Integer familySize = StringUtil.toInt(StringUtil.toNull(request.getParameter("familySize")));
        String costGap = StringUtil.toNull(request.getParameter("costGap"));
        String homeAddress = StringUtil.toNull(request.getParameter("homeAddress"));
        String contactName = StringUtil.toNull(request.getParameter("contactName"));
        String helpLetter = StringUtil.toNull(request.getParameter("helpLetter"));
        String contactPhoneNum = StringUtil.toNull(request.getParameter("contactPhoneNum"));
        String contactWechat = StringUtil.toNull(request.getParameter("contactWechat"));
        String relation = StringUtil.toNull(request.getParameter("relation"));

        // 封装对象
        Patient patient = new Patient();
        patient.setPatientId(patientId);
        patient.setName(name);
        patient.setDisease(disease);
        patient.setSex(sex);
        patient.setAge(age);
        patient.setJob(job);
        patient.setDate(date);
        patient.setHospital(hospital);
        patient.setDoctor(doctor);
        patient.setDoctorPhoneNum(doctorPhoneNum);
        patient.setBackground(background);
        patient.setIllnessExperience(illnessExperience);
        patient.setInsurance(insurance);
        patient.setReimbursementRatios(reimbursementRatios);
        patient.setFundRaisingChannel(fundRaisingChannel);
        patient.setAmountReceived(amountReceived);
        patient.setFixedAssets(fixedAssets);
        patient.setFamilySize(familySize);
        patient.setCostGap(costGap);
        patient.setHomeAddress(homeAddress);
        patient.setContactName(contactName);
        patient.setHelpLetter(helpLetter);
        patient.setContactPhoneNum(contactPhoneNum);
        patient.setContactWechat(contactWechat);
        patient.setRelation(relation);

        // 处理结果
        patientService.editAllInfo(patient);

        String fileListString = StringUtil.toNull(request.getParameter("fileListString"));

        List<ImageInfo> imageInfos= imageInfoService.getImageInfoList(fileListString,patientId);
        imageInfoService.save(imageInfos);  //保存照片信息
    }
}
