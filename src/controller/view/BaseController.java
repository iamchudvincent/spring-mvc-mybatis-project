package controller.view;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import util.core.MethodUtil;

/**
 * @BaseController 
 * @Common Function
 * @author Leo
 * @Datetime 2013-12-05
 */
public class BaseController extends MultiActionController{
	protected final Logger log = Logger.getLogger(BaseController.class);
	public static final String CONTEXT_PATH = "base";
	//Tools Class
	protected static  MethodUtil util = new MethodUtil();
	// Service Class
	
	/**
	 * 
	 * OutPut JSON
	 * @param t
	 * @param response
	 */
	/*
	public <T> void toJson(T t,HttpServletResponse response){
		 try {
			PrintWriter pw=response.getWriter();
			pw.write(JSONUtil.toJSONString(t));
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e);
		}
	}*/
}

