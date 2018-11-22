package action_impl_Pag;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.sql.Insert;
import org.hibernate.transform.Transformers;

import com.google.gson.Gson;

import pojo_Pag.Older;
import pojo_Pag.Shop;
import sun.misc.BASE64Encoder;
import action_dao_Pag.ShopDAO;


public class ShopImpl implements ShopDAO{
	//注册
		@Override
		public int shopRegister(Shop shop) {
			// TODO Auto-generated method stub
			int num=0;
			SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			
			String sql="select * from tb_shops where S_number='"+shop.getS_number()+"'";
			
			Query query=session.createSQLQuery(sql);
			
			@SuppressWarnings("unchecked")
			List<Shop> list=query.list();
			
			
			int a=list.size();
			if(shop!=null&&a==0){
			session.save(shop);
//				Query query1=session.createSQLQuery("Insert into tb_shops values(9,?,?,?,?,?,?,?,?)");
//				query1.setString(0, shop.getS_number());
//				query1.setString(1, shop.getU_account());
//				query1.setString(2, shop.getS_introduction());
//				query1.setString(3, shop.getS_address());
//				query1.setString(4, shop.getS_phone());
//				query1.setString(5, shop.getS_headP());
//				query1.setString(6, shop.getS_tuiJianP());
//				query1.setString(7, shop.getS_workTime());
				
				System.out.println("******hhh***"+shop.getS_number()+shop.getU_account()+shop.getS_introduction()+shop.getS_address()+shop.getS_phone()+shop.getS_headP()+shop.getS_tuiJianP()+shop.getS_workTime());
//				query1.executeUpdate();
			num=1;
			}
			session.getTransaction().commit();//向数据库传输数据
	        session.close();
	        sessionFactory.close();
			return num;
		}
		
		@Override
		public String getAllShop(){ //void相当于不返回东西，此处需要返回一个集合
			// 实例化配置对象 加载映射文件 加载 hibernate.cfg.xml
			//Configuration configuration = new Configuration().configure();
			// 创建会话工厂
			SessionFactory sessionFactory =new Configuration().configure().buildSessionFactory();
			// 创建会话
			Session session = sessionFactory.openSession();
			// 开启事务
			Transaction transaction = session.beginTransaction();
			//编写HQL语句(面向类和属性的查询 

			//这里是Student不是表名 是类名 查询Student
			Shop shop=new Shop();
			Query query =session.createSQLQuery("select * from tb_shops").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//传json数据时不能用hibernate语句写数据库代码
			//query.setString(0, U_account);
			
			
			@SuppressWarnings("unchecked")
			List<Shop> list=query.list();
			
			System.out.println("*******---"+list.size());
			
			Gson gson=new Gson();
			String data=gson.toJson(list);
			

			transaction.commit();
			session.close();
			sessionFactory.close();

			return data; 
		}
		

		@Override
		public void shopDelete(String S_number) {
			// TODO Auto-generated method stub
			
			SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			//Shop shop=new Shop();
//			Shop shop=new Shop();
//			int query=session.createQuery("delete Shop where S_number="+S_number+"").executeUpdate();

//			Shop shop =new Shop();
//			shop.setS_number(S_number);
//			session.delete(shop);

			
			
			
			Query query = session.createSQLQuery("delete from tb_shops where S_number=?");   //此处的“1”表示从数据库中将数据调出给第一个问号
			query.setString(0,S_number);
			query.executeUpdate();
			
			session.getTransaction().commit();//向数据库传输数据
	        session.close();
	        sessionFactory.close();
		}

		@Override
		public String getAllFood() {
			// TODO Auto-generated method stub
			SessionFactory sessionFactory =new Configuration().configure().buildSessionFactory();
			// 创建会话
			Session session = sessionFactory.openSession();
			// 开启事务
			Transaction transaction = session.beginTransaction();
			//编写HQL语句(面向类和属性的查询 

			//这里是Student不是表名 是类名 查询Student
			Shop shop=new Shop();
			Query query =session.createSQLQuery("select * from tb_foods").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//传json数据时不能用hibernate语句写数据库代码
			//query.setString(0, U_account);
			
			
			@SuppressWarnings("unchecked")
			List<Shop> list=query.list();
			
			System.out.println("*******---"+list.size());
			
			Gson gson=new Gson();
			String data=gson.toJson(list);
			

			transaction.commit();
			session.close();
			sessionFactory.close();

			return data; 
			
		}

		@Override
		public String getAllOlder(String account) {
			// TODO Auto-generated method stub
			//Configuration configuration = new Configuration().configure();
			// 创建会话工厂
			SessionFactory sessionFactory =new Configuration().configure().buildSessionFactory();
			// 创建会话
			Session session = sessionFactory.openSession();
			// 开启事务
			Transaction transaction = session.beginTransaction();
			//编写HQL语句(面向类和属性的查询 

			//这里是Student不是表名 是类名 查询Student
			
			
			Query query =session.createSQLQuery("select * from tb_older where U_account='"+account+"'").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//传json数据时不能用hibernate语句写数据库代码
			//query.setString(0, U_account);
			
			
			@SuppressWarnings("unchecked")
			List<Older> list=query.list();
			
			System.out.println("*******---"+list.size());
			
			Gson gson=new Gson();
			String data=gson.toJson(list);
			

			transaction.commit();
			session.close();
			sessionFactory.close();

			return data; 
		}
}
