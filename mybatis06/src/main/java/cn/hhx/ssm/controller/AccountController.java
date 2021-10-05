package cn.hhx.ssm.controller;

import cn.hhx.ssm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author hhxStellar
 * @date 2021/9/28-15:44
 */
@Controller
public class AccountController {
    @Autowired
    AccountService accountService;

    @ResponseBody
    @RequestMapping("/accountList")
    public ModelAndView accountList() {
        ModelAndView modelAndView = new ModelAndView("accountList");
        modelAndView.addObject("accountList", accountService.getAllaccount());
        return modelAndView;
    }

    @RequestMapping("/transfer")
    public String transfer() {
        return "transfer";
    }

    @ResponseBody
    @RequestMapping("/transfering")
    public ModelAndView transfering(
            @RequestParam("id1") Integer id1,
            @RequestParam("id2") Integer id2,
            @RequestParam("money") Integer money) {
        ModelAndView result = new ModelAndView("result");
        try {
            accountService.transfer(id1, id2, money);
            result.addObject("message", "转账成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.addObject("message", "转账失败");
            return result;
        }
    }


}
