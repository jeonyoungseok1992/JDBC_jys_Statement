package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

// Controller : View를 통해서 사용자가 요청한 기능에 대해서 처리하는 담당
//				해당 메서드로 전달 된 데이터를 dao로 전달하며 호출
//				dao로부터 반환받은 결과에 따라서 성공인지 실패인지 판단 후 응답화면 결정(View 메서드 호출)
public class MemberController {
	
	
	/**	 
	 * 사용자의 추가요청을 처리해주는 메서드
	 * @param userId ~ hobby까지 : 사용자가 입력했던 정보들이 담겨있는 매개변수
 	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, 
							 String email, String phone, String address, String hobby) {
		
		//View로부터 전달받은 값을 바로 dao쪽으로 전달X
		// 어딘가에 담아서 전달  --> 어딘가 : Member객체(vo)
		
		
		//방법1) 기본생성자로 생성 후 각 필드에 setter메서드를 통해 일일히 담는 방법
		//방법2) 매개변수 생성자로 생성과 동시에 담는 방법
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), 
				email, phone, address, hobby);
		
		//System.out.println(m);
		//MemberDao md = new MemberDao();
		int result = new MemberDao().insertMember(m);  //여기에서 dao로 넘김
		
		if(result > 0) {  //insert가 성공적으로 완료
			new MemberMenu().displaySuccess("성공적으로 회원이 추가 되었습니다");
		}else { //insert가 실패
			new MemberMenu().displayFail("회원추가에 실패하였습니다");
			
		}
	}
	
//	public void printMember() {
//	//new MemberDao().ppprintMember();
//	ArrayList<Member> resultSet = new MemberDao().ppprintMember();
//	new MemberMenu().pprintMember(resultSet);
//
//		
//	}
	
	
	public void selectList() {
		ArrayList<Member> list = new MemberDao().selectList();
		//조회 된 결과에 따라서 사용자가 보게 될 응답화면 지정
		if(list.isEmpty()) {//list가 비어있을 경우
			new MemberMenu().displayNoData("전체 조회 된 결과가 없습니다");
		}else {
			new MemberMenu().displayMemberList(list);
		}
	}
	
	
	
	
//	public void searchMember(String userId) {
//		Member resultSet = new MemberDao().searchMember2(userId);
//	}
	/**
	 * 사용자의 아이디로 회원 검색 요청을 처리해주는 메서드
	 * @param userId : 사용자가 검색하고자 입력 한 아이디
	 */
	public void selectByUserId(String userId) {

		Member m = new MemberDao().selectByUserId(userId);
		
		if(m == null) {//검색 결과가 없는 경우
			new MemberMenu().displayNoData(userId +"에 해당하는 조회 결과가 없습니다");
			
		}else {//검색 결과가 있을 경우
			new MemberMenu().displayMember(m);
			
		}
		
	}

	/**
	 * 이름으로 키워드 검색 요청 시 처리해주는 메서드
	 * @param userName : 사용자가 입력한 검색 할 키워드명
	 */
	public void selectByUserName(String Keyword) {
		ArrayList<Member> list = new MemberDao().selectByUserName(Keyword);
		if(list.isEmpty()) {
			new MemberMenu().displayNoData(Keyword + "에 해당하는 데이터가 없습니다");
		}else {
			new MemberMenu().displayMemberList(list);
		}
	}
	
	public void updateMember(String userId, String userPwd, String email
			,String phone,String address) {
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		
		int result =  new MemberDao().updateMember(m);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원정보 변경 되었습니다");
		
		}else {
			new MemberMenu().displayFail("회원 정보 변경에 실패하였습니다");
		}
	}
	
	
	
	
	public void deleteMember(String userId) {
		int result = new MemberDao().deleteMember(userId);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원 탈퇴 되었습니다");
		
		}else {
			new MemberMenu().displayFail("회원 탈퇴에 실패하였습니다");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
