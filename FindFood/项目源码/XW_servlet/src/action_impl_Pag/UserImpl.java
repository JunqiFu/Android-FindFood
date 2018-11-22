package action_impl_Pag;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;







import sun.misc.BASE64Encoder; 








import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;




import pojo_Pag.User;
import action_dao_Pag.UserDAO;

public class UserImpl implements UserDAO{

	//登录
	@Override
	public String userIsExsit(String U_account, String U_password) {
		// TODO Auto-generated method stub
		
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("从上世纪差"+U_account+"  "+U_password);
		String sql="select * from tb_user where U_account='"+U_account+"' and U_password='"+U_password+"'";
		
		Query query=session.createSQLQuery(sql);
		
		@SuppressWarnings("unchecked")
		List<User> list=query.list();
		
		System.out.println("从上世纪差"+list.size());
		
		if(list.size()>0)return "exsit";
		
		session.getTransaction().commit();
        session.close();
        sessionFactory.close();
		return "no_exsit";
		
		
	}

	
	//注册
	@Override
	public int userRegister(User user) {
		// TODO Auto-generated method stub
		int num=0;
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		
		String sql="select * from tb_user where U_account='"+user.getU_account()+"'";
		
		Query query=session.createSQLQuery(sql);
		
String sql2="select * from tb_user";
		
		Query query2=session.createSQLQuery(sql2);
		@SuppressWarnings("unchecked")
		List<User> list=query.list();
		List<User> list3=query2.list();
		
		int ll=list3.size()+1;
		int a=list.size();
		if(user!=null&&a==0){
		String sql1="insert into tb_user values("+ll+",'"+user.getU_account()+"','"+user.getU_password()+"','无','无','无','无',0.00,'无','无')";
			//session.save(user);
		int ak=session.createSQLQuery(sql1).executeUpdate();
			num=1;
		}
		session.getTransaction().commit();//向数据库传输数据
        session.close();
        sessionFactory.close();
		return num;
	}

	
	
	//利用json传输数据
	//例如：  [{"U_account":"1","U_name":"1","U_balance":11.55,"U_id":1,"U_address":"1","U_password":"1","U_sex":"1","U_phone":"1"}]

	
	@Override
	public String userSelfSearch(String a) {
		// TODO Auto-generated method stub
		//User user=null;
		String string=null;
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		String sql="select * from tb_user where U_account='"+a+"'";
		
		Query query=session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)这是把，列名加上的代码
		
		@SuppressWarnings("unchecked")
		List<User> list=query.list();
		
//		InputStream inputStream1=null;
//		byte[] data1=null;
//		
//			try {
//		        inputStream1 = new FileInputStream("F:/yxp/myStudy/MyEclipse/my project/XW_servlet/images/user/abc.jpg");
//		        data1 = new byte[inputStream1.available()];
//		        inputStream1.read(data1);
//		        inputStream1.close();
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
//			
//			BASE64Encoder encoder=new BASE64Encoder();
//			
//			String img=encoder.encode(data1);
//		     
//		//解码时data:image/jpeg;base64,这段必须在前面
//		
		Gson gson=new Gson();
		string=gson.toJson(list);
			
//		JSONArray array=JSONArray.fromObject(string);
//		JSONObject object=(JSONObject) array.get(0);
//		object.remove("U_headP");
//		object.put("U_headP", encoder.encode(data1));
//		
//		string=object.toString();
//		
//		string=object.toString();
//		
		//System.out.println("   jcd   "+encoder.encode(data1));
		return string;
	
	}


	@Override
	public String userSearch(String account) {
		// TODO Auto-generated method stub
		String string="";
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		String sql="select * from tb_user where U_account="+account+"";
		
		Query query=session.createSQLQuery(sql);
		
		@SuppressWarnings("unchecked")
		List<User> list=query.list();
		
		if(list.size()>0)string="exsit";
		
		session.getTransaction().commit();//向数据库传输数据
        session.close();
        sessionFactory.close();
		
		return string;

	}

	
	//没得密码   a为json数据

	@Override
	public String userEditInfo(String a) {
		// TODO Auto-generated method stub
		
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		
		User user=new User();
		
		if(a.startsWith("[")){
			JSONArray array=JSONArray.fromObject(a);
			JSONObject object=(JSONObject) array.get(0);
			
          user.setU_account(object.get("U_account").toString());
       // user.setU_password(object.get("U_password").toString());
          user.setU_address(object.get("U_address").toString());
          user.setU_balance(Float.parseFloat(object.get("U_balance").toString()));
          user.setU_birthday(object.get("U_birthday").toString());
         // user.setU_headP(object.get("U_headP").toString());
          user.setU_phone(object.get("U_phone").toString());
          user.setU_sex(object.get("U_sex").toString());
          user.setU_name(object.get("U_name").toString());
			
		}else if(a.startsWith("{")){
			JSONObject object=JSONObject.fromObject(a);
			
			user.setU_account(object.get("U_account").toString());
	          
	          user.setU_address(object.get("U_address").toString());
	          user.setU_balance(Float.parseFloat(object.get("U_balance").toString()));
	          user.setU_birthday(object.get("U_birthday").toString());
	         // user.setU_headP(object.get("U_headP").toString());
	          user.setU_phone(object.get("U_phone").toString());
	          user.setU_sex(object.get("U_sex").toString());
	          user.setU_name(object.get("U_name").toString());
		}
		
		
		String sql="update tb_user set U_name='"+user.getU_name()+"',U_sex='"+user.getU_sex()+"',U_phone='"+user.getU_phone()+"',U_birthday='"+user.getU_birthday()+"',U_address='"+user.getU_address()+"',U_balance="+user.getU_balance()+" where U_account='"+user.getU_account()+"'";
		
		int query=session.createSQLQuery(sql).executeUpdate();
		
		String result="";
		if(query!=0)result="success";
		
		session.getTransaction().commit();//向数据库传输数据
        session.close();
        sessionFactory.close();
        
		return result;
	}


	public void passwordUpdate(String data) {
		// TODO Auto-generated method stub
		String string="";
//		JSONArray array=JSONArray.fromObject(data);
		JSONObject object=JSONObject.fromObject(data);
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		String sql="update tb_user set U_password='"+object.get("U_password").toString()+"' where U_account='"+object.get("U_account").toString()+"'";
		
		int query=session.createSQLQuery(sql).executeUpdate();
		
//		@SuppressWarnings("unchecked")
//		List<User> list=query.list();
		
		if(query>0) System.out.println("密码修改成功");
		
		session.getTransaction().commit();//向数据库传输数据
        session.close();
        sessionFactory.close();
		
		//return string;

	}


	public void userUpdate(String data) {
		// TODO Auto-generated method stub
		
	
		JSONObject object=JSONObject.fromObject(data);
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		
		System.out.println("修改数据+++"+data);
		String sql="update tb_user set U_address='"+object.get("U_address").toString()+"',U_name='"+object.get("U_name").toString()+"',U_sex='"+object.get("U_sex").toString()+"',U_birthday='"+object.get("U_birthday").toString()+"' where U_account='"+object.get("U_account").toString()+"'";
		
		int query=session.createSQLQuery(sql).executeUpdate();
		
//		@SuppressWarnings("unchecked")
//		List<User> list=query.list();
		
		if(query>0) System.out.println("密码修改成功");
		
		session.getTransaction().commit();//向数据库传输数据
        session.close();
        sessionFactory.close();
		
		
	}
	
	
	
	
}
