package com.bolyartech.d2overrides.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class HttpMockUtils {
    /**
     * Non-instantiable utility class
     */
    private HttpMockUtils() {
        throw new AssertionError();
    }


    public static OkHttpClient createWithResponseBody(String str) {
        Request req = new Request.Builder().get().url("http://fake").build();

        ResponseBody body = ResponseBody.create(MediaType.parse("text/plain"), str);
        Response resp = new Response.Builder().request(req).body(body).protocol(Protocol.HTTP_1_1).code(200).build();

        Call call = mock(Call.class);
        try {
            when(call.execute()).thenReturn(resp);
        } catch (IOException e) {
            //suppress
        }

        OkHttpClient mockHttp = mock(OkHttpClient.class);
        when(mockHttp.newCall(any(Request.class))).thenReturn(call);

        return mockHttp;
    }
}
