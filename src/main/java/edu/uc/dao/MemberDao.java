package edu.uc.dao;
import edu.uc.bean.Member;
import org.apache.ibatis.annotations.Mapper;


public interface MemberDao extends BaseDao<Member> {
//标准签名放泛基的接口中，额外独有的签名放实体的dao接口中
}
