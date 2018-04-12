package controller.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SystemIndexControllerView extends BaseController{
	
	/**
	 * 系统首页入口
	 * System 
	 */
	
	//Index page
	@RequestMapping("index")
	public String index(ModelMap modelMap,HttpServletRequest request) {
		modelMap.put(CONTEXT_PATH, request.getContextPath());
		return "modules/home/index";
	} 
	
	
	//About OurCulture
	@RequestMapping("ourCulture.html")
	public String ourCulture(ModelMap modelMap,HttpServletRequest request) {
		modelMap.put(CONTEXT_PATH, request.getContextPath());
		return "modules/about/ourCulture";

	} 
	
	//About QQEnglish
	@RequestMapping("QQEnglish.html")
	public String qQEnglish(ModelMap modelMap,HttpServletRequest request) {
		modelMap.put(CONTEXT_PATH, request.getContextPath());
		return "modules/about/qQEnglish";
	} 
	
	//About Company
	@RequestMapping("company.html")
	public String company(ModelMap modelMap,HttpServletRequest request) {
		modelMap.put(CONTEXT_PATH, request.getContextPath());
		return "modules/about/company";
	}
	
	/**
	 * 
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping("downloadForms.html")
	public String downloadForms(ModelMap modelMap,HttpServletRequest request) {
		modelMap.put(CONTEXT_PATH, request.getContextPath());
		return "modules/about/downloadForms";
	}
	
	
	//home page
	@RequestMapping("access/nopermission")
	public String acessip(ModelMap modelMap,HttpServletRequest request) {
		//super.loadHead(modelMap);
		//super.loadLeft(modelMap);
		modelMap.put(CONTEXT_PATH, request.getContextPath());
		return "modules/accessPermission/access";
	}
   
	@RequestMapping("download.html")
	public String download(){
		System.out.println("download.html");
		return "download";
	}
	
	
	//404 page
	@RequestMapping("error/404.html")
	public String pageNotFound(ModelMap modelMap,HttpServletRequest request){
		modelMap.put(CONTEXT_PATH, request.getContextPath());
		return "error/404";
	}
	
	//500 page
	@RequestMapping("error/500.html")
	public String foundError(ModelMap modelMap){
		return "error/500";
	}	
	
	//405 page
	@RequestMapping("error/405.html")
	public String foundRequestMethodError(ModelMap modelMap){
		return "error/405";
	}
		

	
	/**
	 * *********************************************************************************
	 * New Item
	 */
	
	//intercept
	@RequestMapping("intercept.html")
	public String intercept(ModelMap modelMap,HttpServletRequest request){
		modelMap.put(CONTEXT_PATH, request.getContextPath());
		return "modules/intercept";
		
	}
	
	//Software
	@RequestMapping("software.html")
	public String software(ModelMap modelMap,HttpServletRequest request){
		modelMap.put(CONTEXT_PATH, request.getContextPath());
		return "modules/common/software";
		
	}
	
	//error
	@RequestMapping("error.html")
	public String error(ModelMap modelMap,HttpServletRequest request){
		modelMap.put(CONTEXT_PATH, request.getContextPath());
		return "modules/common/error";
		
	}
	
	
	
}
