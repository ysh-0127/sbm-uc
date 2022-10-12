package edu.uc.service.impl;
import edu.uc.bean.Member;
import edu.uc.dao.MemberDao;
import edu.uc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service("memberService")
public class MemberServiceImpl extends BaseServiceImpl<Member> implements
        MemberService {

    @Autowired
    private MemberDao memberDao;
    @Override
    public Long insert(Member bean) {
        return memberDao.insert(bean);
    }
    @Override
    public Long delete(Long id) {
        return memberDao.delete(id);
    }
    @Override
    public Long update(Member bean) {
        return memberDao.update(bean);
    }
    @Override
    public List<Member> list() {
        return memberDao.list();
    }
    @Override
    public Member load(Long id) {
        return memberDao.load(id);
    }
    @Override
    public Member loadByName(String name) {
        return memberDao.loadByName(name);
    }
    @Override
    public Long count() {
        return memberDao.count();
    }
    @Override
    public Long countByName(String name) {
        return memberDao.countByName(name);
    }

    @Override
    public List<Member> pager(Long pageNum, Long pageSize) {
        pageNum = dealPageNum(pageNum,pageSize);
        pageSize = dealPageSize(pageNum,pageSize);
        return memberDao.pager(pageNum,pageSize);
    }
    @Override
    public List<Member> pagerByName(String name, Long pageNum, Long pageSize) {
        pageNum = dealPageNum(pageNum,pageSize);
        pageSize = dealPageSize(pageNum,pageSize);
        return memberDao.pagerByName(name,pageNum,pageSize);
    }

}
