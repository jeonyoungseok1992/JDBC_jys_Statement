package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.MemberService;
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
		
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), 
							 email, phone, address, hobby);
		
		int result = new MemberService().insertMember(m);
		
		if (result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원 추가를 완료하였습니다");
		}else
			new MemberMenu().displayFail("회원 추가에 실패하였습니다");
	
	}
	
	/**
	 * 전체 회원 정보 출력
	 */
	public void selectList() {
		ArrayList<Member> list = new MemberService().selectList();
		
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("전체 조회 결과가 없습니다");
		}else {
			new MemberMenu().displayMemberList(list);
		}
	}
	
	
	
	

	/**
	 * 사용자의 아이디로 회원 검색 요청을 처리해주는 메서드
	 * @param userId : 사용자가 검색하고자 입력 한 아이디
	 */
	public void selectByUserId(String userId) {
		Member m = new MemberService().selectByUserId(userId);
		
		if(m == null) {
			new MemberMenu().displayNoData(userId + "검색 결과가 없습니다");
		}else {
			new MemberMenu().displayMember(m);
		}
	}

	/**
	 * 이름으로 키워드 검색 요청 시 처리해주는 메서드
	 * @param userName : 사용자가 입력한 검색 할 키워드명
	 */
	public void selectByUserName(String Keyword) {
		ArrayList<Member> list = new MemberService().selectByUserName(Keyword);
		
		if(list.isEmpty()) {
			new MemberMenu().displayNoData(Keyword + " 키워드 검색 결과가 없습니다");
		}else
			new MemberMenu().displayMemberList(list);
	}
	
	public void updateMember(String userId, String userPwd, String email
			,String phone,String address) {
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		int result = new MemberService().updateMember(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 수정하였습니다");
		}else
			new MemberMenu().displayFail("변경에 실패하였습니다");
		
		
	
	}
	
	
	
	
	public void deleteMember(String userId) {
		int result = new MemberService().deleteMember(userId);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 탈퇴되었습니다");
		}else
			new MemberMenu().displayFail("회원 탈퇴에 실패하였습니다");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
