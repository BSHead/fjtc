package com.example.panda.myapplication.location;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.security.MessageDigest;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2018/10/11/011.
 */

public class GetKeyStor {
    private static GetKeyStor getKeyStor;

    public static GetKeyStor newInsance() {
        if(getKeyStor == null) {
            getKeyStor = new GetKeyStor();
        }

        return getKeyStor;
    }

    public void getSign(Context ctx){
        try {
            PackageInfo packageInfo = ctx.getPackageManager().getPackageInfo("com.example.panda.myapplication", PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            Signature sign = signs[0];
            MessageDigest md1 = MessageDigest.getInstance("MD5");
            md1.update(sign.toByteArray());
            byte[] digest = md1.digest();
            String res = toHexString(digest);
            Log.e(TAG, "apk md5 = "+res);
            MessageDigest md2 = MessageDigest.getInstance("SHA1");
            md2.update(sign.toByteArray());
            byte[] digest2 = md2.digest();
            String res2 = toHexString(digest2);
            Log.e(TAG, "apk SHA1 = "+res2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String toHexString(byte[] block) {
        StringBuffer buf = new StringBuffer();
        int len = block.length;
        for (int i = 0; i < len; i++) {
            byte2hex(block[i], buf);
            if (i < len-1) {
                buf.append(":");
            }
        }
        return buf.toString();
    }
    private void byte2hex(byte b, StringBuffer buf) {
        char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }

}
