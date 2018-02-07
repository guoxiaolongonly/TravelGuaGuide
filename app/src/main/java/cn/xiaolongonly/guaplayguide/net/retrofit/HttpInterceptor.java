package cn.xiaolongonly.guaplayguide.net.retrofit;


import cn.xiaolongonly.guaplayguide.utils.LogUtil;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class HttpInterceptor implements Interceptor {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private RetrofitDao.IBuildPublicParams iBuildPublicParams;

    public HttpInterceptor(RetrofitDao.IBuildPublicParams iBuildPublicParams) {
        this.iBuildPublicParams = iBuildPublicParams;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        if (request.method().equals("POST") && !(request.body() instanceof MultipartBody) && request.body() instanceof RequestBody) {
        if (request.method().equals("POST") && request.body() instanceof RequestBody) {
            HttpUrl httpUrl = iBuildPublicParams.buildPublicParams(request.url().newBuilder()).build();
//            okhttp3.HttpUrl.Builder builder = iBuildPublicParams.buildPublicParams(request.url().newBuilder());
            request = request.newBuilder().url(httpUrl).build();
        }
        printRequestLog(request);
        Response response = chain.proceed(request);
        printResponseLog(response);
        return response;
    }

    private void printRequestLog(Request request) throws IOException {
        LogUtil.d(request.url().toString());
        if (request.body() instanceof MultipartBody) return;
        Buffer buffer = new Buffer();
        if(request.body()!=null) {
            request.body().writeTo(buffer);
        }
        String decode = URLDecoder.decode(buffer.readUtf8(), "UTF-8");
        String[] split = decode.split("&");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : split) {
            stringBuilder.append(s + "\n");
        }
        LogUtil.d(stringBuilder.toString());
    }


    private void printResponseLog(Response response) throws IOException {
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();

        Charset charset = Charset.forName("UTF8");
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(Charset.forName("UTF8"));
        }

        if (responseBody.contentLength() != 0) {
            String s = buffer.clone().readString(charset);
            LogUtil.json(s.trim());
        }
    }

}
