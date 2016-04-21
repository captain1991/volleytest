package xiaodong.com.volleytest;

/**
 * 常量；
 * Created by gqy on 2016/2/24.
 */
public class AppConstants {
    public static final String CHECK_UPDATE_SERVICE_ACTION = "com.ayd.rhcf.update_service_aciton";
    public static final String DOWNLOAD_SERVICE_ACTION = "com.ayd.rhcf.download_service_aciton";
    public static final String CHECK_UPDATE_RECEIVER_ACTION = "com.ayd.rhcf.update_receiver_aciton";
    public static final String DOWNLOAD_RECEIVER_ACTION = "com.ayd.rhcf.download_receiver_aciton";


    /* 以下密钥要替换为自己申请的(目前只是个人的)*/
    public static final String weixin_id = "wx967daebe835fbeac";
    public static final String weixin_secret = "5bb696d9ccd75a38c8a0bfe0675559b3";

    public static final String qq_id = "100424468";
    public static final String qq_secret = "c7394704798a158208a74ab60104f0ba";

    public static final String sina_id = "3921700954";
    public static final String sina_secret = "04b48b094faeb16683c32669824ebdad";


    public static final int APP_UPDATE_ID = 2; //app更新任务id；
    public static final int PATCH_DOWN_ID = 3; //patch文件下载任务id；

    public static final String APK_FILE_PATH = "http://music.baidu.com/cms/BaiduMusic-danqu.apk";
    public static final String PATCH_FILE_PATH = "http://music.baidu.com/cms/BaiduMusic-danqu.apk";
    /**
     * 补丁文件的路径；
     */
    public static final String APATCH_PATH = "out.apatch";

    public static final String INTENT_BUNDLE = "intent_bundle";

    public static final float VP_IMG_WH_SCALE = 2.0f; //首页ViewPager图片宽高比例；
    public static final String ZTXM_FRAGMENT_TAG = "ZTXM_FRAGMENT_TAG";
    public static final String ZZXM_FRAGMENT_TAG = "ZZXM_FRAGMENT_TAG";
    public static final String HTTP_PREFIX = "http";
    public static final String PREF_NAME = "rhcf_sp";
    public static final String WKTHFZF_FRAGMENT_TAG = "WKTHFZF_FRAGMENT_TAG";

    /*==========打开Dialog的请求和结果码==============*/
    public static final int TX_LKTX_REQ_CODE = 1001;
    public static final int DIALOG_TX_GETYHK_RESULT_CODE = 1002;
    public static final int DIALOG_TX_LKTX_RESULT_CODE = 1003;
    public static final int TX_GETYHK_REQ_CODE = 1000;
    /*==============================================*/

    public static final String TOGGLE_RECV_MSG = "toggle_recv_msg";

    /*===================网络请求相关=======================*/
    public static final String CZ_REQ_TAG = "cz_req_tag";
    public static final String FINDBACKPWD_REQ_TAG = "findbackpwd_req_tag";
    public static final String GMZQ_REQ_TAG = "gmzq_req_tag";
    public static final String GRZX_REQ_TAG = "grzx_req_tag";

    public static final String HKJH_REQ_TAG = "Hkjh_req_tag";
    public static final String JYJL_REQ_TAG = "Jyjl_req_tag";
    public static final String LccpDetail_REQ_TAG = "LccpDetail_req_tag";
    public static final String LiCai_Tzjl_REQ_TAG = "LiCai_Tzjl_req_tag";
    public static final String LiCai_Xmxx_REQ_TAG = "LiCai_Xmxx_req_tag";
    public static final String LiCai_REQ_TAG = "LiCai_req_tag";

    public static final String Login_REQ_TAG = "Login_req_tag";
    public static final String ModifyLoginPwd_REQ_TAG = "ModifyLoginPwd_req_tag";
    public static final String MyHfzh_REQ_TAG = "MyHfzh_req_tag";
    public static final String Myyq_REQ_TAG = "Myyq_req_tag";
    public static final String Ptgg_REQ_TAG = "Ptgg_req_tag";

    public static final String Qrtz_REQ_TAG = "Qrtz_req_tag";
    public static final String Registration_REQ_TAG = "Registration_req_tag";
    public static final String Splash_REQ_TAG = "Splash_req_tag";
    public static final String Tx_REQ_TAG = "Tx_req_tag";
    public static final String ResetPwd_REQ_TAG = "ResetPwd_req_tag";
    public static final String Yjfk_REQ_TAG = "Yjfk_req_tag";
    public static final String Yqhy_REQ_TAG = "Yqhy_req_tag";
    public static final String Zqxx_REQ_TAG = "Zqxx_req_tag";


    public static final String SHOUYE_REQ_TAG = "shouye_req_tag";
    public static final String LICAI_LCCP_REQ_TAG = "licai_lccp_req_tag";
    public static final String LICAI_ZQZR_REQ_TAG = "licai_zqzr_req_tag";
    public static final String UPDATE_VERSION_REQ_TAG = "update_version_req_tag";
}
