package com.mfrdev.myapplication.utils;

import android.content.Context;

import com.mfrdev.myapplication.R;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class NetworkErrorHandler {
    private static String getErrorMessage(Throwable throwable, Context context, String message) {
        if (throwable instanceof java.net.SocketTimeoutException || throwable instanceof java.net.ConnectException) {
            return "Could not connect to the server";
        }
        return message;
    }

    private static void logThrowable(Throwable throwable) {
        if (throwable instanceof java.net.SocketTimeoutException || throwable instanceof java.net.ConnectException) {
            return;
        }

    }

    public static Response handleThrowable(Throwable throwable, Context context, String message) {
        logThrowable(throwable);
        return Response.error(404
                , ResponseBody.create(null
                        , NetworkErrorHandler.getErrorMessage(throwable, context, message)));
    }

    public static void logUnsuccessfulResponse(Response response) throws Throwable {
        String errorString = response.errorBody().string();
        int statusCode = response.code();

    }
}
