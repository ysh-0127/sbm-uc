package edu.uc.dao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 【泛型基接口】标准的十个签名
 *
 * @author Liuvei
 * @param <T>
 */
public interface BaseDao<T> {
    Long insert(T bean);
    Long delete(Long id);
    Long update(T bean);
    List<T> list();
    /**
     * 根据主键加载一行数据
     * @param id
     * @return
     */
    T load(Long id);
    /**
     * 根据名称加载一行数据
     * @param name
     * @return
     */
    T loadByName(String name);
    /**
     * 取所有行数
     * @return
     */
    Long count();
    /**
     * 根据名称，取符合要求的行数
     * @param name
     * @return
     */
    Long countByName(String name);

    List<T> pager(@Param("pageNum") Long pageNum, @Param("pageSize") Long pageSize);
    /**
     * 分页: 按查找的名称
     *
     * 方法参数超过一个，MyBatis要求使用@Param注解标明参数名称；
     * 否则，运行出错。
     */
    List<T> pagerByName(@Param("name") String name
            , @Param("pageNum") Long pageNum, @Param("pageSize") Long pageSize);
}