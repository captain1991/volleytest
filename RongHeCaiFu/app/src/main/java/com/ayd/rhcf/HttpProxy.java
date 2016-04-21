package com.ayd.rhcf;


import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ayd.rhcf.utils.CommonUtil;
import com.ayd.rhcf.utils.GsonRequest;
import com.ayd.rhcf.utils.LogUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by gqy on 2016/3/4.
 * Http请求代理类；
 */
public class HttpProxy {
    private static final int RESPONSE_SUCCESS_CODE = 101; // success交易码；
    private static final int RESPONSE_ERROR_CODE = 102;  // fail交易码；
    private static RequestQueue requestQueue; //Volley请求队列；
    private static final int TIMEOUT_MS = 15000;//超时时间；
    private static final int MAX_NUM_RETRIES = 1;//最大的请求重试次数；

    /**
     * GET请求；
     *
     * @param context
     * @param url
     * @param reqTag           请求的tag；(取消请求使用)
     * @param params           GET请求的参数；
     * @param responseCallBack 结果回调接口；
     * @param resultCode       结果码；
     */
    public static void getDataByGet(final Context context, String url, String reqTag, Map<String, String> params, final HRCallBack responseCallBack, int resultCode) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(url);

        if (params != null && params.size() > 0) {
            urlBuilder.append("?");
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                urlBuilder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue());

                if (iterator.hasNext()) {
                    urlBuilder.append("&");
                }
            }
        }

        String lastUrl = urlBuilder.toString();
        LogUtil.i("Get请求的url==\n" + lastUrl);

        if (TextUtils.isEmpty(lastUrl) || !lastUrl.startsWith(AppConstants.HTTP_PREFIX)) {
            return;
        }

        //参数2为空，默认是GET请求；
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                //响应成功；
                LogUtil.i("响应====\n" + jsonObject.toString());
                if (responseCallBack != null) {
                    responseCallBack.httpResponse(RESPONSE_SUCCESS_CODE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (responseCallBack != null) {
                    responseCallBack.httpResponse(RESPONSE_ERROR_CODE);
                }
                notifyResult(context, error);
            }
        });
        jsonRequest.setTag(reqTag);

        //超时；
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_MS, MAX_NUM_RETRIES, 1.0f));
        getAppRequestQueue(context).add(jsonRequest);
    }

    /**
     * GET请求直接返回一个对象（可能会有bug）；
     *
     * @param context
     * @param url
     * @param reqTag           请求的tag；(取消请求使用)
     * @param params           GET请求的参数；
     * @param responseCallBack 结果回调接口；
     * @param resultCode       结果码；
     */
    public static void getObjectByGet(final Context context, String url, String reqTag, Map<String, String> params, final HRCallBack responseCallBack, int resultCode, Class clazz) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(url);

        if (params != null && params.size() > 0) {
            urlBuilder.append("?");
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                urlBuilder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue());

                if (iterator.hasNext()) {
                    urlBuilder.append("&");
                }
            }
        }

        String lastUrl = urlBuilder.toString();
        LogUtil.i("Get请求的url==\n" + lastUrl);

        if (TextUtils.isEmpty(lastUrl) || !lastUrl.startsWith(AppConstants.HTTP_PREFIX)) {
            return;
        }

        GsonRequest gsonRequest = new GsonRequest(lastUrl, clazz, new Response.Listener() {
            @Override
            public void onResponse(Object o) {
                if (responseCallBack !=null){
                    responseCallBack.httpResponse(RESPONSE_SUCCESS_CODE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (responseCallBack !=null){
                    responseCallBack.httpResponse(RESPONSE_ERROR_CODE);
                }
                notifyResult(context, volleyError);
            }
        });

        gsonRequest.setTag(reqTag);

        //超时；
        gsonRequest.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_MS, MAX_NUM_RETRIES, 1.0f));
        getAppRequestQueue(context).add(gsonRequest);
    }

        /**
         * POST请求；
         *
         * @param context
         * @param url
         * @param reqTag           请求的tag；(取消请求使用)
         * @param params           POST请求的参数；
         * @param responseCallBack 结果回调接口；
         * @param resultCode       结果码；
         */
    public static void getDataByPost(final Context context, String reqTag, String url,
                                     Map<String, String> params, HRCallBack responseCallBack, int resultCode) {

        StringBuilder argBuilder = new StringBuilder();
        JSONObject argJson = null;
        if (params != null && params.size() > 0) {
            argBuilder.append("{");
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();

            //构造post提交的json格式参数；
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                argBuilder.append(entry.getKey() + ":" + entry.getValue());

                if (iterator.hasNext()) {
                    argBuilder.append(",");
                } else {
                    argBuilder.append("}");
                }
            }
        }

        String argJsonStr = argBuilder.toString();
        LogUtil.i("Post请求参数的json==\n" + argJsonStr);
        if (TextUtils.isEmpty(url) || !url.startsWith(AppConstants.HTTP_PREFIX)) {
            return;
        }

        try {
            argJson = new JSONObject(argJsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        //参数2不为空，是POST请求；
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, argJson, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                //响应成功；
                LogUtil.i("响应====\n" + jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                notifyResult(context, error);
            }
        });
        jsonRequest.setTag(reqTag);

        //设置超时；
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_MS, MAX_NUM_RETRIES, 1.0f));
        getAppRequestQueue(context).add(jsonRequest);
    }

    /**
     * 取消特定的请求；
     *
     * @param context
     * @param requestTag
     */
    public static void cancelSpecificRequest(Context context, String requestTag) {
        if (CommonUtil.isNotEmpty(requestTag)) {
            getAppRequestQueue(context).cancelAll(requestTag);
        }
    }

    public static RequestQueue getAppRequestQueue(Context context) {
        if (requestQueue == null) {
            synchronized (HttpProxy.class) {
                requestQueue = Volley.newRequestQueue(context);
            }
        }
        return requestQueue;
    }

    private static void notifyResult(Context context, VolleyError error) {
        if (error != null) {
            if (error instanceof TimeoutError) {
                LogUtil.i("VolleyError1====" + context.getString(R.string.time_out));
            } else if (error instanceof NetworkError || error instanceof NoConnectionError) {
                LogUtil.i("VolleyError2====" + context.getString(R.string.network_error));
            } else if (error instanceof ServerError || error instanceof AuthFailureError) {
                LogUtil.i("VolleyError3====" + context.getString(R.string.server_error));
            } else if (error instanceof ParseError) {
                LogUtil.i("VolleyError4====" + "json解析失败");
            } else {
                LogUtil.i("VolleyError5====" + context.getString(R.string.general_error));
            }
        } else {
            //为空时，提示失败；
            LogUtil.i("VolleyError====6" + context.getString(R.string.general_error));
        }
    }
}
