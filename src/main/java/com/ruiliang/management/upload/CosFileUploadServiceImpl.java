package com.ruiliang.management.upload;

import java.io.InputStream;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;


@Service("cosFileUploadService")
public class CosFileUploadServiceImpl implements FileUploadService {

	@Value("${tencloud.cos.secretid}")
	private String apiSecretId;
	@Value("${tencloud.cos.secretkey}")
	private String apiSecretKey;
	@Value("${tencloud.cos.region}")
	private String cosRegion;
	@Value("${tencloud.cols.bucketname}")
	private String bucketName;

	

	@Override
	public String copy(InputStream input, String contentType, String srcFileName, int nfsType,String prefix) throws Exception {
		// 建立cos连接
		COSCredentials cred = new BasicCOSCredentials(apiSecretId, apiSecretKey);
		ClientConfig clientConfig = new ClientConfig(new Region(cosRegion));
		COSClient cosClient = new COSClient(cred, clientConfig);
		String fileUrl = "";

		String tempname = FileUtil.htmlEncode(NFSPathUtil.getPath(nfsType));
		if(tempname.contains("apk") && StringUtils.isNoneBlank(prefix)){
			tempname += "/ruiliangapp"+"_"+prefix+"."+contentType;
		}else{
			tempname += "/" + new Date().getTime();
			// 随机数
			char[] randoms = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
					'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
			Random rand = new Random();
			tempname += randoms[rand.nextInt(randoms.length)] + randoms[rand.nextInt(randoms.length)]
					+ randoms[rand.nextInt(randoms.length)] + randoms[rand.nextInt(randoms.length)];

			tempname += "." + contentType;

		}
		
		
		
		TransferManager transferManager = null;
		 ExecutorService threadPool = Executors.newFixedThreadPool(32);
		try {
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(input.available());
//			objectMetadata.setContentType("image/jpeg");
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, tempname, input, objectMetadata);

			transferManager = new TransferManager(cosClient, threadPool);

			Upload upload = transferManager.upload(putObjectRequest);
			UploadResult uploadResult = upload.waitForUploadResult();

			fileUrl = NFSConstants.NFS_SERVER_URL + tempname;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("上传失败: " + e.toString());
		} finally {
			if (transferManager != null) {
				transferManager.shutdownNow();
			}
			if(cosClient != null) {
				cosClient.shutdown();
			}

		}

		return fileUrl;
	}

	@Override
	public String copy2Chat(InputStream input, String contentType, String srcFileName) throws Exception {
		int nfsType = NFSPathUtil.getNFSType(contentType);

		return copy(input, contentType, srcFileName, nfsType,"");
	}

	@Override
	public String copy2Avatar(InputStream input, String contentType, String srcFileName) throws Exception {
		int nfsType = NFSConstants.NFS_TYPE_USER_AVATAR;
		return copy(input, contentType, srcFileName, nfsType,"");
	}

	@Override
	public String copy2Apk(InputStream input, String contentType,
			String srcFileName,String prefix) throws Exception {
		int nfsType = NFSConstants.NFS_TYPE_APK;
		return copy(input, contentType, srcFileName, nfsType,prefix);
	}

}
