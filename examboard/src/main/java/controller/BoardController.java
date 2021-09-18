package controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import model.BoardDto;
import service.SelectService;
import service.DeleteService;
import service.EditService;
import service.WriteService;

@Controller
@SessionAttributes("boardDto")
@RequestMapping(value="/board/")
public class BoardController {
	@Autowired
	private SelectService selectService;
	@Autowired
	private WriteService writeService;
	@Autowired
	private EditService editService;
	@Autowired
	private DeleteService deleteService;
	
	@RequestMapping(value="list")
	public String list(Model model, HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		int currentPage = 0;
		if(pageNum == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(pageNum);
		}
		Map<String, Object> tmp = selectService.list(currentPage);
		List<BoardDto> articleList = (List<BoardDto>) tmp.get("articleList");
		int startNum = (Integer) tmp.get("startNum");
		int endNum = (Integer) tmp.get("endNum");
		int startPaging = (Integer) tmp.get("startPaging");
		int endPaging = (Integer) tmp.get("endPaging");
		int pageBlock = (Integer) tmp.get("pageBlock");
		int totalCount = (Integer) tmp.get("totalCount");
		model.addAttribute("articleList", articleList);
		model.addAttribute("startNum", startNum);
		model.addAttribute("endNum", endNum);
		model.addAttribute("startPaging", startPaging);
		model.addAttribute("endPaging", endPaging);
		model.addAttribute("pageBlock", pageBlock);
		model.addAttribute("totalCount", totalCount);

		return "/exboard/list";
	}
	
//	@RequestMapping(value="detail/{num}", method=RequestMethod.GET)
//	public String detail(@PathVariable int num, Model model) {
//		model.addAttribute("num", num);
//		model.addAttribute("boardDto", new BoardDto());
//		return "/exboard/detail";
//	}
	
	@RequestMapping(value="detail/{num}")
	public String detail(Model model, @PathVariable int num, String type) {
		BoardDto tmp = null;
		if(type != null) {
			tmp = selectService.move(num, type);
		}else {
			tmp = selectService.read(num);
		}
		model.addAttribute("boardDto", tmp);
		return "/exboard/detail";
	}
	
	@RequestMapping(value="write", method=RequestMethod.GET)
	public String write(Model model) {
		BoardDto tmp = new BoardDto();
		tmp.setNum(0);
		tmp.setRef(1);
		tmp.setStep(0);
		tmp.setDepth(0);
		model.addAttribute("boardDto", tmp);
		return "/exboard/writeForm";
	}
	
	@RequestMapping(value="write", method=RequestMethod.POST)
	public String write(@Valid BoardDto boardDto, BindingResult bindingResult) {
		if(boardDto.getNum() != 0) {
			writeService.reple(boardDto.getNum());
		}
		if(bindingResult.hasErrors()) {
			return "/exboard/writeForm";
		}
		writeService.write(boardDto);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="update/{num}", method=RequestMethod.GET)
	public String edit(@PathVariable int num, Model model) {
		BoardDto boardDto = selectService.read(num);
		model.addAttribute("boardDto", boardDto);
		return "/exboard/updateForm";
	}
	
	@RequestMapping(value="update/{num}", method=RequestMethod.POST)
	public String edit(@Valid @ModelAttribute BoardDto boardDto, 
			BindingResult bindingResult, String checkPass,
			SessionStatus sessionStatus, Model model) {
		if(bindingResult.hasErrors()) {
			return "/exboard/updateForm";
		}else {
			BoardDto tmp = boardDto;
			tmp.setCheckPass(checkPass);
			boolean check = editService.edit(tmp);
			if(check == true) {
				sessionStatus.setComplete();
				return "redirect:/board/list";
			}else {
				model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
				return "/exboard/updateForm";
			}
		}
	}
	
	@RequestMapping(value="delete/{num}", method=RequestMethod.GET)
	public String delete(@PathVariable int num, Model model) {
		model.addAttribute("num", num);
		return "/exboard/deleteForm";
	}
	
	@RequestMapping(value="delete/{num}", method=RequestMethod.POST)
	public String delete(@PathVariable int num, String checkPass, Model model) {
		BoardDto tmp = new BoardDto();
		tmp.setNum(num);
		tmp.setCheckPass(checkPass);
		boolean check = deleteService.delete(tmp);
		if(!check) {
			model.addAttribute("num", num);
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "/exboard/deleteForm";
		}
		return "redirect:/board/list";
	}

}
