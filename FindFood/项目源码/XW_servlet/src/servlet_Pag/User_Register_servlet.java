package servlet_Pag;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo_Pag.User;
import action_impl_Pag.UserImpl;

public class User_Register_servlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		doGet(request, response);  
        
        //设置客户端的解码方式为utf-8  
		
		response.setContentType("text/html;charset=utf-8");  
        //  
        response.setCharacterEncoding("UTF-8");  
        
        //PrintWriter out = response.getWriter();
        
        PrintWriter outPrintWriter=response.getWriter();
        
        User user=new User();
        String result="";
        
        //user.setU_id(6);
        
        String data=request.getParameter("data");
        String data1=new String(data.getBytes("ISO-8859-1"),"utf-8");
        
        
        System.out.println("shuju***"+data1);
        
//        JSONArray array=JSONArray.fromObject(data);
        
        
        
        JSONObject object=JSONObject.fromObject(data1);
        
        user.setU_account(object.get("U_account").toString());
        
        user.setU_password(object.get("U_password").toString());
        
//        user.setU_name(object.get("U_name").toString());
//        
//        user.setU_sex(object.get("U_sex").toString());
//        user.setU_phone(object.get("U_phone").toString());
//        
//        user.setU_address(object.get("U_address").toString());
        user.setU_balance(Float.parseFloat(object.get("U_balance").toString()));
//        user.setU_birthday(object.get("U_birthday").toString());
//        user.setU_headP(object.get("U_balance").toString());
        
        
        
        
        
        
        UserImpl impl=new UserImpl();
        
        int num=impl.userRegister(user);
        
        if(num>0)result="success";
        else {
			result="fail";
		}
        
        outPrintWriter.write(result);
        outPrintWriter.flush();
        outPrintWriter.close();
        System.out.println();
        System.out.println("注册数据-----");
        System.out.println(user.getU_account()+"  "+user.getU_password()+"  "+user.getU_sex()+"  "+user.getU_name()+"  "+user.getU_phone()+"  "+user.getU_address()+"  "+user.getU_balance());
        System.out.println("注册结果-----"+result);
        System.out.println();
	}
}
