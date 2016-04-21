package xiaodong.com.volleytest;

/**
 * Created by gqy on 2016/3/8.
 */
public interface HRCallBack {

    void httpResponse(int resultCode, Object... responseData);
    void httpResponse(int resultCode, Object responseData);
}
