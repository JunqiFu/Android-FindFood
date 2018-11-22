package action_impl_Pag;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;


import action_dao_Pag.UserManagerDAO;

public class UserManagerImpl implements UserManagerDAO {

	@Override
	public int deleteUser(String account) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("delete from User where U_account=?");
		query.setString(0, account);
		int a=query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		return a;
	}

}
