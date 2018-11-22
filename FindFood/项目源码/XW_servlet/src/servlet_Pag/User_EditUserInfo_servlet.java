package servlet_Pag;

//import it.sauronsoftware.base64.Base64;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;









import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import com.google.gson.JsonSerializer;




//import javax.swing.ImageIcon;
//
//import sun.misc.BASE64Decoder;  
//import sun.misc.BASE64Encoder; 
import action_impl_Pag.UserImpl;

public class User_EditUserInfo_servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		doGet(request, response);  
        
		
		//获取移动端的请求
		
		
        //设置客户端的解码方式为utf-8  
		
		response.setContentType("text/html;charset=utf-8");  
        //  
        response.setCharacterEncoding("UTF-8");  
        
        //PrintWriter out = response.getWriter();
        
        PrintWriter outPrintWriter=response.getWriter();
        
        String result="";  //定义一个结果参数
   
//        //User user=new User();
        
        
        //String account=request.getParameter("name");  //获取移动端传过来的账号
        
        String data=request.getParameter("data");//获取移动端传来的数据
        String data1=new String(data.getBytes("ISO-8859-1"),"utf-8");
        System.out.println("数据*************78456468dd"+data);
        
//        String jk=request.getParameter("jk");// 测试移动端传来的base64编码
        
//        
//        byte[] a=null;
//        
//        
//        
//        
//        //编码
//        try {
//			FileInputStream fileInputStream=new FileInputStream("F://yxp/myStudy/MyEclipse/my project/XW_servlet/images/user/abc.jpg");
//			a=new byte[fileInputStream.available()];
//			fileInputStream.read(a);
//			fileInputStream.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//        
//       BASE64Encoder encoder=new BASE64Encoder();
//       
//       BASE64Decoder decoder=new BASE64Decoder();
//       
//       //解码
//      if(jk!=null){
//    	  byte[] b=null;
//          b=decoder.decodeBuffer(jk);
//          for(int i=0;i<b.length;i++){
//       	   if(b[i]<0){
//       		   b[i]+=256;
//       	   }
//          }
//          
//          OutputStream outputStream=new FileOutputStream("E:/yxp/test/"+account+".jpg");
//          outputStream.write(b);
//          outputStream.flush();
//          outputStream.close();
      //}
//       String bString=encoder.encode(a);
        
        
        UserImpl impl=new UserImpl();
        
        String string=impl.userEditInfo(data1);   //调用一条数据（json）；
        
        
        
        //这有问题      object=null;
        
        //创建一个解析器
        
//        
//        JSONArray array=JSONArray.fromObject(password);
//        
//        
//        JSONObject object=(JSONObject) array.get(0);
//        
//       
//        	System.out.println("ooooo******"+object.get("password").toString());
//        
//        
//        //user=impl.userSelfSearch(1);
        //String exsit=impl.userIsExsit(account, password);
        
        
        
        
        
         
        outPrintWriter.write(string);
        outPrintWriter.flush();
        outPrintWriter.close();
		
        System.out.println();
       // System.out.println("用户  “"+account+"” 登录结果-----"+result);
        System.out.println();
		
	}

}
