package com.huaxin.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.huaxin.bean.UserInfo;
import com.huaxin.util.Constant;

@Controller
@RequestMapping(value="/index")
@SessionAttributes({Constant.USER_SESSION_KEY}) 
public class IndexController {
	@RequestMapping(method=RequestMethod.GET)
	public String index(@ModelAttribute(Constant.USER_SESSION_KEY) UserInfo user, Model model) {
		model.addAttribute("user", user);
		return "index";
	}
}
