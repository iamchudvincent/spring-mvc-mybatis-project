package controller.admin;

import java.sql.Timestamp;
import java.util.*;

import model.AgenciesModel;
import model.CountryModel;
import model.CourseTypeModel;
import model.ProvinceModel;
import model.FeedbackModel;
import model.StudentTypeModel;
import model.TbsRoleUserModel;
import model.TipsMessageModel;
import org.springframework.util.FileCopyUtils;
import service.CourseTypeService;
import service.ProvinceService;
import service.FeedbackService;
import service.StudentTypeService;
import service.CountryService;
import service.AgenciesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import util.spring.MyTimestampPropertyEdit;
import util.spring.SessionUtil;
import util.core.MethodUtil;
import util.core.ExcelUtil;
import util.core.PageParams;
import util.core.StringUtilKit;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;

@Controller
@RequestMapping("/admin/Feedback/")
public class FeedbackControllerAdmin extends BaseController {
    private final static Logger log = Logger.getLogger(FeedbackControllerAdmin.class);
    private static MethodUtil util = new MethodUtil();

    @Autowired
    service.TbsMenuService<model.TbsMenuModel> tbsMenuService;

    // 服务类
    @Autowired(required = false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
    private FeedbackService<FeedbackModel> feedbackService;
    @Autowired
    private StudentTypeService<StudentTypeModel> studentTypeService;
    @Autowired
    private CourseTypeService<CourseTypeModel> courseTypeService;
    @Autowired
    private ProvinceService<ProvinceModel> provinceService;
    @Autowired
    private CountryService<CountryModel> countryService;
    @Autowired
    private AgenciesService<AgenciesModel> agenciesService;


    @InitBinder//必须有一个参数WebDataBinder 日期类型装换
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Timestamp.class, new MyTimestampPropertyEdit()); //使用自定义属性编辑器
        //  binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }


