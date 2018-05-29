package com.framework.constants;

import org.slf4j.impl.StaticMDCBinder;

/**
 * 系统常量
 * 
 * @author Chen Dang
 * @date 2016年3月29日 上午8:45:58
 * @version 1.0
 * @Description:
 */
public interface Constants {

	public final static String SYSTEM_NAME = "56物流平台";

	/**
	 * 异常信息统一头信息<br>
	 * 非常遗憾的通知您,程序发生了异常
	 */
	public static final String Exception_Head = "OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS :";
	/** 客户端语言 */
	public static final String USERLANGUAGE = "userLanguage";
	/** 客户端主题 */
	public static final String WEBTHEME = "webTheme";
	/** 当前用户 */
	public static final String CURRENT_USER = "CURRENT_USER";
	/** 在线用户数量 */
	public static final String ALLUSER_NUMBER = "ALLUSER_NUMBER";
	/** 登录用户数量 */
	public static final String USER_NUMBER = "USER_NUMBER";
	/** 上次请求地址 */
	public static final String PREREQUEST = "PREREQUEST";
	/** 上次请求时间 */
	public static final String PREREQUEST_TIME = "PREREQUEST_TIME";
	/** 非法请求次数 */
	public static final String MALICIOUS_REQUEST_TIMES = "MALICIOUS_REQUEST_TIMES";
	/** 缓存命名空间 */
	public static final String CACHE_NAMESPACE = "iBase4J:";

	/** 用户类型 */
	public static interface USER_TYPE {
		public static final String USER_TYPE_CLIENT = "010001";
		public static final String PLATFORM_USER = "010002";
	}

	/** 消息 */
	public static interface MESSAGE_CONTENT {
		public static final String VERTIFY_CODE_MSG = "尊敬的用户，您的验证码是：0914，10分钟内有效。";
	}

	/** 状态码 */
	public static interface STATUS_CODE {
		public static final String LOGIN_SUCCESS = "5606";
		public static final String LOGIN_FAIL = "5607";
		public static final String LOGIN_FAIL_ERROR_PWD = "560701";
		public static final String LOGIN_FAIL_USER_NOT_EXIST = "560702";
		public static final String USER_NOT_LOGIN = "560703";
		public static final String USER_LOGIN_ANOTHER_DEVICE = "560704";
		public static final String LOGIN_EXPIRE = "560705";
		public static final String SUCCESS = "5600";
		public static final String FAIL = "5700";
		public static final String LOGIN_ANOTHER_PLACE = "5701";
		public static final String ACCOUNT_MONEY_NOT_ENOUGH = "5702";
		public static final String PAYPWD_ERROR = "5703";
		public static final String IMAGE_UPLOAD_SUCCESS = "5610";
		public static final String IMAGE_UPLOAD_FAIL = "5611";
	}

	/** 日期格式 */
	public static interface DATE_FORMAT {
		public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd HH:mm";
		public static final String DATE_FORMATE_YYYYMMDD = "yyyy-MM-dd";
	}

	public static interface CHARACTER {
		public static final String STRENGTH_CHAR = "-";
	}

	public static interface STRING_ONE_ZERO {
		public static final String ONE = "1";
		public static final String ZERO = "0";
	}

	public static interface NUMBER {
		public static final int ONE = 1;
		public static final int ZERO = 0;
	}

	public static interface LOCATION_TYPE {
		public static final String PROVINCE = "province";
		public static final String CITY = "city";
		public static final String DISTRICT = "district";
	}

	public static interface PLATFORM {
		public static final String ANDROID = "020002";
		public static final String IOS = "020001";
	}

	/**
	 * 本地
	 */
	/*
	 * public static interface HOST { public static final String LOCALHOST =
	 * "http://192.168.1.91:82/newsimages/"; public static final String TEA =
	 * "http://192.168.1.91:82/tea/"; public static final String DOCUMENT =
	 * "http://192.168.1.91:82/document/"; public static final String FILE =
	 * "http://192.168.1.91:82/file/"; public static final String ICON =
	 * "http://192.168.1.91:82/icon/"; public static final String IMG =
	 * "http://192.168.1.91:82/img/"; public static final String STORE =
	 * "http://192.168.1.91:82/store/"; public static final String COMMON =
	 * "http://192.168.1.91:82/common/"; }
	 * 
	 * public static interface FILE_HOST { public static final String LOCALHOST =
	 * "F:\\upload\\newsimages\\"; public static final String TEA =
	 * "F:\\upload\\tea\\"; public static final String DOCUMENT =
	 * "F:\\upload\\document\\"; public static final String FILE =
	 * "F:\\upload\\file\\"; public static final String ICON = "F:\\upload\\icon\\";
	 * public static final String IMG = "F:\\upload\\img\\"; public static final
	 * String STORE = "F:\\upload\\store\\"; public static final String COMMON =
	 * "F:\\upload\\common\\"; }
	 */

