package action_impl_Pag;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.transform.Transformers;

import pojo_Pag.Shops;
import pojo_Pag.User;

import com.google.gson.Gson;

import action_dao_Pag.ShopsDAO;

public class ShopsImpl implements ShopsDAO {

	@Override
	public String allListShops() {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		
		String sql="select * from tb_shops";
		Query query=session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		@SuppressWarnings("unchecked")
		List<Shops> list=query.list();
		
		Gson gson=new Gson();
		String data=gson.toJson(list);
		return data;
	}

}
