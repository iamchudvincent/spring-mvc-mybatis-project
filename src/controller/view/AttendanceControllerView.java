package controller.view;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import util.core.MethodUtil;
import util.spring.MyTimestampPropertyEdit;

@Controller
@RequestMapping("/console/Attendance/")
public class AttendanceControllerView extends BaseController {
private static  MethodUtil util = new MethodUtil();
	
	@Autowired
	service.TbsMenuService<model.TbsMenuModel> tbsMenuService;
	
	@InitBinder//必须有一个参数WebDataBinder 日期类型装换
	public void initBinder(WebDataBinder binder) {
		    binder.registerCustomEditor(Timestamp.class,new MyTimestampPropertyEdit()); //使用自定义属性编辑器
	}
	
	@RequestMapping("index.html")
	public ModelAndView index(String id, ModelMap modelMap, HttpServletRequest request) {
		modelMap.put(CONTEXT_PATH, request.getContextPath());
		//Get 3 Month data，current month,next month,after 2 month.
		Calendar c = Calendar.getInstance();
		Integer year = c.get(Calendar.YEAR);
		Integer currentMonth  = c.get(Calendar.MONTH) + 1;
		Integer afterOneMonth  = c.get(Calendar.MONTH) + 2;
		Integer afterTwoMonth  = c.get(Calendar.MONTH) + 3;
		Integer afterThreeMonth  = c.get(Calendar.MONTH) + 4;
		String current_date = year+"-"+(currentMonth<10?"0"+currentMonth:currentMonth);
		String afterOneMonthDate = year+"-"+(afterOneMonth<10?"0"+afterOneMonth:afterOneMonth);
		String afterTwoMonthDate = year+"-"+(afterTwoMonth<10?"0"+afterTwoMonth:afterTwoMonth);
		String afterThreeMonthDate = year+"-"+(afterThreeMonth<10?"0"+afterThreeMonth:afterThreeMonth);
		
		
		//数据月份显示
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy",Locale.ENGLISH);  
		c.setTime(new Date());
		c.set(Calendar.MONTH, +1);
		String currentMonth_date= sdf.format(c.getTime());
		modelMap.put("currentMonth", currentMonth_date);
		c.setTime(new Date());
		c.set(Calendar.MONTH, +2);
		String nextMonth_date= sdf.format(c.getTime());
		modelMap.put("nextMonth", nextMonth_date);
		c.setTime(new Date());
		c.set(Calendar.MONTH, +3);
		String afterTwoMonth_date= sdf.format(c.getTime());
		modelMap.put("afterTwoMonth", afterTwoMonth_date);
		return new ModelAndView("modules/attendance/index", modelMap);
	} 

}
