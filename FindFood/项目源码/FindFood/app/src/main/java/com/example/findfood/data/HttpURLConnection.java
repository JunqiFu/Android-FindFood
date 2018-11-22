package com.example.findfood.data;

import android.util.Log;

import com.example.findfood.app.LoginActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by French on 2018/6/30.
 */

public class HttpURLConnection {
    private static String LOGIN_URL = "http://172.16.204.128:8080/XW_servlet/";

    public static String LoginByPost(String name, String data/*,String name/*,String sex,String phone,String address,float balance*/) {
        Log.d( LoginActivity.TAG, "启动登录线程");

        String msg = "";
        try {
            //初始化URL
            URL url = new URL(LOGIN_URL+name);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            Log.d( LoginActivity.TAG, "11111");
            //设置请求方式
            conn.setRequestMethod("POST");

            //设置超时信息
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);

            //设置允许输入
            conn.setDoInput(true);
            //设置允许输出
            conn.setDoOutput(true);

            //post方式不能设置缓存，需手动设置为false
            conn.setUseCaches(false);

            //我们请求的数据   请求时  &  这个必须有！！！
            //String data1 = "data=" + URLEncoder.encode(data, "UTF-8");
            System.out.println("传出数据****"+data);
            String data2= "data=" + URLEncoder.encode(data, "GBK");

            // String data2 = "&account="+URLEncoder.encode(username,"UTF-8");
            // String data3="&jk="+URLEncoder.encode(jk,"UTF-8");
            //String data3 = "&phone="+URLEncoder.encode(name,"UTF-8");
//            String data4 = "&sex="+URLEncoder.encode(sex,"UTF-8");
//            String data5 = "&phone="+URLEncoder.encode(phone,"UTF-8");
//            String data6 = "&address="+URLEncoder.encode(address,"UTF-8");
//            String data7 = "&balance="+URLEncoder.encode(String.valueOf(balance),"UTF-8");

            Log.d( LoginActivity.TAG, "  ffff  " + data);
            //  Log.d(MainActivity.TAG,"  ffff  "+data2);
//            Log.d(MainActivity.TAG,"  ffff  "+data3);
//            Log.d(MainActivity.TAG,"  ffff  "+data4);
//            Log.d(MainActivity.TAG,"  ffff  "+data5);
//            Log.d(MainActivity.TAG,"  ffff  "+data6);
//            Log.d(MainActivity.TAG,"  ffff  "+data7);

            //獲取輸出流
            OutputStream out = conn.getOutputStream();

            out.write(data2.getBytes());
            //out.write(data2.getBytes());
            // out.write(data2.getBytes());
            //out.write(data3.getBytes());
//            out.write(data3.getBytes());
//            out.write(data4.getBytes());
//            out.write(data5.getBytes());
//            out.write(data6.getBytes());
//            out.write(data7.getBytes());

            out.flush();
            out.close();
            conn.connect();

            if (conn.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = conn.getInputStream();

                Log.d( LoginActivity.TAG, "  ffff  " + is);
                // 创建字节输出流对象
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    message.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                message.close();
//                 返回字符串
//

                msg = new String(message.toByteArray());


                //获取服务器端的json数据

//               JSONArray array=new JSONArray(msg);
//                JSONObject b=array.optJSONObject(0);
////
//                JSONObject object=b.getJSONObject("U_id");
//
//                String name1=b.getString("U_balance");
//                String name2=b.getString("U_id");
//                String name3=b.getString("U_name");
//                String name4=b.getString("U_account");
//                String name5=b.getString("U_password");
//                String name6=b.getString("U_address");
//
//                System.out.println("煞笔"+name1+"  "+name2+"  "+name3+" "+name4+"  "+name5+"  "+name6);
//                return name1+"  "+name2+"  "+name3+" "+name4+"  "+name5+"  "+name6;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
        }

//        catch (JSONException e) {
//            e.printStackTrace();
//        }

//        try {
//            JSONObject jsonObject=new JSONObject(username+passwd);
//            System.out.println(jsonObject);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }



//        RequestParams params=new RequestParams(LOGIN_URL);
//        params.addParameter("name","login");
//        params.addParameter("data","{"+username+"}");
//        x.http().get(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                msg[0] =result;
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                System.out.println(ex+"   "+isOnCallback);
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
        Log.d( LoginActivity.TAG, "exit");

        return msg;
    }
}


//    protected HttpURLConnection(URL u) {
//
//        super( u );
//
//
//    }
//
//    @Override
//    public void disconnect() {
//
//    }
//
//    @Override
//    public boolean usingProxy() {
//        return false;
//    }
//
//    @Override
//    public void connect() throws IOException {
//
//
//    }
//
//
//
//    public static class NetUtils {
//
//        // POST
//        public static String post(String url, String content) {
//            HttpURLConnection conn = null;
//            try {
//                // 创建一个URL对象
//                URL mURL = new URL(url);
//                // 调用URL的openConnection()方法,获取HttpURLConnection对象
//                conn = (HttpURLConnection) mURL.openConnection();
//
//                conn.setRequestMethod("POST");// 设置请求方法为post
//                conn.setReadTimeout(5000);// 设置读取超时为5秒
//                conn.setConnectTimeout(10000);// 设置连接网络超时为10秒
//                conn.setDoOutput(true);// 设置此方法,允许向服务器输出内容
//
//                // post请求的参数
//                String data = content;
//                // 获得一个输出流,向服务器写数据,默认情况下,系统不允许向服务器输出内容
//                OutputStream out = conn.getOutputStream();// 获得一个输出流,向服务器写数据
//                out.write(data.getBytes());
//                out.flush();
//                out.close();
//
//                int responseCode = conn.getResponseCode();// 调用此方法就不必再使用conn.connect()方法
//                if (responseCode == 200) {
//
//                    InputStream is = conn.getInputStream();
//                    String response = getStringFromInputStream(is);
//                    return response;
//                } else {
//                    throw new NetworkErrorException("response status is "+responseCode);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (conn != null) {
//                    conn.disconnect();// 关闭连接
//                }
//            }
//
//            return null;
//        }
//
//        public static String get(String url) {
//            HttpURLConnection conn = null;
//            try {
//                // 利用string url构建URL对象
//                URL mURL = new URL(url);
//                conn = (HttpURLConnection) mURL.openConnection();
//
//                conn.setRequestMethod("GET");
//                conn.setReadTimeout(5000);
//                conn.setConnectTimeout(10000);
//
//                int responseCode = conn.getResponseCode();
//                if (responseCode == 200) {
//
//                    InputStream is = conn.getInputStream();
//                    String response = getStringFromInputStream(is);
//                    return response;
//                } else {
//                    throw new NetworkErrorException("response status is "+responseCode);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//
//                if (conn != null) {
//                    conn.disconnect();
//                }
//            }
//
//            return null;
//        }
//
//        private static String getStringFromInputStream(InputStream is)
//                throws IOException {
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            // 模板代码 必须熟练
//            byte[] buffer = new byte[1024];
//            int len = -1;
//            while ((len = is.read(buffer)) != -1) {
//                os.write(buffer, 0, len);
//            }
//            is.close();
//            String state = os.toString();// 把流中的数据转换成字符串,采用的编码是utf-8(模拟器默认编码)
//            os.close();
//            return state;
//        }
//    }


//}