	public static interface HOST {
		public static final String LOCALHOST = "http://www.yibuwangluo.cn:88/newsimages/";
		public static final String TEA = "http://www.yibuwangluo.cn:88/tea/";
		public static final String DOCUMENT = "http://www.yibuwangluo.cn:88/document/";
		public static final String FILE = "http://www.yibuwangluo.cn:88/file/";
		public static final String ICON = "http://www.yibuwangluo.cn:88/icon/";
		public static final String IMG = "http://www.yibuwangluo.cn:88/img/";
		public static final String STORE = "http://www.yibuwangluo.cn:88/store/";
		public static final String COMMON = "http://www.yibuwangluo.cn:88/common/";
	}

	public static interface FILE_HOST {
		public static final String LOCALHOST = "D:\\upload\\newsimages\\";
		public static final String TEA = "D:\\upload\\tea\\";
		public static final String DOCUMENT = "D:\\upload\\document\\";
		public static final String FILE = "D:\\upload\\file\\";
		public static final String ICON = "D:\\upload\\icon\\";
		public static final String IMG = "D:\\upload\\img\\";
		public static final String STORE = "D:\\upload\\store\\";
		public static final String COMMON = "D:\\upload\\common\\";
	}
	
	/*public static interface FILE_HOST {
		public static final String LOCALHOST = "F:\\upload\\newsimages\\";
		public static final String TEA = "F:\\upload\\tea\\";
		public static final String DOCUMENT = "F:\\upload\\document\\";
		public static final String FILE = "F:\\upload\\file\\";
		public static final String ICON = "F:\\upload\\icon\\";
		public static final String IMG = "F:\\upload\\img\\";
		public static final String STORE = "F:\\upload\\store\\";
		public static final String COMMON = "F:\\upload\\common\\";
	}*/

	/**
	 * centos服务器 public static interface HOST{ public static final String LOCALHOST
	 * = "http://app.tongjichaye.com:88/tea/"; public static final String TEA =
	 * "http://app.tongjichaye.com:88/tea/"; public static final String DOCUMENT =
	 * "http://app.tongjichaye.com:88/document/"; public static final String FILE =
	 * "http://app.tongjichaye.com:88/file/"; public static final String ICON =
	 * "http://app.tongjichaye.com:88/icon/"; public static final String IMG =
	 * "http://app.tongjichaye.com:88/img/"; public static final String STORE =
	 * "http://app.tongjichaye.com:88/store/"; public static final String COMMON =
	 * "http://app.tongjichaye.com:88/common/"; }
	 * 
	 * public static interface FILE_HOST{ public static final String LOCALHOST =
	 * "/home/data/images/tea/"; public static final String TEA =
	 * "/home/data/images/tea/"; public static final String DOCUMENT =
	 * "/home/data/images/document/"; public static final String FILE =
	 * "/home/data/images/file/"; public static final String ICON =
	 * "/home/data/images/icon/"; public static final String IMG =
	 * "/home/data/images/img/"; public static final String STORE =
	 * "/home/data/images/store/"; public static final String COMMON =
	 * "/home/data/images/common/"; }
	 */

	public static interface FEEDBACK_STATUS {
		public static final String HANDLE = "030002";
		public static final String STAY_HANDLE = "030001";
	}

	public static interface CAROUSEL_TYPE {
		// 店招
		public static final String STORY_ZHAO = "040001";
		// PC版轮播图
		public static final String PC_INDEX = "040002";
		// 微信轮播图
		public static final String WX_INDEX = "040003";
		// 小程序轮播图
		public static final String XCX_INDEX = "040004";
		// 以租代购横幅
		public static final String CAR_LEASE_IMAGE = "040005";
		// 二手车横幅
		public static final String CAR_SECONDHAND_IMAGE = "040006";
		// 平行进口横幅
		public static final String CAR_IMPORT_IMAGE = "040007";
		
		//pc以租代购横幅
		public static final String PC_CAR_LEASE_IMAGE = "040008";
		//pc二手车横幅
		public static final String PC_CAR_SECONDHAND_IMAGE = "040009";
		//pc平行进口横幅
		public static final String PC_CAR_IMPORT_IMAGE = "040010";
	}
}