    /**
     * Load StudentType Model
     */
    public void loadStudentTypeModel(ModelMap modelMap) {
        try {
            List<StudentTypeModel> listStudentTypeModel = studentTypeService.selectByModel(new StudentTypeModel());
            modelMap.put("listStudentTypeModel", listStudentTypeModel);
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * Load CourseType Model
     */
    public void loadCourseTypeModel(ModelMap modelMap) {
        try {
            List<CourseTypeModel> listCourseTypeModel = courseTypeService.selectByModel(new CourseTypeModel());
            modelMap.put("listCourseTypeModel", listCourseTypeModel);
        } catch (Exception ex) {
            log.error(ex);
        }
    }


    /**
     * Load ProvinceModel
     *
     * @param modelMap
     */
    public void loadProvinceModel(ModelMap modelMap) {
        try {
            List<ProvinceModel> listProvinceModel = provinceService.selectByModel(new ProvinceModel());
            modelMap.put("listProvinceModel", listProvinceModel);
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    /**
     * Load CountryModel
     *
     * @param modelMap
     */
    public void loadCountryModel(ModelMap modelMap) {
        try {
            List<CountryModel> listCountryModel = countryService.selectByModel(new CountryModel());
            modelMap.put("listCountryModel", listCountryModel);
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    /**
     * Load CountryModel
     *
     * @param modelMap
     */
    public void loadAgenciesModel(ModelMap modelMap) {
        try {
            List<AgenciesModel> listAgenciesModel = agenciesService.selectByModel(new AgenciesModel());
            modelMap.put("listAgenciesModel", listAgenciesModel);
        } catch (Exception ex) {
            log.error(ex);
        }
    }


    /**
     * Index page
     *
     * @param parm
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping("index.html")
    public ModelAndView index(String parm, ModelMap modelMap, HttpServletRequest request) {
        List<String> buttons = new java.util.ArrayList<String>();
        model.TbsMenuModel tbsMenuModel = new model.TbsMenuModel();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentId", parm);
        map.put("orderCondition", "sortNumber");
        System.out.println("id:" + parm);
        String isAdmin = (String) SessionUtil.getAttr(request, "isAdmin");
        List<model.TbsMenuModel> list = null;
        try {
            if (null != isAdmin && isAdmin.equals("0")) {// 超管不需要验证权限
                list = tbsMenuService.selectByMap(map);
            } else {
                list = tbsMenuService.selectByButtons(map);
            }
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    tbsMenuModel = list.get(i);
                    String button = tbsMenuModel.getButton();
                    if (null != button && "null" != button) {
                        buttons.add(button);//.replaceAll("&apos;", "'").replaceAll("&quot;", "\"")
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("buttons", buttons);
        loadStudentTypeModel(modelMap);
        loadCourseTypeModel(modelMap);
        loadProvinceModel(modelMap);
        loadCountryModel(modelMap);
        loadAgenciesModel(modelMap);
        return new ModelAndView("admin/Feedback/index", modelMap);
    }

    /**
     * <br>
     * <b>功能：</b>转向指定的视图<br>
     * <b>作者：</b>Chud<br>
     * <b>日期：</b> 2018-04-02 <br>
     *
     * @return
     */
    @RequestMapping("charts.html")
    public String charts() {
        return "admin/Feedback/charts";
    }


    /**
     * <br>
     * <b>功能：</b>图表<br>
     * <b>作者：</b>Chud<br>
     * <b>日期：</b> 2018-04-02 <br>
     *
     * @param response
     * @param startTime
     * @param endTime
     * @param chartsName
     * @param type
     */
    @RequestMapping("chartsJson.html")
    public void chartsJson(HttpServletResponse response, String startTime, String endTime, String chartsName, Integer type) {
        String resultJson = "[]";
        String data = null;
        String categories = null;
        String startTimeFormat = null;
        String endTimeFormat = null;
        String groupTimeFormat = null;
        String year = "%Y";
        String month = "%m";
        String day = "%d";
        String hour = "%H";
        String minute = "%i";
        String second = "%s";
        Map<String, String> paramMap = new HashMap<String, String>();
        if (null == chartsName) {
            chartsName = "line";
        }
        if (null == type) {
            type = 4;
        }
        switch (type) {
            case 1: //年
                groupTimeFormat = year;
                break;
            case 2://月
                startTimeFormat = year + "-00";
                endTimeFormat = year + "-12";
                groupTimeFormat = year + "-" + month;
                break;
            case 3://日
                startTimeFormat = year + "-" + month + "-01";
                endTimeFormat = year + "-" + month + "-31";
                groupTimeFormat = year + "-" + month + "-" + day;
                break;
            case 4://时
                startTimeFormat = year + "-" + month + "-" + day + " 00";
                endTimeFormat = year + "-" + month + "-" + day + " 24";
                groupTimeFormat = year + "-" + month + "-" + day + " " + hour;
                break;
            case 5://分
                startTimeFormat = year + "-" + month + "-" + day + " " + hour + ":01";
                endTimeFormat = year + "-" + month + "-" + day + " " + hour + ":59";
                groupTimeFormat = year + "-" + month + "-" + day + " " + hour + ":" + minute;
                break;
            case 6://秒
                startTimeFormat = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00";
                endTimeFormat = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":59";
                groupTimeFormat = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
                break;
            default:
                break;
        }
        paramMap.put("startTimeFormat", startTimeFormat);
        paramMap.put("endTimeFormat", endTimeFormat);
        paramMap.put("groupTimeFormat", groupTimeFormat);
        try {
            List<Map<?, ?>> list = feedbackService.charts(paramMap);
            if (null != list && list.size() > 0) {
                System.out.println("list:" + list.size());
                data = "\"data\":[";
                categories = "\"categories\":[";
                for (int i = 0; i < list.size(); i++) {
                    Map<?, ?> map = list.get(i);
                    int count = ((Long) map.get("COUNT(*)")).intValue();
                    String time = map.get("createTime").toString();
                    data += count + ",";
                    categories += "\"" + time + "\",";
                    System.out.println("count:" + count + "|time:" + time);
                }
                data = data.substring(0, data.length() - 1);
                data = data + "]";
                categories = categories.substring(0, categories.length() - 1);
                categories = categories + "]";
                System.out.println(data);
                System.out.println(categories);
                resultJson = "{" + data + "," + categories + "}";
                util.toJsonPrint(response, resultJson);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        util.toJsonPrint(response, resultJson);
    }


    /**
     * <br>
     * <b>功能：</b>转向指定的视图<br>
     * <b>作者：</b>Chud<br>
     * <b>日期：</b> 2018-04-02 <br>
     *
     * @return
     */
    @RequestMapping("baseDlg.html")
    public String baseDlg() {
        return "admin/Feedback/baseDlg";
    }

    /**
     * <br>
     * <b>功能：</b>转向指定的视图<br>
     * <b>作者：</b>Chud<br>
     * <b>日期：</b> 2018-04-02 <br>
     *
     * @return
     */
    @RequestMapping("importDlg.html")
    public String importDlg() {
        return "admin/Feedback/importDlg";
    }

    /**
     * <br>
     * <b>功能：</b>转向指定的视图<br>
     * <b>作者：</b>Chud<br>
     * <b>日期：</b> 2018-04-02 <br>
     *
     * @return
     */
    @RequestMapping("searchDlg.html")
    public String searchDlg() {
        return "admin/Feedback/searchDlg";
    }


    /**
     * <br>
     * <b>功能：</b>方法功能描述<br>
     * <b>作者：</b>Chud<br>
     * <b>日期：</b> 2018-04-02 <br>
     *
     * @param pageParams
     * @param start_date
     * @param end_date
     * @param feedbackModel
     * @return
     * @throws Exception
     */
    @RequestMapping("data.html")
    @ResponseBody
    public String data(PageParams pageParams, String start_date, String end_date, FeedbackModel feedbackModel) throws Exception {
        System.out.println("pageParams:" + pageParams + "|tbsRoleUserModel:" + feedbackModel);
        feedbackModel.getPageUtil().setPaging(true);
        String result = "[]";
        if (pageParams.getPage() != null) {
            try {
                feedbackModel.getPageUtil().setPageId(Integer.parseInt(pageParams.getPage())); // 当前页
            } catch (Exception e) {
                log.error(e);
            }
        }
        if (pageParams.getRows() != null) {
            try {
                feedbackModel.getPageUtil().setPageSize(Integer.parseInt(pageParams.getRows()));// 显示X条
            } catch (Exception e) {
                log.error(e);
            }
        }
        if (pageParams.getSort() != null) {
            try {
                feedbackModel.getPageUtil().setOrderByCondition(pageParams.getSort()); // 排序字段名称
            } catch (Exception e) {
                log.error(e);
            }
        }

        // tbsRoleUserModel.getPageUtil().setOrderDirection(true); //true false 表示 正序与倒序
        String str = "";
        String suffix = "}";
        if (pageParams.getGridName() != null) {
            str = "[";
            suffix = "]}";
        }
        List<FeedbackModel> listFeedbackModel = null;
        StringBuilder center = new StringBuilder();

        if (pageParams.getSearchType() != null) {
            if (pageParams.getSearchType().equals("1")) { // 模糊搜索
                feedbackModel.getPageUtil().setLike(true);

                //New Conditions
                if (StringUtilKit.notBlank(start_date) && StringUtilKit.notBlank(end_date)) {

                    feedbackModel.getPageUtil().setAndCondition(" fb.createTime between '" + start_date + " 00:00:00 ' and '" + end_date + " 23:59:59' ");  //new
                } else if (StringUtilKit.notBlank(start_date)) {

                    feedbackModel.getPageUtil().setAndCondition(" fb.createTime like '%" + start_date + "%' ");  //new
                } else if (StringUtilKit.notBlank(end_date)) {

                    feedbackModel.getPageUtil().setAndCondition(" fb.createTime like '%" + end_date + "%' ");  //new
                }

                listFeedbackModel = feedbackService.selectByModel(feedbackModel);
                center.append("{\"total\":\"" + feedbackModel.getPageUtil().getRowCount() + "\",\"rows\":" + str);
            } else {
                StringBuffer sb = new StringBuffer(); // 高级查询
                String[] searchColumnNameArray = pageParams.getSearchColumnNames().split("\\,");
                String[] searchAndsArray = pageParams.getSearchAnds().split("\\,");
                String[] searchConditionsArray = pageParams.getSearchConditions().split("\\,");
                String[] searchValsArray = pageParams.getSearchVals().split("\\,");
                System.out.println(Arrays.toString(searchColumnNameArray));
                for (int i = 0; i < searchColumnNameArray.length; i++) {
                    if (searchColumnNameArray[i].trim().length() > 0 && searchConditionsArray[i].trim().length() > 0) {
                        if (i == 0) {
                            sb.append(searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " '" + searchValsArray[i].trim() + "'");
                        } else {
                            sb.append(" " + searchAndsArray[i].trim() + " " + searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " '" + searchValsArray[i].trim() + "'");
                        }
                    }
                }
                if (sb.length() > 0) {
                    System.out.println("searchCondition:" + sb.toString());
                    feedbackModel.getPageUtil().setAndCondition(sb.toString());
                    listFeedbackModel = feedbackService.selectByModel(feedbackModel);
                    center.append("{\"total\":\"" + feedbackModel.getPageUtil().getRowCount() + "\",\"rows\":" + str);
                }
            }
        } else {
            if (pageParams.getGridName() == null) {
                listFeedbackModel = feedbackService.selectByModel(feedbackModel);
                center.append("{\"total\":\"" + feedbackModel.getPageUtil().getRowCount() + "\",\"rows\":");
                suffix = "}";
            } else {
            }
        }

        if (pageParams.getGridName() == null) { //datagrid
            center.append(JSON.toJSONString(listFeedbackModel));
        } else {
        }
        center.append(suffix);
        result = center.toString();
        System.out.println("json:" + result);
        return result;
    }


    /**
     * <br>
     * <b>功能：</b>导入<br>
     * <b>作者：</b>Chud<br>
     * <b>日期：</b> 2018-04-02 <br>
     *
     * @param request
     * @param response
     */
    @RequestMapping("import.html")
    public void tbsRoleUserImport(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();
            ExcelUtil excelUtil = new ExcelUtil();
            String[] columnName = new String[]{"id", "href", "text"};
            try {
                Map<String, List<FeedbackModel>> ml = excelUtil.readExcel(new ByteArrayInputStream(mf.getBytes()), columnName, 2, FeedbackModel.class);
                for (Map.Entry<String, List<FeedbackModel>> map : ml.entrySet()) {
                    List<FeedbackModel> lt = map.getValue();
                    for (int i = 0; i < lt.size(); i++) {
                        try {
                            feedbackService.insert(lt.get(i));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                util.toJsonMsg(response, 1, null);
                return;
            }
        }
        util.toJsonMsg(response, 0, null);
    }


    /**
     * AddView
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("addView.html")
    public String addView(ModelMap modelMap) {
        loadStudentTypeModel(modelMap);
        loadCourseTypeModel(modelMap);
        loadProvinceModel(modelMap);
        loadCountryModel(modelMap);
        loadAgenciesModel(modelMap);
        return "admin/Feedback/addView";
    }


    @RequestMapping("editView.html")
    public String editView(ModelMap modelMap, String feedbackId) {
        loadStudentTypeModel(modelMap);
        loadCourseTypeModel(modelMap);
        loadProvinceModel(modelMap);
        loadCountryModel(modelMap);
        loadAgenciesModel(modelMap);
        try {
            FeedbackModel feedbackModel = feedbackService.selectByPrimaryKey(feedbackId);
            modelMap.put("feedbackModel", feedbackModel);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "admin/Feedback/editView";
    }


    @RequestMapping("detail.html")
    public String detail(ModelMap modelMap, String feedbackId) {
        loadStudentTypeModel(modelMap);
        loadCourseTypeModel(modelMap);
        loadProvinceModel(modelMap);
        loadCountryModel(modelMap);
        loadAgenciesModel(modelMap);
        try {
            FeedbackModel feedbackModel = feedbackService.selectByPrimaryKey(feedbackId);
            modelMap.put("feedbackModel", feedbackModel);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "admin/Feedback/detail";
    }


    @RequestMapping("add.html")
    public String add(FeedbackModel feedbackModel, String returnUrl, ModelMap modelMap, HttpServletResponse response) {
        feedbackModel.setId(util.getUid());//设置主键
        try {
            if (feedbackService.insert(feedbackModel) > 0) {
                TipsMessageModel success_tips = new TipsMessageModel(TipsMessageModel.TIPS_STATUS_ADD_SUCCESS, "admin/Feedback/addView.html", "View List", returnUrl, "View Detail", "");
                modelMap.put("tips", success_tips);
                return "forward:/admin/Common/messageView.html";
            }
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        TipsMessageModel error_tips = new TipsMessageModel(TipsMessageModel.TIPS_STATUS_FAILED);
        modelMap.put("tips", error_tips);
        return "forward:/admin/Common/messageView.html";
    }


    @RequestMapping("save.html")
    public String save(FeedbackModel feedbackModel, String returnUrl, ModelMap modelMap, HttpServletResponse response) {
        try {
            if (feedbackService.updateByPrimaryKey(feedbackModel) > 0) {
                TipsMessageModel success_tips = new TipsMessageModel(TipsMessageModel.TIPS_STATUS_ADD_SUCCESS, returnUrl, "View List", returnUrl, "View Detail", "");
                modelMap.put("tips", success_tips);
                return "forward:/admin/Common/messageView.html";
            }
        } catch (Exception e) {
            util.toJsonMsg(response, 1, null);
            e.printStackTrace();
        }
        TipsMessageModel error_tips = new TipsMessageModel(TipsMessageModel.TIPS_STATUS_FAILED);
        modelMap.put("tips", error_tips);
        return "forward:/admin/Common/messageView.html";
    }


    @RequestMapping("del.html")
    public void del(String[] ids, HttpServletResponse response) {
        System.out.println("del-ids:" + Arrays.toString(ids));
        try {
            if (null != ids && ids.length > 0) {
                if (feedbackService.deleteByPrimaryKeys(ids) > 0) {
                    util.toJsonMsg(response, 0, null);
                    return;
                }
            }
        } catch (Exception e) {
            util.toJsonMsg(response, 1, null);
            log.error(e);
        }
    }


}
