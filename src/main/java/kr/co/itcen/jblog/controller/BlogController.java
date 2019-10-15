package kr.co.itcen.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.UserService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller
@RequestMapping("/{userId:(?!assets)(?!images).*}")
public class BlogController {
	@Autowired
	private UserService userService;

	@Autowired
	private BlogService blogService;


	@RequestMapping({"","/{category}","/{category}/{post}"})
	public String index(
			@PathVariable String userId,
			@PathVariable Optional<Long> category,
			@PathVariable Optional<Long> post,
			Model model,UserVo uservo){

		boolean exist = userService.existUser(userId);

		if (!exist) {
			return "redirect:/";
		}

		List<CategoryVo> list = blogService.getList(userId);
		model.addAttribute("list",list);
		Long categoryno= list.get(0).getNo();
		Long postno = 0L;
		if(post.isPresent()) {
			categoryno=category.get();
			postno=post.get();
		}else if(category.isPresent()) {
			categoryno=category.get();
		}	
		
		List<PostVo> postList= blogService.getList(categoryno);
		model.addAttribute("postList",postList);
		PostVo postvo;
		if(postno==0L && (!postList.isEmpty())) {
			postno=postList.get(0).getNo();
			postvo=blogService.getPost(postno,categoryno);
		}else {
			postvo= blogService.getPost(postno,categoryno);
		}
		BlogVo blogvo = blogService.get(userId);
		
		model.addAttribute("vo",blogvo);
		model.addAttribute("post",postvo);
		model.addAttribute("userId",userId);
		if(userId.equals(vo.getId()))
			model.addAttribute("isMe",true);
		return "blog/blog-main";
	}

}
