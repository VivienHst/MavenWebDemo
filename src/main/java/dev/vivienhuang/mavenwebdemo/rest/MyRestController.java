package dev.vivienhuang.mavenwebdemo.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class MyRestController {
	
	private List<String> students;
	private final String UPLOAD_DIRECTORY = "/images/";

	
	@PostConstruct
	public void loadData() {
		students = new ArrayList<>();
		
		students.add("Andy");
		students.add("Cindy");
		students.add("Evans");
	}
	
	@PostMapping("/uploadData")
	public String submit(
		@RequestParam String name,
		ModelMap modelMap) {
	 
//	    modelMap.addAttribute("name", name);
		System.out.println("name : " + name);
	    modelMap.addAttribute("name", name);
//	  
	    return "name : " + name;
	}
	              
	@PostMapping("/students")
	public List<String> getPostStudents(){
		return students;
	}
	
	@PostMapping("/uploadFileWithAddtionalData")
	public String submit(
		@RequestParam MultipartFile file,
		ModelMap modelMap) {
	 
//	    modelMap.addAttribute("name", name);
	    modelMap.addAttribute("file", file);
//	    System.out.println("name : " + name);
	    System.out.println("file : " + file.getOriginalFilename());

	    if(file != null) {
	    	List<MultipartFile> files = new ArrayList<MultipartFile>();
		    files.add(file);
		    try {
			    saveUploadedFiles(files);
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }catch (Exception e) {
		    	e.printStackTrace();
		    }
	    }
	    return "file : " + file.getOriginalFilename();
	}
	
	 //將檔案儲存
    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //繼續下一個檔案
            }

            byte[] bytes = file.getBytes();
            
            Path path = Paths.get("/Users/9suser/Downloads/image002.jpg");
            Files.write(path, bytes);

        }

    }
}
