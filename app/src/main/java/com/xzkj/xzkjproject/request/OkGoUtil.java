package com.xzkj.xzkjproject.request;

import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.lzy.okhttputils.model.HttpHeaders;
import com.lzy.okhttputils.model.HttpParams;
import com.xzkj.xzkjproject.interfaces.IRequestLisenter;
import com.xzkj.xzkjproject.model.RequestDto;
import com.xzkj.xzkjproject.utils.LogsUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by sunqi on 2018/2/27.
 */

public class OkGoUtil<T> {
    private HttpParams params;
    private Class<T> clazz;
    private int JsonType;
    public static final int TYPE_ARRAY = 1001;
    public static final int TYPE_DATA = 1002;
    public static final int TYPE_STRING = 1003;
    private static final int RESULT_CODE_DATANULL = 10001;//数据null
    private static final int RESULT_CODE_ERRER = 10002;//返回异常信息
    private static final int RESULT_CODE_CALLBACK = 10003;//mCallback是null
    private static final int RESULT_CODE_NULL = 10004;//code是null
    private IRequestLisenter<T> mCallback;
    private Gson gson = new Gson();

    public OkGoUtil(Class<T> clazz, int JsonType, IRequestLisenter<T> callback) {
        this.clazz = clazz;
        this.JsonType = JsonType;
        this.mCallback = callback;
        initHttpHeaders();
    }

    public OkGoUtil() {
        initHttpHeaders();
    }

    /**
     * Get请求
     *
     * @param url
     */
    public void requestGet(String url) {
        OkHttpUtils.get(addTelMsgByGet(url)).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (s != null && !s.isEmpty()) {
                    analysisData(s);
                } else {
                    if (mCallback != null) {
                        mCallback.onErrer(RESULT_CODE_DATANULL, "s == null");
                    }
                }

            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                if (mCallback != null && response != null) {
                    mCallback.onErrer(response.code(), response.message());
                }
            }
        });
    }

    /**
     * Post请求
     *
     * @param url
     */
    public void requestPost(String url) {
        OkHttpUtils.post(addTelMsgByGet(url)).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (s != null && !s.isEmpty()) {
                    analysisData(s);
                } else {
                    if (mCallback != null) {
                        mCallback.onErrer(RESULT_CODE_DATANULL, "s == null");
                    }
                }
            }
            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);

            }
        });
    }

    private void analysisData(String content) {
        LogsUtil.d("----------content---------" + content);
        try {
            JSONObject object = new JSONObject(content);
            RequestDto dto = new RequestDto();
            if (object.has("code")) {
                int code = object.getInt("code");
                dto.setErrCode(code);
                if (200 == code) {
                    switch (JsonType) {
                        case TYPE_ARRAY:
                            try {
                                String data = object.getString("data");
                                JSONArray array = new JSONArray(data);
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject obj = array.getJSONObject(i);
                                    T date = gson.fromJson(obj.toString(), clazz);
                                    dto.getList().add(date);
                                }
                                if (mCallback != null) {
                                    mCallback.onSuccessData(dto);
                                }
                            } catch (JSONException e) {
                                // TODO 自动生成的 catch 块
                                e.printStackTrace();
                                if (mCallback != null) {
                                    mCallback.onErrer(RESULT_CODE_ERRER, e.getMessage());
                                } else {
                                    mCallback.onErrer(RESULT_CODE_CALLBACK, "mCallback == null");
                                }
                            }
                            break;
                        case TYPE_DATA:
                            try {
                                String data = object.getString("data");
                                if (gson != null) {
                                    dto.setData(gson.fromJson(data, clazz));
                                }
                                if (mCallback != null) {
                                    mCallback.onSuccessData(dto);
                                } else {
                                    mCallback.onErrer(RESULT_CODE_CALLBACK, "mCallback == null");
                                }
                            } catch (JSONException e) {
                                // TODO 自动生成的 catch 块
                                e.printStackTrace();
                                if (mCallback != null) {
                                    mCallback.onErrer(RESULT_CODE_ERRER, e.getMessage());
                                }
                            }
                            break;
                        case TYPE_STRING:
                            if (mCallback != null) {
                                dto.setResult(content);
                                mCallback.onSuccessData(dto);
                            } else {
                                if (mCallback != null) {
                                    mCallback.onErrer(RESULT_CODE_CALLBACK, "mCallback == null");
                                }
                            }
                            break;
                    }
                } else {
                    mCallback.onErrer(code, "");
                }
            } else {
                mCallback.onErrer(RESULT_CODE_NULL, "code = null");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            if (mCallback != null) {
                mCallback.onErrer(RESULT_CODE_ERRER, e.getMessage());
            }
        }
    }


    public void params(String key, String value) {
        params.put(key, value);
    }

    /*** 添加手机信息（Get） ***/
    private static String addTelMsgByGet(String url) {

        return url;
    }

    public void initHttpHeaders() {
        params = new HttpParams();
        HttpHeaders headers = new HttpHeaders();

        OkHttpUtils.getInstance().addCommonHeaders(headers);
    }
}
