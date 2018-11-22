package action_dao_Pag;

import pojo_Pag.Shop;

public interface ShopDAO {
	//register 注册
		public int shopRegister(Shop shop);
		public String getAllShop();
		public String getAllFood();
		public void shopDelete(String S_number);
		
		
		public String  getAllOlder(String account);
}
