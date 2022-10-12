package edu.uc.service;
import java.util.List;
/**
 * 【泛型基接口】标准的十个签名
 *
 * @author Liuvei
 * @param <T>
 */

public interface BaseService<T> {
    Long insert(T bean);
    Long delete(Long id);
    Long update(T bean);
    List<T> list();
    List<T> pager(Long pageNum, Long pageSize);
    List<T> pagerByName(String name, Long pageNum, Long pageSize);
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

}