package controller;

import java.util.Scanner; 

import dao.UserDao;
import dto.UserDto;

public class GameController {
	
	public static final int MAX_DAY = 5;
	
	public int showStatus(UserDto userDto,
			int countTq, int countSq,
			int TQQQ, int SQQQ, 
			int money, int day) {
		int ret = 0;
		
		do {
			int total = money + (TQQQ*countTq) + (SQQQ*countSq);
				
			System.out.println("\nuser_id = " + userDto.getUserId() + "\n" + 
					"현재 턴 수 : " + day + "/" + MAX_DAY + "\n");
			//표의 열 제목
			System.out.printf("%-10s %-10s %-10s %-10s\n", "", "액수", "보유량", "주가");
			//TQQQ 데이터 출력	
			System.out.printf("%-10s %-10d %-10d %-10d\n", "TQQQ", TQQQ*countTq, countTq, TQQQ);
			//SQQQ 데이터 출력
			System.out.printf("%-10s %-10d %-10d %-10d\n", "SQQQ", SQQQ*countSq, countSq, SQQQ);
			//예수금(잔액) 데이터 출력
			System.out.println("\n현금 : " + money + "\n" + 
					"총액 : " + total + "\n");
			
			System.out.println("[1] TQQQ 사기" + "\n" + 
							"[2] TQQQ 팔기" + "\n" + 
							"[3] SQQQ 사기" + "\n" + 
							"[4] SQQQ 팔기" + "\n" + 
							"[5] 넘어가기" + "\n" +
							"[99] 때려치기" + "\n");
			System.out.print(">> ");

			Scanner sc = new Scanner(System.in);
			ret = sc.nextInt();
			if(!(ret>0 && ret<6) && (ret!=99)){
				System.out.println("[System] 잘못된 입력입니다.");
			}
		}while( !(ret>0 && ret<6) && (ret!=99) );
		
		return ret;
	}
	
	public int buyTQ(int money, int TQQQ) {
		Scanner sc = new Scanner(System.in);
		int ret = 0;
		do {
			System.out.print("몇 주를 살까요? : ");	
			ret = sc.nextInt();
			if(ret*TQQQ <= money) {
				break;
			}else {
				System.out.println("[Error] 현재 보유하고 있는 현금으로 매수할 수 없습니다.");
			}
		}while(true);
		sc.close();
		return ret;
	}
	
	public int sellTQ(int countTq) {
		Scanner sc = new Scanner(System.in);
		int ret = 0;
		do {
			System.out.print("몇 주를 팔까요? : ");
			ret = sc.nextInt();
			if(countTq>=ret) {
				break;
			}else {
				System.out.println("[Error] 현재 보유하고 있는 양보다 많습니다.");
			}
		}while(true);
		sc.close();
		return ret;
	}
	
	public int buySQ(int money, int SQQQ) {
		Scanner sc = new Scanner(System.in);
		int ret = 0;
		do {
			System.out.print("몇 주를 살까요? : ");	
			ret = sc.nextInt();
			if(ret*SQQQ <= money) {
				break;
			}else {
				System.out.println("[Error] 현재 보유하고 있는 현금으로 매수할 수 없습니다.");
			}
		}while(true);
		sc.close();
		return ret;
	}
	
	public int sellSQ(int countSq) {
		Scanner sc = new Scanner(System.in);
		int ret = 0;
		do {
			System.out.print("몇 주를 팔까요? : ");
			ret = sc.nextInt();
			if(countSq>=ret) {
				break;
			}else {
				System.out.println("[Error] 현재 보유하고 있는 양보다 많습니다.");
			}
		}while(true);
		sc.close();
		return ret;
	}
	
	public void updateTopScore(String userId, int newScore) {
		UserDto dto = UserDao.getUserDto(userId);
		int topScore = dto.getTopScore();
		if(newScore>topScore) {
			try {
				UserDao.updateTopScore(dto.getUserNo(), newScore);
				System.out.println("[System] " + userId + "님이 기록을 생신하였습니다.");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return;
	}
}