
public class Card {

		 	enum Suit{Club,Diamond,Heart,Spade};//把樸克牌的花色用enum列出
			//Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
			private int rank; //1~13
			Suit suit;
			public Card(Suit s, int value){
				suit=s;
				rank=value;
			}	
			//TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10 for rank)
			public void printCard(){
				//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace
				System.out.print(getSuit().name());	
				if(rank==1)//設定rank
						System.out.println(" A");
					else if(rank==2)
						System.out.println(" 2");
					else if(rank==3)
						System.out.println(" 3");
					else if(rank==4)
						System.out.println(" 4");
					else if(rank==5)
						System.out.println(" 5");
					else if(rank==6)
						System.out.println(" 6");
					else if(rank==7)
						System.out.println(" 7");
					else if(rank==8)
						System.out.println(" 8");
					else if(rank==9)
						System.out.println(" 9");
					else if(rank==10)
						System.out.println(" 10");
					else if(rank==11)
						System.out.println(" J");
					else if(rank==12)
						System.out.println(" Q");
					else if(rank==13)
						System.out.println(" K");
					
					}
				
			public Suit getSuit(){
					return suit;//取得花色
				
			}
			public int getRank(){
				return rank;//取得牌號
			}
		}
	


