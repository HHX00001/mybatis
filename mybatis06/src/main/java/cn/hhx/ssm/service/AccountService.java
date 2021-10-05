package cn.hhx.ssm.service;

import cn.hhx.ssm.bean.Account;
import cn.hhx.ssm.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hhxStellar
 * @date 2021/9/28-15:45
 */
@Service
public class AccountService {
    @Autowired
    AccountDao accountDao;

    public List<Account> getAllaccount() {
        return accountDao.getAllAccount();
    }

    @Transactional
    public void transfer(Integer id1, Integer id2, Integer money) throws Exception {

        accountDao.updateMoney(id2, -money);

        if (accountDao.getMoney(id1) < money) {
            throw new RuntimeException("账户余额不足");
        }

        accountDao.updateMoney(id1, money);

    }
}
