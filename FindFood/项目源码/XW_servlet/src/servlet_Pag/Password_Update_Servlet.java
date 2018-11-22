package servlet_Pag;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//import pojo_Pag.Order;
import pojo_Pag.Shop;
import pojo_Pag.User;
//import action_impl_Pag.OrderImpl;
import action_impl_Pag.ShopImpl;
import action_impl_Pag.UserImpl;


public class Password_Update_Servlet extends HttpServlet{

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
        //String result="";
        
        //user.setU_id(6);
       // String result="";
        String data=request.getParameter("data");
        String data1=new String(data.getBytes("ISO-8859-1"),"utf-8");
       // String U_newpassword=request.getParameter("U_newpassword");
        
        UserImpl impl=new UserImpl();
        
       // String result1=impl.passwordSearch(U_password);
        
        //if(result1=="sure"){
        	impl.passwordUpdate(data1);
        	//result="修改成功";
        //}else
        	//result="旧密码错误，请重新输入";
        
       outPrintWriter.write("true");
        outPrintWriter.flush();
        outPrintWriter.close();
//        System.out.println();
//        System.out.println("注册数据-----");
//        System.out.println(result1);
//        System.out.println("注册结果-----"+"修改成功");
//        System.out.println();
	}
}
