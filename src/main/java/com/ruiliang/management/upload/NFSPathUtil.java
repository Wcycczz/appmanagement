package com.ruiliang.management.upload;

import java.util.Date;



public class NFSPathUtil {

	public static String getPath(int NFSType) {
		StringBuilder sb = new StringBuilder();

		if (NFSConstants.NFS_TYPE_CHAT_IMAGE == NFSType || NFSConstants.NFS_TYPE_CHAT_VOICE == NFSType
				|| NFSConstants.NFS_TYPE_CHAT_MUSIC == NFSType || NFSConstants.NFS_TYPE_CHAT_VEDIO == NFSType) {
			sb.append(NFSConstants.NFS_CHAT_ROOT_PATH);
		} else if (NFSConstants.NFS_TYPE_USER_AVATAR == NFSType) {
			sb.append(NFSConstants.NFS_USER_ROOT_PATH);
		}

		sb.append("/");
		String ym = CalendarUtil.format(new Date(), "yyyyMM");

		switch (NFSType) {
		case NFSConstants.NFS_TYPE_CHAT_IMAGE:
			sb.append("image");
			sb.append("/").append(ym);
			break;
		case NFSConstants.NFS_TYPE_CHAT_VOICE:
			sb.append("voice");
			sb.append("/").append(ym);
			break;
		case NFSConstants.NFS_TYPE_CHAT_VEDIO:
			sb.append("video");
			sb.append("/").append(ym);
			break;
		case NFSConstants.NFS_TYPE_CHAT_MUSIC:
			sb.append("music");
			sb.append("/").append(ym);
			break;
		case NFSConstants.NFS_TYPE_USER_AVATAR:
			sb.append("avatar");
			sb.append("/").append(ym);
			break;
		case NFSConstants.NFS_TYPE_APK:
			sb.append("apk");
			break;
		default:
			sb.append("unknow");
			break;
		}

		

		return sb.toString();
	}

	public static int getNFSType(String fileType) {
		if (NFSConstants.NFS_TYPE_IMAGE.contains(fileType)) {
			return NFSConstants.NFS_TYPE_CHAT_IMAGE;
		}
		if (NFSConstants.NFS_TYPE_VOICE.contains(fileType)) {
			return NFSConstants.NFS_TYPE_CHAT_VOICE;
		}
		if (NFSConstants.NFS_TYPE_VIDEO.contains(fileType)) {
			return NFSConstants.NFS_TYPE_CHAT_VEDIO;
		}
		return NFSConstants.NFS_TYPE_CHAT_IMAGE;
	}
}
