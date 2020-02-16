package com.framework.constants;

/**
 * 系统常量
 * 
 * @author Chen Dang
 * @date 2016年3月29日 上午8:45:58traceSource
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
		public static final String USER_TYPE_CLIENT = "1001";
		public static final String PLATFORM_USER = "1002";
	}

	/** 消息 */
	public static interface MESSAGE_CONTENT {
		public static final String VERTIFY_CODE_MSG = "尊敬的用户，您的验证码是：0914，10分钟内有效。";
	}

	/** 状态码 */
	public static interface STATUS_CODE {
		public static final String SUCCESS = "2000";
		public static final String FAIL = "4000";
	}

	public static interface LOGIN_STATUS_CODE{
		public static final String LOGIN_SUCCESS = "2001";
		public static final String LOGIN_FAIL = "4001";
		public static final String LOGIN_FAIL_ERROR_PWD = "4002";
		public static final String LOGIN_FAIL_USER_NOT_EXIST= "4003";
		public static final String USER_NOT_LOGIN = "4004";
		public static final String USER_LOGIN_ANOTHER_DEVICE = "4005";
		public static final String LOGIN_EXPIRE = "4006";
		public static final String LOGIN_ANOTHER_PLACE = "4007";
		public static final String PAYPWD_ERROR = "4008";
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
	/**
	public static interface HOST {
		public static final String LOCALHOST = "http://192.168.1.91:82/newsimages/";
		public static final String TEA = "http://192.168.1.91:82/tea/";
		public static final String DOCUMENT = "http://192.168.1.91:82/document/";
		public static final String FILE = "http://192.168.1.91:82/file/";
		public static final String ICON = "http://192.168.1.91:82/icon/";
		public static final String IMG = "http://192.168.1.91:82/img/";
		public static final String STORE = "http://192.168.1.91:82/store/";
		public static final String COMMON = "http://192.168.1.91:82/common/";
	}

	public static interface FILE_HOST {
		public static final String LOCALHOST = "F:\\upload\\newsimages\\";
		public static final String TEA = "F:\\upload\\tea\\";
		public static final String DOCUMENT = "F:\\upload\\document\\";
		public static final String FILE = "F:\\upload\\file\\";
		public static final String ICON = "F:\\upload\\icon\\";
		public static final String IMG = "F:\\upload\\img\\";
		public static final String STORE = "F:\\upload\\store\\";
		public static final String COMMON = "F:\\upload\\common\\";
	}
	
	public static interface HTTP_HOST {
		public static final String LOCALHOST = "http://192.168.1.91:82/newsimages/";
		public static final String TEA = "http://192.168.1.91:82/tea/";
		public static final String DOCUMENT = "http://192.168.1.91:82/document/";
		public static final String FILE = "http://192.168.1.91:82/file/";
		public static final String ICON = "http://192.168.1.91:82/icon/";
		public static final String IMG = "http://192.168.1.91:82/img/";
		public static final String STORE = "http://192.168.1.91:82/store/";
		public static final String COMMON = "http://192.168.1.91:82/common/";
	}*/

	//测试服务器地址
	/**
	public static interface HOST {
		public static final String DOCUMENT = "http://www.yibuwangluo.cn:88/document/";
		public static final String IMG = "http://www.yibuwangluo.cn:88/img/";
		public static final String MAP_URL = "http://www.yibuwangluo.cn/framework/map.html";
	}
	
	public static interface HTTPS_HOST {
		public static final String DOCUMENT = "https://www.yibuwangluo.cn/document/";
	}

	public static interface HTTP_HOST {
		public static final String DOCUMENT = "http://www.yibuwangluo.cn:88/document/";
	}
	
	public static interface HTTPS_FILE_HOST {
		public static final String DOCUMENT = "D:\\apache-tomcat-7.0.69-ht\\webapps\\ROOT\\document\\";
	}

	public static interface FILE_HOST {
		public static final String DOCUMENT = "D:\\upload\\document\\";
		public static final String IMG = "D:\\upload\\img\\";
	}*/

	public static interface FILE_HOST {
		public static final String DOCUMENT = "/var/www/html/file/";
		public static final String IMG = "/var/www/html/img/";
		public static final String ICON = "/var/www/html/icon/";
	}

	//Linux服务器地址
	public static interface HOST {
		public static final String DOCUMENT = "http://47.105.184.157/file/document/";
		public static final String IMG = "http://47.105.184.157/img/";
		public static final String ICON = "http://47.105.184.157/icon/";
	}
	
	public static interface HTTPS_HOST {
		public static final String DOCUMENT = "http://47.105.184.157/file/document/";
		public static final String STORY = "http://47.105.184.157/file/story/";
		public static final String NEWS = "http://47.105.184.157/file/news/";
		public static final String MALL_PRODUCT = "http://47.105.184.157/file/mallProduct/";
		public static final String FINANCE = "http://47.105.184.157/file/finance/";
		public static final String TRACE_SOURCE = "http://47.105.184.157/file/tracesource/";
	}

	public static interface HTTP_HOST {
		public static final String DOCUMENT = "http://47.105.184.157/file/document/";
	}
	
	public static interface HTTPS_FILE_HOST {
		public static final String DOCUMENT = "/var/www/html/file/document/";
		public static final String STORY = "/var/www/html/file/story/";
		public static final String NEWS = "/var/www/html/file/news/";
		public static final String MALL_PRODUCT = "/var/www/html/file/mallProduct/";
		public static final String FINANCE = "/var/www/html/file/finance/";
		public static final String TRACE_SOURCE = "/var/www/html/file/tracesource/";
	}


	/**
	 * 反馈状态
	 */
	public static interface FEEDBACK_STATUS {
		public static final String HANDLE = "050002";
		public static final String STAY_HANDLE = "050001";
	}

	public static interface CAROUSEL_TYPE {
		//首页轮播图
		public static final String INDEX_CAROUSEL = "040001";
		public static final String DEFAULT_ICON = "040002";
	}

	// 车型
	public static interface CAR_TYPE {
		public static final String SMALL = "060001";
		public static final String MIDDLE = "060002";
		public static final String SUV = "060003";
		public static final String MPV = "060004";
		public static final String PAOCHE = "060005";
	}

	// 品类
	public static interface CAR_CLASS {
		public static final String MEIGUI = "070001";
		public static final String JIAGUI = "070002";
		public static final String MOGUI = "070003";
		public static final String ZHONGDONG = "070004";
		public static final String OUGUI = "070005";
	}

	// 问答类型
	public static interface QUESTION_ANSWER_TYPE {
		public static final String GOUCHE_MARK = "080001";
		public static final String FEIYONG = "080002";
		public static final String SHOUHOU = "080003";
		public static final String YONGCHE = "080004";
		public static final String BAOXIAN = "080005";
		public static final String QITA = "080006";
	}

	// 资讯类型
	public static interface QUESTION_TYPE {
		public static final String PC = "090001";
		public static final String XCX = "090002";
		public static final String H5 = "090003";
	}

	// 电话
	public static interface TEL_TYPE {
		public static final String KEFU = "100001";
	}

	// 汽车销售类型
	public static interface CAR_SALE_TYPE {
		public static final String LEASE = "110001";
		public static final String SECONDHAND = "110002";
		public static final String IMPORT = "110003";
	}
	
	//资讯类型
	public static interface NEWS_TYPE {
		//平台通知
		public static final String PLAT_NOTIFICATION = "030001";
		//汽车资讯
		public static final String CAR_NEWS = "030002";
		//活动专题
		public static final String ACTIVITY = "030003";
		//媒体报道
		public static final String MEDIA = "030004";
	}
	
	//文档类型
	public static interface DOCUMENT_ABOUTUS{
		public static final String ABOUTUS = "120001";
		public static final String HELP_CENTER = "120002";
		public static final String USER_ABOUT = "120003";
		public static final String EXPRESS = "120004";
	}

	public static interface APP_VERSION{
		public static final String ANDROID = "140001";
		public static final String IOS = "140002";
	}

	/**
	 * 用户审核状态
	 */
	public static interface MEMBER_STATUS{
//		未认证、待审核、审核通过、审核失败
		public static final String NOT_REVIEW = "150001";
		public static final String STAY_REVIRE = "150002";
		public static final String REVIEW_PASS = "150003";
		public static final String REVIEW_FAIL = "150004";
	}

	/**
	 * 用户类型/用户等级
	 */
	public static interface MEMBER_GRADE{
		//个人
		public static final String PERSON = "160001";
		//企业
		public static final String COMPANY = "160002";
	}

	/**
	 * 短信类型
	 */
	public static interface SHORT_MSG_TYPE{
		public static final String REGISTER = "170001";
		public static final String FORGET_PASSWORD = "170002";
	}

	/**
	 * 常量设置
	 */
	public static interface STATIC_PARAMS{
		public static final String SHOWMSG_COUNT = "180001";
	}

	/**
	 * 积分操作类型
	 */
	public static interface OPERATE_TYPE{
		public static final String EXCHANGE_PRODUCT = "190001";
		public static final String REGISTER = "190002";
	}

	/**
	 * 兑换状态
	 */
	public static interface EXCHANGE_STATUS{
		public static final String STAY_REVIEW = "200001";//待审核
		public static final String HANDLED = "200002";//已处理
	}

	/**
	 * 商品购买类型
	 */
	public static interface BUY_TYPE{
		public static final String POINTS = "210001";
		public static final String YLPAY = "210002";
	}

	/**
	 * 商品状态
	 */
	public static interface MALL_STATUS{
		/** 正常*/
		public static final String NORMAL = "220001";
		/** 已下架*/
		public static final String OFF_LOADING = "220002";
	}

	/**
	 * 产品类型
	 */
	public static interface PRODUCT_TYPE{
		/** 实体产品*/
		public static final String PHYSICAL = "230001";
		/** 虚拟产品*/
		public static final String VIRTUAL = "230002";
	}

	/**
	 * 金融产品类型
	 */
	public static interface FINANCE_TYPE{

		/**融易多*/
		public static final String LYD = "240001";
		/**银行委托贷款*/
		public static final String BANK = "240002";
		/**保理*/
		public static final String BAOLI = "240003";
		/**供应链金融*/
		public static final String GONGYINGLIAN = "240004";
		/**农村合作社*/
		public static final String NONGCUNHZS = "240005";
	}

	/**
	 * 普通保险
	 */
	public static interface INSURANCE_TYPE{

		/**普通保险*/
		public static final String LYD = "250001";
	}

	public static interface FISH_TYPE{

		public static final String RUAN = "260001";

		public static final String YOUTI = "260002";

		public static final String MIAO = "260003";

		public static final String CHENGPIN = "260004";
	}

	public static interface ORDER_TYPE{
		//求购
		public static final String BUY = "270001";
		//供应
		public static final String SUPPLY = "270002";
		//自行添加
		public static final String ADD = "270003";
	}

	public static interface ORDER_STUTUS{
		//待审核
		public static final String STAY_REVIEW = "280001";
		//审核失败
		public static final String FAIL = "280002";
        //审核通过
        public static final String PASS = "280003";
		//待支付
		public static final String STAY_PAY = "280004";
		//已取消
		public static final String CANCLED = "280005";
		//待发货
		public static final String STAY_SHIP = "280006";
		//已发货
		public static final String SHIPPED= "280007";
		//已收货
		public static final String RECEIVED= "280008";
		//完成
		public static final String COMPLETE= "280009";
		//下架
		public static final String UNDER_CARRIAGE= "280010";
	}

	public static interface ADVERTISEMENT_TYPE {
		//求购详情
		public static final String BUY_DETAIL = "290001";
		//买卖行情
		public static final String MARKET_PRICE = "290002";
	}

	//支付方式
	public static interface PAY_TYPE {
		//银联支付
		public static final String YL_PAY = "300001";
		//线下支付
		public static final String OUTLINE_PAY = "300002";
	}

}
