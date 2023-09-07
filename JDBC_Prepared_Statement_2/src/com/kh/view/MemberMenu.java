package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

//View : 사용자가 보게 될 시각적인 요소(화면) 출력 및 입력
public class MemberMenu {
	
	private Scanner sc = new Scanner(System.in);
	
	//MemberController 객체생성(전역에서 바로 요청 할 수 있도록)
	private MemberController mc = new MemberController();
	
	/**
	 * 사용자가 보게 될 첫 화면(메인화면)
	 */
	public void mainMenu() {

		
		
		while(true) {
			
			System.out.println("\n==회원관리 프로그램==");
			System.out.println("1. 회원추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디로 해당정보 조회");
			System.out.println("4. 회원 이름으로 키워드 검색");
			System.out.println("5. 정보변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			
			System.out.print(">> 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
				case 1: 
					inputMember();
					break;
				case 2: 
					//printMember();
					mc.selectList();
					break;
				case 3: 
					//String userId = inputMemberId();
					//mc.selectByUserId(userId);
					mc.selectByUserId(inputMemberId());
					break;
				case 4: 
					mc.selectByUserName(inputMemberName());
					break;
				case 5: 
					//USERID로 찾아서
					//패스워드 이메일 전화번호 주소 변경
					updateMember();
					
					break;
				case 6: 
					// DELETE FROM MEMBER WHERE USERID = '입력 아이디'
//					String userId = inputMemberId();
//					mc.deleteMember(userId);
					
					mc.deleteMember(inputMemberId());
					break;
				case 0: 
					System.out.println("프로그램을 종료합니다.");
					return;
				default:
					System.out.println("메뉴 번호를 잘 못 입력함. 다시입력 ㄱㄱ");
			}
		}
		
	}

	/**
	 * 회원 추가 창(서브화면) 즉, 추가하고자 하는 회원의 정보를 입력받아 회원의 추가요청을 하는 창
	 */
	public void inputMember() {
		
		System.out.println("\n== 회원 추가 ==");
		//id부터 hobby까지 입력받기
		
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		
		System.out.print("성별(M/F) : ");
		String gender = sc.nextLine().toUpperCase();
		
		System.out.print("나이 : ");
		String age = sc.nextLine();
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("전화번호(-제외) : ");
		String phone = sc.nextLine();
		
		System.out.print("주소 : ");
		String address = sc.nextLine();
		
		System.out.print("취미(,로 이어 작성) : ");
		String hobby = sc.nextLine();
		
		//회원 추가 요청 ==> Controller에 메소드 요청
		mc.insertMember(userId, userPwd, userName, gender, age, 
						email, phone, address, hobby);


		
	}
	
	public String inputMemberId() {

		
		System.out.println("\n 회원 아이디 입력 : ");
		return sc.nextLine();
	}
	
	public String inputMemberName() {
		
		System.out.println("\n 회원 이름(키워드) 입력 : ");
		return sc.nextLine();
		
	}
	
	public void updateMember() {
		System.out.println("\n== 회원 정보 변경==");
		
		String userId = inputMemberId();
		
		System.out.print("변경 할 비밀번호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("변경 할 이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("변경 할 전화번호 : ");
		String phone = sc.nextLine();
		
		System.out.print("변경 할 주소 : ");
		String address = sc.nextLine();
		
		mc.updateMember(userId, userPwd, email, phone, address );
	}
	
	
	
	
	
	
	
	
	
	//--------------------------응답화면--------------------------
	/**
	 * 서비스 요청 처리 후 성공했을 경우 사용자가 보게 될 응답화면
	 * @param message : 객체 별 성공 메세지
	 */
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공 : " + message);
		
	}
	
	
	
	/**
	 * 서비스 요청 처리 후 실패했을 경우 사용자게 보게 될 응답화면
	 * @param message : 객체 별 실패 메세지
	 */
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패 : " + message);
		
	}
	

	/**
	 * 조회 서비스 요청 시 조회결과가 없을 때 사용자가 보게 될 응답화면
	 * @param message : 조회 결과에 대한 응답메세지
	 */
	public void displayNoData(String message) {
		System.out.println("\n" + message);
		
	}
	
	public void displayMemberList(ArrayList<Member> list) {
		System.out.println("\n 조회 된 데이터는 다음과 같습니다\n");
		
		for(int i = 0; i < list.size() ; i++) {
			//Member m = list.get(i) 생략
			System.out.println(list.get(i));
		}
	}
	
	public void displayMember(Member m) {
		System.out.println("\n조회 된 데이터는 다음과 같습니다");
		System.out.println(m);
	}

}
