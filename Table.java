import java.util.ArrayList;
public class Table {
	private final int MAXPLAYER = 4;
	private Deck allcard;//放入所有的牌
	private Player[ ] allplayer;//放入所有玩家
	private Dealer dealer;//存放莊家
	private int[] pos_betArray=new int[4];//這局你下了多少錢
	public Table(int nDeck) {
		this.allcard = allcard;
		Deck deck = new Deck(nDeck);
		allcard = deck;
		allplayer = new Player[MAXPLAYER];	//設定牌桌最多四位玩家
	}
	public void set_player(int pos, Player p) {
		allplayer[pos]=p;//將玩家放到牌桌上
	}
	public Player[] get_player() {
		return allplayer;//回傳桌子上面有誰
	}
	public void set_dealer(Dealer d) {
		dealer = d;//指定一個莊家去管桌子
	}
	public Card get_face_up_card_of_dealer() {
		return dealer.getOneRoundCard().get(1);//莊家的牌是一張打開一張蓋起來，拿莊家手上打開的牌
	}
	private void ask_each_player_about_bets() {
		for(int i=0;i<=allplayer.length-1;i++)
		{
			allplayer[i].sayHello();//每個玩家打招呼
		}
		for(int i=0;i<=allplayer.length-1;i++)
		{
			allplayer[i].makeBet();
			pos_betArray[i]=allplayer[i].makeBet();//每個玩家下注並存放起來
		}
	}
	private void distribute_cards_to_dealer_and_players() {//發牌給玩家跟莊家
		for(int i=0;i<=allplayer.length-1;i++)
		{
			ArrayList<Card> playercard=new ArrayList<Card>();
			playercard.add(allcard.getOneCard(true));
			playercard.add(allcard.getOneCard(true));
			allplayer[i].setOneRoundCard(playercard);//發兩張打開的牌給每個玩家
		}
		ArrayList<Card> dealercard=new ArrayList<Card>();
		dealercard.add(allcard.getOneCard(false));
		dealercard.add(allcard.getOneCard(true));
		dealer.setOneRoundCard(dealercard);//發一張蓋起來一張打開的卡給莊家
		System.out.print("Dealer's face up card is ");
		get_face_up_card_of_dealer().printCard();//印出莊家打開的牌
	}
	private void ask_each_player_about_hits() {
		boolean hit=false;
		for(int i=0;i<=allplayer.length-1;i++){//問每個玩家要不要牌，並印出當下印出每個玩家手上的牌
			do{
				hit=allplayer[i].hitMe(); 
				if(hit){//持續要牌
					allplayer[i].getOneRoundCard().add(allcard.getOneCard(true));
					allplayer[i].setOneRoundCard(allplayer[i].getOneRoundCard());
					System.out.print("Hit! ");
					System.out.print(allplayer[i].getName()+"'s Cards now:");
					for(Card c : allplayer[i].getOneRoundCard()){
						c.printCard();
					}
				}
				else{//不要牌
					System.out.print(allplayer[i].getName()+"'s Cards now:");
					for(Card c : allplayer[i].getOneRoundCard()){
						c.printCard();}
					System.out.println(allplayer[i].getName()+", Pass hit!");
					System.out.println(allplayer[i].getName()+"'s hit is over!");
				}
			}while(hit);
			hit=false;
		}
	}
	private void ask_dealer_about_hits() {
		boolean hit=false;
		do{
			hit=dealer.hit_me(this); //問莊家要不要牌
			if(hit){//莊家要牌
				dealer.getOneRoundCard().add(allcard.getOneCard(true));
				dealer.setOneRoundCard(dealer.getOneRoundCard());
				}
			else{//莊家不要牌
				System.out.println("Dealer's hit is over!");
				}
	}while(hit);
		hit=false;
}
	private void calculate_chips() {//去算是誰贏是誰輸，莊家和每個玩家比，莊家贏就沒收籌碼，莊家輸就給贏家下注的錢
		System.out.println("Dealer's card value is "+dealer.getTotalValue()+" ,Cards:");
		dealer.printAllCard();
		for(int i=0;i<=allplayer.length-1;i++) {
			System.out.print(allplayer[i].getName()+"'s Cards: ");
			for(Card c : allplayer[i].getOneRoundCard()){
				c.printCard();}
			System.out.print(allplayer[i].getName()+" card value is "+allplayer[i].getTotalValue());
			if(allplayer[i].getTotalValue()>21 && dealer.getTotalValue()>21){
				System.out.println(",chips have no change! The Chips now is: "+allplayer[i].getCurrentChips());//莊家和玩家皆超過21，平手
			}else if(allplayer[i].getTotalValue()<=21&&dealer.getTotalValue()>21){
				allplayer[i].increaseChips(allplayer[i].makeBet());//莊家點數超過21點，玩家勝利
				System.out.println(",Get "+allplayer[i].makeBet()+" Chips, the Chips now is: "+allplayer[i].getCurrentChips());
			}else if(allplayer[i].getTotalValue()>21&&dealer.getTotalValue()<=21){
				allplayer[i].increaseChips(-allplayer[i].makeBet());//玩家點數超過21點，莊家勝利
				System.out.println(", Loss "+allplayer[i].makeBet()+" Chips, the Chips now is: "+allplayer[i].getCurrentChips());
			}else if(allplayer[i].getTotalValue()>dealer.getTotalValue()&&allplayer[i].getTotalValue()<=21){
				allplayer[i].increaseChips(allplayer[i].makeBet());//兩者皆沒有超過21點，但是玩家的數字較大或剛好等於21，玩家勝利
				System.out.println(",Get "+allplayer[i].makeBet()+" Chips, the Chips now is: "+allplayer[i].getCurrentChips());
			}else if(allplayer[i].getTotalValue()<dealer.getTotalValue()&&dealer.getTotalValue()<=21){
				allplayer[i].increaseChips(-allplayer[i].makeBet());//兩者皆沒有超過21點，但是莊家的數字較大或剛好等於21，莊家勝利
				System.out.println(", Loss "+allplayer[i].makeBet()+" Chips, the Chips now is: "+allplayer[i].getCurrentChips());
			}else{
				System.out.println(",chips have no change! The Chips now is: "+allplayer[i].getCurrentChips());//雙方點數一樣平手
			}
		}
	}
	public int[] get_palyers_bet() {
		return pos_betArray;
	}
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
}
