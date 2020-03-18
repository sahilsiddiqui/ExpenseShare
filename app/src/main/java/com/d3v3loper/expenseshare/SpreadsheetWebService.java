package com.d3v3loper.expenseshare;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SpreadsheetWebService {

    @POST("https://docs.google.com/forms/u/0/d/e/1FAIpQLScn7SkOPGgGA5Ajz0DIPmHhqt0WBVnKBXRl8pfQYvQG5j09jA/formResponse")
    @FormUrlEncoded
    Call<Void> feedbackSend(
        @Field("entry.1906421542") String items,
        @Field("entry.682150236") String amount,
        @Field("entry.542264605") String name
    );

}