package com.example.myweb;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void jwr(){
        //定義一個要儲存的資訊放入Map
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","Geo");
        String token = JWT.create() //建立JWT物件
                .withClaim("user",claims) //將儲存的資訊放入名為user的Claim中
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12)) //單位：毫秒 設定當前時間+12小時
                .sign(Algorithm.HMAC256("myWebProject")); //使用HMAC256演算法與自訂密鑰簽署token
        System.out.println(token);
    }

    @Test
    public void testParse(){
        //模擬使用者傳遞的JWT字串（通常來自前端或請求標頭）
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IkdlbyJ9LCJleHAiOjE3NTIzMjk1OTh9.5y2k64oJeAXDUsbKAe8QC2KtLoX8ZrY16HbRUc_7U0s";

        //建立JWT驗證器，指定使用的密鑰與加密演算法（這裡是 HMAC256）
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("myWebProject")).build();

        //驗證並解析 token，若驗證失敗會拋出異常
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        //取得所有 Claims（聲明），這裡是從 payload 中解析出來的資料
        Map<String, Claim> claimMap = decodedJWT.getClaims();

        //印出名為 "user" 的 Claim 內容
        System.out.println(claimMap.get("user"));
    }
}
