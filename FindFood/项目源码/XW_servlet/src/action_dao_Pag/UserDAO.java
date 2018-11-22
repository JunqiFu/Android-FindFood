package action_dao_Pag;

import pojo_Pag.User;

public interface UserDAO {

	//login 登录
	public String userIsExsit(String U_account, String U_password);
	
	//register 注册
	public int userRegister(User user);
	
	
	//单个用户search
	public String userSelfSearch(String a);
	
	public String userSearch(String a);
	
	public String userEditInfo(String a);
	
	
		
	
}
