package cn.hhx.ssm.dao;

import cn.hhx.ssm.bean.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hhxStellar
 * @date 2021/9/28-15:45
 */
public interface AccountDao {
    List<Account> getAllAccount();

    Integer getMoney(@Param("id") Integer id);

    void updateMoney(@Param("id") Integer id, @Param("money") Integer money);
}
