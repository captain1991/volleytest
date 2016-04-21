package xiaodong.com.volleytest;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yxd on 2016/4/15.
 */
public class MultiPartRequest extends Request<String> {

    private MultipartEntity entity = new MultipartEntity();
    private final Response.Listener<String> mListener;
    private List<File> mFileParts;
    private String mFilePartName;
    private Map<String, String> mParams;

    /**
     * 单个文件
     *
     * @param method
     * @param url
     * @param errorListener
     * @param mListener
     * @param filePartName
     * @param file
     * @param params
     */
    public MultiPartRequest(int method, String url, Response.ErrorListener errorListener, Response.Listener<String> mListener
            , String filePartName, File file, Map<String, String> params) {
        super(method, url, errorListener);
        this.mListener = mListener;
        mFileParts = new ArrayList<File>();
        if (file != null) {
            mFileParts.add(file);
        }
        mFilePartName = filePartName;
        mParams = params;
        buildMultipartEntity();
    }

    private void buildMultipartEntity() {
        if (mFileParts != null && mFileParts.size() > 0) {
            for (File file : mFileParts) {
                entity.addPart(mFilePartName, new FileBody(file));
            }
//            long l = entity.getContentLength();
        }

        try {
            if (mParams != null && mParams.size() > 0) {
                for (Map.Entry<String, String> entry : mParams.entrySet()) {
                    entity.addPart(entry.getKey(),new StringBody(entry.getValue(), Charset
                                    .forName("UTF-8")));
                }
            }
        } catch (UnsupportedEncodingException e) {
            VolleyLog.e("UnsupportedEncodingException");
        }
    }

//    @Override
//    public String getBodyContentType() {
//        return entity.getContentType().getValue();
//    }

//没搞懂
    @Override
    public String getBodyContentType() {
        return super.getBodyContentType();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            entity.writeTo(bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        if (headers == null || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }

        return headers;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
        if (VolleyLog.DEBUG) {
            if (networkResponse.headers != null) {
                for (Map.Entry<String, String> entry : networkResponse.headers
                        .entrySet()) {
                    VolleyLog.d(entry.getKey() + "=" + entry.getValue());
                }
                }
        }

            try {
            String reqStr = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            return Response.success(reqStr, HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            String reqStr = new String(networkResponse.data);
            return Response.success(reqStr, HttpHeaderParser.parseCacheHeaders(networkResponse));
        }
    }

    @Override
    protected void deliverResponse(String s) {
        mListener.onResponse(s);
    }
}
