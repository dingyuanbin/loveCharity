package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;

public class FileUpLoadUtil {
	//	初始化用户身份信息(secretid, secretkey)
	public static COSCredentials cred = new BasicCOSCredentials("AKIDcMq9E4LjcKtFUYPyufD2f2FKIME0Ddi1","MTKQK2VigSyUxFIVT0GH2atrFAwUmpfM" );
	//	设回bucket的区域，COS地域的简称请参照https://cloud.tencent.com/document/product/436/62.24
	//	区域
	static String region = "ap-guangzhou";
	// clientConfig中包含了设置region,https(默认 http)，超时，代理等set方法
	static ClientConfig clientConfig = new ClientConfig(new Region (region));
	static COSClient cosclient = new COSClient(cred,clientConfig);
	// bucket的命名规则为name-appid，此处填写的存储桶名将必须为此格式
	static String bucketName = "public-benefit-1305646967";	
	//appId
	static String appId="1305646967";
	
	/**
	 * 	上传文件
	 *	@param file
	 **/
	public static void upFile(InputStream inputStream,String key) {
		//指定要上传到的存储桶
		ObjectMetadata metadata = new ObjectMetadata();
		PutObjectRequest request = new PutObjectRequest(bucketName,key,inputStream,metadata);
		cosclient.putObject(request) ;
		cosclient.shutdown();
	}
	/**	
	*	下载文件
	**/
	public static void downFile(File file,String key) {
		//指定要下载到的本地路径
		File downFile = new File("D://"+key );
		GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName,key);
		cosclient.getObject(getObjectRequest, downFile);
		cosclient.shutdown();
	}
	/**
	*	删除文件
	**/
	public static void delFile(String bucketName,String key) {
	cosclient.deleteObject(bucketName,key);
	cosclient.shutdown();
	}

	/**
     * @author dyb
     * @date 2020-4-20
     * @param key  key前面为路径，后面为文件名
     * @param filepath  上传文件的路径
     * @return String
     * @Des:  将本地文件上传到COS
     */
	public String SimpleUploadFileFromLocal(String key ,String filepath) {
    	//  生成cos客户端
    	COSClient cosClient = new COSClient(cred, clientConfig);
        File localFile = new File(filepath);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia)
        putObjectRequest.setStorageClass(StorageClass.Standard);
        try {
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            // 关闭客户端
            cosClient.shutdown();
            return "https://"+bucketName+".cos."+region+".myqcloud.com/"+key;
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        
        // 关闭客户端
        cosClient.shutdown();
        return null;
    }

	// 从输入流进行读取并上传到COS
    public static String SimpleUploadFileFromStream(String key,InputStream input) throws IOException {
        //  生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        // bucket名需包含appid
        ObjectMetadata objectMetadata = new ObjectMetadata();
        // 从输入流上传必须制定content length, 否则http客户端可能会缓存所有数据，存在内存OOM的情况
        objectMetadata.setContentLength(Integer.valueOf(input.available()).longValue());
        // 默认下载时根据cos路径key的后缀返回响应的contenttype, 上传时设置contenttype会覆盖默认值
        objectMetadata.setContentType("image/jpeg");
        
        PutObjectRequest putObjectRequest =
                new PutObjectRequest(bucketName, key, input, objectMetadata);
        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia)
        putObjectRequest.setStorageClass(StorageClass.Standard);
        try {
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            // 关闭客户端
            cosClient.shutdown();
            return "https://"+bucketName+".cos."+region+".myqcloud.com/"+key;
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        
        // 关闭客户端
        cosClient.shutdown();
        return null;
    }

}
