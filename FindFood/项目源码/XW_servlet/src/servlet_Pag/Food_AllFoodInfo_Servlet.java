package servlet_Pag;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo_Pag.Shop;
import action_impl_Pag.ShopImpl;
import action_impl_Pag.UserImpl;

public class Food_AllFoodInfo_Servlet extends HttpServlet {

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
        
        
       
        
        ShopImpl impl=new ShopImpl();
        
        //String string=impl.userSelfSearch(1);   //调用一条数据（json）；
        
        //user=impl.userSelfSearch(1);
        String list=impl.getAllFood();
        
        
        
        
        if(list!=null)result=list;//字符型数据要用.equals表示等于
        else {
			result="fail";
		}
        
//        shop.getS_id();
//        shop.getS_number();
//        shop.getU_account();
//        shop.getS_introduction();
//        shop.getS_address();
//        shop.getS_phone();
//        shop.getS_headP();
//        shop.getS_tuiJianP();
//        shop.getS_workTime();
        
         
        outPrintWriter.write(result);
        outPrintWriter.flush();
        outPrintWriter.close();
		
        
        //System.out.println(shop);
        System.out.println("用户  "+" 登录结果-----"+result);
        System.out.println();
		
	}
}
