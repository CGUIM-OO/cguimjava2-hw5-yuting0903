import java.util.ArrayList;
public class Player extends Person{
	private String name;//玩家姓名
	private int chips;// 玩家有的籌碼
	private int bet; //玩家此局下注的籌碼
	public Player(String name, int chips)//新增Player物件時，需要姓名、擁有的籌碼等兩個參數
	{
		 this.name=name;
		 this.chips=chips;
	}
	public String getName()//回傳姓名
	{
		return name;
	}
	public int makeBet()//回傳預計下注的籌碼
	{
		if(chips>0)//如果還有錢
			{
			bet=1;//預設下注1元
			return bet;
			}
		else
			return 0;//如果沒錢下注0元
		
	}
	public boolean hitMe()//判斷是否要牌
	{
		if(getTotalValue()<=16)//如果目前點數總和小於16則繼續要牌
			return true;
		else
			return false;//如果目前點數總和大於16則不要牌
	}
	public int getCurrentChips()
	{
		return chips;//回傳玩家現有籌碼
	}
	public void increaseChips (int diff)
	{
		chips = chips+diff;//玩家籌碼變動
	}
	public void sayHello()
	{
		System.out.println("Hello, I am " + name + ".");
	    System.out.println("I have " + chips + " chips.");
	}
	public boolean hit_me(Table tbl) {
		  int total_value = getTotalValue();
		  if (total_value < 17)
		   return true;
		  else if (total_value == 17 && hasAce()) {
		   return true;
		  } else {
		   if (total_value >= 21)
		    return false;
		   else {
		    Player[] players = tbl.get_player();
		    int lose_count = 0;
		    int v_count = 0;
		    int[] betArray = tbl.get_palyers_bet();
		    for (int i = 0; i < players.length; i++) {
		     if (players[i] == null) {
		      continue;
		     }
		     if (players[i].getTotalValue() != 0) {
		      if (total_value < players[i].getTotalValue()) {
		       lose_count += betArray[i];
		      } else if (total_value > players[i].getTotalValue()) {
		       v_count += betArray[i];
		      }
		     }
		    }
		    if (v_count < lose_count)
		     return true;
		    else
		     return false;
		   }
		  }

		 }
}


