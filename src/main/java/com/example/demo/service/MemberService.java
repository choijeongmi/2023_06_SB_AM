package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Service
public class MemberService {

	private MemberDao memberDao;

	@Autowired
	MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {

		Member existsMember = getMemberByLoginId(loginId);
		
		if(existsMember != null) {
			return ResultData.from("F-7", Util.f("이미 사용중인 아이디(%s) 입니다.", loginId));
		}
		 existsMember = getMemberByNickname(nickname);
		
		if(existsMember != null) {
			return ResultData.from("F-8", Util.f("이미 사용중인 닉네임입니다.", nickname));
		}
		existsMember = getMemberByNameAndEmaill(name, email);
		
		if(existsMember != null) {
			return ResultData.from("F-8", Util.f("이미 사용중인 이름(%s)과 e메일(%s)입니다.", name, email));
		}

		memberDao.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		return ResultData.from("S-1", Util.f("%s회원님이 가입되었습니다.", nickname),getMemberById(getLastInsertId()));
	}

	private Member getMemberByNameAndEmaill(String name, String email) {
		
		return memberDao.getMemberByNameAndEmaill(name, email);
	}

	private Member getMemberByLoginId(String loginId) {

		return memberDao.getMemberByLoginId(loginId);
	} 
	private Member getMemberByNickname(String nickname) {
		
		return memberDao.getMemberByNickname(nickname);
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public int getLastInsertId() {

		return memberDao.getLastInsertId();
	}

	// 서비스 메서드

}