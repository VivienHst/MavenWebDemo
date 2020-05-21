package dev.vivienhuang.mavenwebdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.vivienhuang.mavenwebdemo.entity.animal.AnimalVO;
import dev.vivienhuang.mavenwebdemo.service.animal.IAnimalService;

@Controller
@RequestMapping("/animal")
public class AnimalController {
	@Autowired
	IAnimalService animalService;
	
	@GetMapping("/all")
	public String getAllAnimalPage(@RequestParam(value = "page",required=false)Integer page, Model model) {
		
//		GetURLAnimalContent getURLAnimalContent = new GetURLAnimalContent();
//
//		List<AnimalVO> addAnimalVOS = getURLAnimalContent.GetUrlContent("https://www.steamxo.com/2020/04/03/977764");
////		animalService.insert(animalVOS.get(0));
//
//		for (AnimalVO animalVO : addAnimalVOS) {
//			animalService.createAnimal(animalVO);
//		}
		
		if(page == null) {
			page = 0;
		}
		
		List<AnimalVO> animalVOs = animalService.getAnimals();
		
		int countPerPage = 10;
		int startIndex = 0;
		int endIndex = 0;
		int maxPage = animalVOs.size() / countPerPage;
		
		if(page > maxPage) {
			page = maxPage;
		}
		
		startIndex = page * countPerPage;
		endIndex = startIndex + countPerPage;
		if(endIndex > animalVOs.size()) {
			endIndex = animalVOs.size();
		}
		
		model.addAttribute("isFirstPage", startIndex == 0);
		model.addAttribute("isLastPage", endIndex == animalVOs.size());
		model.addAttribute("currentPage", page);

		model.addAttribute("animalVOs", animalVOs.subList(startIndex, endIndex));
 		return "animal";
	}
	
	@GetMapping("/update")
	public String geUpdatePage(Model model) {
		return "updateAnimal";
	}
	
	@GetMapping("/add")
	public String getAddPage(Model model) {
		model.addAttribute("animalVO", new AnimalVO());
		return "addAniaml";
	}
	
	@PostMapping("/addAnimal")
	public String addAnimal(@Valid AnimalVO animalVO,BindingResult bindingResult, ModelMap modelMap) {
		
		if(bindingResult.hasErrors()) {
			return "addAniaml";
		}
		
		animalService.createAnimal(animalVO);
		
		return "redirect:/animal/all";
	}
}
