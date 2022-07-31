package com.example.user.dao;

import com.example.user.entity.UserWechat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (UserWechat)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-24 22:12:50
 */
public interface UserWechatDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserWechat queryById(String id);

//    /**
//     * 查询指定行数据
//     *
//     * @param userWechat 查询条件
//     * @param pageable   分页对象
//     * @return 对象列表
//     */
//    List<UserWechat> queryAllByLimit(UserWechat userWechat, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param userWechat 查询条件
     * @return 总行数
     */
    long count(UserWechat userWechat);

    /**
     * 新增数据
     *
     * @param userWechat 实例对象
     * @return 影响行数
     */
    int insert(UserWechat userWechat);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserWechat> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserWechat> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserWechat> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserWechat> entities);

    /**
     * 修改数据
     *
     * @param userWechat 实例对象
     * @return 影响行数
     */
    int update(UserWechat userWechat);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

