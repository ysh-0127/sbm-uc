package edu.uc.service;
import edu.uc.bean.Member;
public interface MemberService extends BaseService<Member> {
//标准签名放泛基的父接口中，额外独有的签名放实体的子接口中
}