package edu.uc.service.impl;

public class BaseServiceImpl<T> {
    /**
     * 【处理页码】
     （2）子类MemberServicelmpl：实现签名并调用dao的方法
     *
     * ui层传来的pageNum，在daoimpl要转为startIndex或startNum；
     *
     * 但daoimpl已由MyBatis生成，无法处理，故当前方案是在serviceimpl里处理。
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Long dealPageNum(Long pageNum,Long pageSize){
        return new Long((pageNum -1) * pageSize);
    }
    /**
     * 【处理页大小】
     *
     * ui层传来的pageNum，在daoimpl要转为endIndex或endNum或原来的pageSize；
     *
     * 但daoimpl已由MyBatis生成，无法处理，故当前方案是在serviceimpl里处理。
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Long dealPageSize(Long pageNum,Long pageSize) {
        return new Long(pageSize);
    }
}

