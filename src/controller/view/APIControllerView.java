package controller.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CustomersModel;
import model.TbsAdverModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.CustomersService;
import service.TbsAdverService;
import util.core.StringUtilKit;

@Controller
@RequestMapping("/api")
public class APIControllerView extends BaseController{
	
	/**
	 * API
	 */
	
	// Service
	@Autowired(required=false) 
	private CustomersService<CustomersModel> customersService;
	@Autowired(required=false)
	private TbsAdverService<TbsAdverModel> adverService;
	
	@RequestMapping(value = "")
	public void index(HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("info", "qqe api");
		util.toJsonTxtResponse(response, map);
	}
	
	/**
	 * Register Student
	 * Return: Jsonp
	 * @param response
	 * jsonp format:  getName({"name":"demo","age":22})
	 * Defaut method: get
	 */
	@RequestMapping(value = "/customer/jsonp/register.html", method ={RequestMethod.POST,RequestMethod.GET})
	public void register(HttpServletResponse response, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();

		/**
		 * full name and mobile is required
		 */
		//getting the data
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String nickname = request.getParameter("nickname");
		String gender = request.getParameter("gender");
		String facebook = request.getParameter("facebook");
		String skype = request.getParameter("skype");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String adid = request.getParameter("adid");
		
		if(StringUtilKit.isBlank(nickname)){
			String json = "{\"status\":2,\"message\":\"User Name is Empty!\"}";
			util.toJsonPrint(response, "callback("+json+")");
			return;
		}
		if(StringUtilKit.isBlank(mobile)){
			String json = "{\"status\":3,\"message\":\"Mobile Number is Empty!\"}";
			util.toJsonPrint(response, "callback("+json+")");
			return;
		}
		if(StringUtilKit.isBlank(email)){
			String json = "{\"status\":4,\"message\":\"Email Account is Empty!\"}";
			util.toJsonPrint(response, "callback("+json+")");
			return;
		}
		//validate the parameter
		if(StringUtilKit.isBlank(adid)){
			String json = "{\"status\":1,\"message\":\"ADID is Empty!\"}";
			util.toJsonPrint(response, "callback("+json+")");
			return;
		}
		
		CustomersModel customer = new CustomersModel();
		customer.setId(util.getUid());
		customer.setCsr_first_name(first_name);
		customer.setCsr_last_name(last_name);
		
		customer.setCsr_nickname(nickname==null?"":nickname);
		
		if(StringUtilKit.notBlank(gender)){
			if(gender.equals("female")){
				customer.setCsr_gender(1);
			}else{
				customer.setCsr_gender(2);
			}
		}
		customer.setCsr_facebook(facebook==null?"":facebook);
		customer.setCsr_skype(skype==null?"":skype);
		customer.setCsr_email(email==null?"":email);
		customer.setCsr_mobile(mobile);
		customer.setCsr_adid(adid);
		
		try {
			if(StringUtilKit.notBlank(facebook)){
				map.clear();
				map.put("csr_facebook", facebook);
				if(customersService.selectByMapCount(map)>0){
					String json = "{\"status\":5,\"message\":\"Facebook Account is already registered!\"}";
					util.toJsonPrint(response, "callback("+json+")");
					return;
				}
			}
			
			if(StringUtilKit.notBlank(skype)){
				map.clear();
				map.put("csr_skype", skype);
				if(customersService.selectByMapCount(map)>0){
					String json = "{\"status\":6,\"message\":\"Skype Account is already registered!\"}";
					util.toJsonPrint(response, "callback("+json+")");
					return;
				}
			}
			
			if(StringUtilKit.notBlank(mobile)){
				map.clear();
				map.put("csr_mobile", mobile);
				if(customersService.selectByMapCount(map)>0){
					String json = "{\"status\":7,\"message\":\"Mobile Number is already registered!\"}";
					util.toJsonPrint(response, "callback("+json+")");
					return;
				}
			}
			
			if(StringUtilKit.notBlank(email)){
				map.clear();
				map.put("csr_email", email);
				if(customersService.selectByMapCount(map)>0){
					String json = "{\"status\":8,\"message\":\"Email Account is already registered!\"}";
					util.toJsonPrint(response, "callback("+json+")");
					return;
				}
			}
			
			
			TbsAdverModel ad = new TbsAdverModel();
			ad.setAd_number(adid);
		    if(adverService.selectByModel(ad).size()>0){
		    	//Set Agency_id
		    	customer.setAgency_id(adverService.selectByModel(ad).get(0).getAd_agency_id());
		    	//Set Country_id
				customersService.insert(customer);
				String json = "{\"status\":0,\"message\":\"Successfully Registered.\"}";
				util.toJsonPrint(response, "callback("+json+")");

		    }else{
		    	String json = "{\"status\":9,\"message\":\"ADID is not exist!\"}";
				util.toJsonPrint(response, "callback("+json+")");
				return;
		    }
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String json = "{\"status\":-1,\"message\":\"Error.\"}";
			util.toJsonPrint(response, "callback("+json+")");

		}

	}
	
	
	/**
	 * Customer Register API 
	 * @param customersModel
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/customer/register.html", method ={RequestMethod.POST,RequestMethod.GET})
	public void register(CustomersModel customersModel,String sign, ModelMap modelMap, HttpServletRequest request,HttpServletResponse response){
		customersModel.setId(util.getUid());
		Map<String, Object> map = new HashMap<String, Object>();
		String api_accessKeyID = "14101614202695618364";
        String api_accessKeySecret = "e05d3b06b5025451ba0dc9cdd2cb0dcb";
        
        //ascii 
        StringBuilder param = new StringBuilder();
        param.append("accessKeyID="+api_accessKeyID);
        
        if(StringUtilKit.notBlank(customersModel.getAgency_id())){
        	param.append("&agency_id="+customersModel.getAgency_id());
        }
        if(StringUtilKit.notBlank(customersModel.getCsr_address())){
        	param.append("&csr_address="+customersModel.getCsr_address()); 
        } 
        if(StringUtilKit.notBlank(customersModel.getCsr_adid())){
        	param.append("&csr_adid="+customersModel.getCsr_adid()); 
        } 
        if(StringUtilKit.notBlank(customersModel.getCsr_email())){
        	param.append("&csr_email="+customersModel.getCsr_email()); 
        } 
        if(StringUtilKit.notBlank(customersModel.getCsr_from_ip())){
        	param.append("&csr_from_ip="+customersModel.getCsr_from_ip());
        } 
        if(StringUtilKit.notBlank(customersModel.getCsr_from_url())){
        	param.append("&csr_from_url="+customersModel.getCsr_from_url());
        }
        if(StringUtilKit.notBlank(customersModel.getCsr_http_referer())){
        	param.append("&csr_http_referer="+customersModel.getCsr_http_referer());
        } 
        if(StringUtilKit.notBlank(customersModel.getCsr_mobile())){
        	 param.append("&csr_mobile="+customersModel.getCsr_mobile());
        } 
        if(StringUtilKit.notBlank(customersModel.getCsr_nickname())){
        	 param.append("&csr_nickname="+customersModel.getCsr_nickname());
        } 
        if(StringUtilKit.notBlank(customersModel.getCsr_remarks())){
        	 param.append("&csr_remarks="+customersModel.getCsr_remarks());
        } 
        if(StringUtilKit.notBlank(customersModel.getCsr_zipcode())){
       	 param.append("&csr_zipcode="+customersModel.getCsr_zipcode());
       }
       
        param.append(api_accessKeySecret);
        String original_sign = util.getMD5(param.toString(), "UTF-8", 1);
        
		try {
		    if(StringUtilKit.notBlank(sign)){
				    if(sign.equals(original_sign)){
					map.put("csr_email", customersModel.getCsr_email());
					if(StringUtilKit.notBlank(customersModel.getCsr_email())){
						if(customersService.selectByMap(map).size()>0){
							map.clear();
							map.put("error_code", "10006");
							map.put("result_tips", "Email is already taken");
							util.toJsonTxtResponse(response,map);
							return;
						}
					}
					
					map.clear();
					map.put("csr_mobile", customersModel.getCsr_mobile());
					if(StringUtilKit.notBlank(customersModel.getCsr_mobile())){
						if(customersService.selectByMap(map).size()>0){
							map.clear();
							map.put("error_code", "10006");
							map.put("result_tips", "Mobile is already taken");
							util.toJsonTxtResponse(response,map);
							return;
						}
					}
					
					
					customersService.insert(customersModel);
					map.clear();
					map.put("error_code", "0");
					map.put("result", customersModel);
					util.toJsonTxtResponse(response,map);
				}else{
					map.clear();
					map.put("error_code", "10001");
					map.put("result", customersModel);
					util.toJsonTxtResponse(response,map);
				}
			}else{
				map.clear();
				map.put("error_code", "10003");
				map.put("result", "Sign is null,sign should be included in parameter.");
				util.toJsonTxtResponse(response,map);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.clear();
			map.put("error_code", "10005");
			util.toJsonTxtResponse(response,map);
			return;
		}
		
	}

	
}