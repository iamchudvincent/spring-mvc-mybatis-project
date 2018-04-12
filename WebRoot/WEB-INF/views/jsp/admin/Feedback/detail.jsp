<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta id="_ctl0_EmulateIE7" http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<link href="${base}/static/css/blue.css" rel="stylesheet" type="text/css" rel="stylesheet" type="text/css" />
<!-- Start -->
<!-- End -->
<style type="text/css">
html {
	background: url(${base}/static/image/common/main_bg.png) left -1px repeat-x;
	background-attachment: fixed;
}
.upload_label{
width: 5px;
height: 3px;
background: #0298ff;
display: inline-block;
vertical-align: middle;
font-size: 1px;}
#mContainer {
	padding: 20px 20px 20px 15px;
}
.divCla span {
	line-height: 100%;
}
 input type[text]{width:220px; height:25px; }
 select{width:284px;height:29px;}
</style>
<title>Publish Informations</title>
	<script charset="utf-8" src="${base}/static/js/jquery-1.8.3.min.js"></script>  
	<link rel="stylesheet" href="${base}/static/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="${base}/static/kindeditor/plugins/code/prettify.css" />
	<link id="skin" rel="stylesheet" href="${base}/static/js/jbox/blue/jbox.css" />
	<script charset="utf-8" src="${base}/static/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${base}/static/kindeditor/lang/en.js"></script>
	<script charset="utf-8" src="${base}/static/kindeditor/plugins/code/prettify.js"></script>
	<script type="text/javascript" src="${base}/static/js/jbox/jquery.jBox.src.js"></script>
	
	<!-- easyui-->
	<script type="text/javascript" src="${base}/static/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${base}/static/js/easyui/jquery.easyui.min.js"></script>
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${base}/static/js/easyui/themes/bootstrap/easyui.css?v=201301" />
	<link rel="stylesheet" type="text/css" href="${base}/static/js/easyui/themes/icon.css" />
<script>
    var glb_content_value  = "";
	KindEditor.ready(function(K) {
		var editor = K.create(
						'textarea[name="content"]',
						{
							items : [ 'source', '|', 'undo', 'redo',
									'|', 'preview', 'print',
									'template', 'code', 'cut', 'copy',
									'paste', 'plainpaste', 'wordpaste',
									'|', 'justifyleft',
									'justifycenter', 'justifyright',
									'justifyfull', 'insertorderedlist',
									'insertunorderedlist', 'indent',
									'outdent', 'subscript',
									'superscript', 'clearhtml',
									'quickformat', 'selectall', '|',
									'fullscreen', '/', 'formatblock',
									'fontname', 'fontsize', '|',
									'forecolor', 'hilitecolor', 'bold',
									'italic', 'underline',
									'strikethrough', 'lineheight',
									'removeformat', '|', 'image',
									'multiimage', 'flash', 'media',
									'insertfile', 'table', 'hr',
									'emoticons', 'baidumap',
									'pagebreak', 'anchor', 'link',
									'unlink' ],
							langType : 'en',
							cssPath : '${base}/static/kindeditor/plugins/code/prettify.css',
							uploadJson : '${base}/functions/kindeditor/jsp/upload_json.jsp',
							fileManagerJson : '${base}/functions/kindeditor/jsp/file_manager_json.jsp',
							allowFileManager : true,
							afterCreate : function() {
								var self = this;
								K.ctrl(document, 13, function() {
									self.sync();
									document.forms['contentFm'].submit();
								});
								K.ctrl(self.edit.doc, 13, function() {
									self.sync();
									document.forms['contentFm'].submit();
								});

							}
						});
		prettyPrint();
		//init
	});
</script>
<script type="text/javascript">
        /*Jbox Setting*/
        var _jBoxConfig = {};
        _jBoxConfig.defaults = {
            id: null, /* 在页面中的唯一ID，如果为null则自动为随机ID,一个ID只会显示一个jBox */
            top: '15%', /* 窗口离顶部的距离,可以是百分比或像素(如 '100px') */
            border: 5, /* 窗口的外边框像素大小,必须是0以上的整数 */
            opacity: 0.2, /* 窗口隔离层的透明度,如果设置为0,则不显示隔离层 */
            timeout: 0, /* 窗口显示多少毫秒后自动关闭,如果设置为0,则不自动关闭 */
            showType: 'fade', /* 窗口显示的类型,可选值有:show、fade、slide */
            showSpeed: 'fast', /* 窗口显示的速度,可选值有:'slow'、'fast'、表示毫秒的整数 */
            showIcon: true, /* 是否显示窗口标题的图标，true显示，false不显示，或自定义的CSS样式类名（以为图标为背景） */
            showClose: true, /* 是否显示窗口右上角的关闭按钮 */
            draggable: true, /* 是否可以拖动窗口 */
            dragLimit: true, /* 在可以拖动窗口的情况下，是否限制在可视范围 */
            dragClone: false, /* 在可以拖动窗口的情况下，鼠标按下时窗口是否克隆窗口 */
            persistent: true, /* 在显示隔离层的情况下，点击隔离层时，是否坚持窗口不关闭 */
            showScrolling: true, /* 是否显示浏览的滚动条 */
            ajaxData: {},  /* 在窗口内容使用post:前缀标识的情况下，ajax post的数据，例如：{ id: 1 } 或 "id=1" */
            iframeScrolling: 'auto', /* 在窗口内容使用iframe:前缀标识的情况下，iframe的scrolling属性值，可选值有：'auto'、'yes'、'no' */

            title: 'Message', /* 窗口的标题 */
            width: 350, /* 窗口的宽度，值为'auto'或表示像素的整数 */
            height: 'auto', /* 窗口的高度，值为'auto'或表示像素的整数 */
            bottomText: '', /* 窗口的按钮左边的内容，当没有按钮时此设置无效 */
            buttons: { 'Okay': 'ok' }, /* 窗口的按钮 */
            buttonsFocus: 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
            loaded: function (h) { }, /* 窗口加载完成后执行的函数，需要注意的是，如果是ajax或iframe也是要等加载完http请求才算窗口加载完成，参数h表示窗口内容的jQuery对象 */
            submit: function (v, h, f) { return true; }, /* 点击窗口按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
            closed: function () { } /* 窗口关闭后执行的函数 */
        };
        _jBoxConfig.stateDefaults = {
            content: '', /* 状态的内容，不支持前缀标识 */
            buttons: { 'Okay': 'ok' }, /* 状态的按钮 */
            buttonsFocus: 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
            submit: function (v, h, f) { return true; } /* 点击状态按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
        };
        _jBoxConfig.tipDefaults = {
            content: '', /* 提示的内容，不支持前缀标识 */
            icon: 'info', /* 提示的图标，可选值有'info'、'success'、'warning'、'error' */
            top: '40%', /* 提示离顶部的距离,可以是百分比或像素(如 '100px') */
            width: 'auto', /* 提示的高度，值为'auto'或表示像素的整数 */
            height: 'auto', /* 提示的高度，值为'auto'或表示像素的整数 */
            opacity: 0, /* 窗口隔离层的透明度,如果设置为0,则不显示隔离层 */
            timeout: 2000, /* 提示显示多少毫秒后自动关闭,必须是大于0的整数 */
            closed: function () { } /* 提示关闭后执行的函数 */
        };
        _jBoxConfig.messagerDefaults = {
            content: '', /* 信息的内容，不支持前缀标识 */
            title: 'jBox', /* 信息的标题 */
            icon: 'none', /* 信息图标，值为'none'时为不显示图标，可选值有'none'、'info'、'question'、'success'、'warning'、'error' */
            width: 350, /* 信息的高度，值为'auto'或表示像素的整数 */
            height: 'auto', /* 信息的高度，值为'auto'或表示像素的整数 */
            timeout: 3000, /* 信息显示多少毫秒后自动关闭,如果设置为0,则不自动关闭 */
            showType: 'slide', /* 信息显示的类型,可选值有:show、fade、slide */
            showSpeed: 600, /* 信息显示的速度,可选值有:'slow'、'fast'、表示毫秒的整数 */
            border: 0, /* 信息的外边框像素大小,必须是0以上的整数 */
            buttons: {}, /* 信息的按钮 */
            buttonsFocus: 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
            loaded: function (h) { }, /* 窗口加载完成后执行的函数，参数h表示窗口内容的jQuery对象 */
            submit: function (v, h, f) { return true; }, /* 点击信息按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
            closed: function () { } /* 信息关闭后执行的函数 */
        };
        _jBoxConfig.languageDefaults = {
            close: 'Close', /* 窗口右上角关闭按钮提示 */
            ok: 'Ok', /* $.jBox.prompt() 系列方法的“确定”按钮文字 */
            yes: 'Yes', /* $.jBox.warning() 方法的“是”按钮文字 */
            no: 'No', /* $.jBox.warning() 方法的“否”按钮文字 */
            cancel: 'Cancel' /* $.jBox.confirm() 和 $.jBox.warning() 方法的“取消”按钮文字 */
        };

        $.jBox.setDefaults(_jBoxConfig);

</script>
<script type="text/javascript">
  function active_permisstion(){
	  $('input[type="checkbox"][name="view_permission_ids"]').each(function() {  
		   $(this).attr("disabled", false); 
		  }); 
  }
  function inactive_permisstion(){
	  $('input[type="checkbox"][name="view_permission_ids"]').each(function() {  
		   $(this).attr("disabled", true); 
		  }); 
  }
</script>
	
<title>Publish Informations</title>
</head>
<body class="main">
   
	<div id="mContainer">
	  <form name="contentFm" onsubmit="return checkForm()" method="post" action="${base}/admin/Feedback/save.html" id="contentFm" enctype="multipart/form-data">
		<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td>
					</td>
				</tr>
				<tr id="path">
				<td>
				<div class="path" style=''>
					<div class="menublock" id="menublock">
						<div class="nav">
						<a href="javascript:" class="hover" style="left:0px;z-index:99;width:190px;background:url(${base}/static/image/common/title_hover_200.png)">Publish Information</a>
						<div class="clear"></div>
						</div>
					</div>
					<div class="btnblock" id="btnblock" style="top: 0px">
					</div>
				</div>
				</td>
				</tr>
				<tr>
					<td class="content" id="tdcontent">
						<div class="form" id="formblock">
							<table cellspacing="0" cellpadding="5" width="100%" border="0">
								<tr>
									<td align="left" style="width: 50px">Student Name：</td>
									<td>
									<input name="id" value="${feedbackModel.id }" type="hidden">
									<input name="student_name" value="${FeedbackModel.student_name }" type="text"  maxlength="100" id="student_name_id" class="Line" style="width:284px;height:28px;" />
									</td>
								</tr>
								
								<tr>
									<td align="left" style="width: 50px">Student Type：</td>
									<td>
									<select name="student_type" id=student_type>
									   <option value="">--Please Select--</option>
										<c:forEach items="${listStudentTypeModel }" var="stm" >
									     <option <c:if test="${feedbackModel.student_type==stm.type_id }">selected="selected"</c:if>  value="${stm.type_id }">${stm.type_name }</option>
										</c:forEach>
									</select> 
									</td>
								</tr>

								<tr>
									<td  align="left">Course Type：</td>
									<td align="left">
									<select name="course_type" id="drp_course_type_id">
									   <option value="">--Please Select--</option>
										<c:forEach items="${listCourseTypeModel }" var="ctm" >
									     <option <c:if test="${feedbackModel.course_type==ctm.course_id }">selected="selected"</c:if> value="${ctm.course_id }">${ctm.course_name }</option>
										</c:forEach>
									</select> 
									<span id="span_jspform_top" style="display:none;" class="bottomSolid" style="width:76px;">
										<input id="ckPutTop_id" type="checkbox" name="ckbPutTop" />
										<label for="_ctl0_WorkForm_cbPutTop">Stick</label> 
										<input name="top_level" type="text" id="top_level_id" class="Line" style="width: 40px;" /> Days
									</span></td>
								</tr>
								<tr>
									<td align="left">From City：</td>
									<td>									  
									    <select name="from_city" id="drp_from_city">
									      <option value="">--Please Select--</option>
										     <c:forEach items="${listProvinceModel }" var="pv">
										       <option <c:if test="${feedbackModel.from_city==pv.id }">selected="selected"</c:if> value="${pv.id }">${pv.province_name }</option>
										     </c:forEach>
									    </select>
									</td>
									
									
								</tr>
								
								<tr>
								   <td align="left">Purpose：</td>
								   <td>
								      <input style="width:284px;height:28px;" value="${feedbackModel.purpose }" id="purpose_id" type="text" name="purpose" class="Line"  />
								   </td>
								</tr>
								<tr>
									<td align="left">Study Date：</td>
									<td>
									  <fmt:formatDate var="study_date" value="${feedbackModel.study_date }" pattern="yyyy-MM-dd"/>
									  <input style="width:200px;" type="text" value="${study_date }" id="study_date" name="study_date" data-options="panelWidth:300,height:28" class="easyui-datebox" />
									</td>
								</tr>	
								<tr>
									<td align="left">Interview Date：</td>
									<td>
									<fmt:formatDate var="interview_date" value="${feedbackModel.interview_date }" pattern="yyyy-MM-dd"/>
									  <input style="width:200px;" type="text" value="${interview_date }" id="interview_date" name="interview_date"  data-options="panelWidth:300,height:28"   class="easyui-datebox" />
									 
									     <script>
				                      $('#study_date').datebox({
				          	     	    required:false,
				          	     	    disabled:false,
				          	     	    panelWidth:300,
				          	     	    editable:false,
				          	     	    showSeconds:false,
				          	     	    height:28,
				          	     	    formatter:function(date){
				          	     	    	var y = date.getFullYear();
				             				var m = date.getMonth()+1;
				             				var d = date.getDate();
				             				var h = date.getHours();
				             				var mi = date.getMinutes();
				             				return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
				          	     	    }
				          	     	});
				                      $('#interview_date').datebox({
					          	     	    required:false,
					          	     	    disabled:false,
					          	     	    panelWidth:300,
					          	     	    editable:false,
					          	     	    showSeconds:false,
					          	     	    height:28,
					          	     	    formatter:function(date){
					          	     	    	var y = date.getFullYear();
					             				var m = date.getMonth()+1;
					             				var d = date.getDate();
					             				var h = date.getHours();
					             				var mi = date.getMinutes();
					             				return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d)+' '+(h<10?('0'+h):h)+':'+(mi<10?('0'+mi):mi)+':00';
					          	     	    }
					          	     	});
				                      </script>
									</td>
								</tr>
					
								<tr>
									<td align="left">Title：</td>
									<td>
									<input name="title" type="text" value="${feedbackModel.title }" maxlength="100" style="height:28px;width:482px;font-weight:bold;" id="title_id" class="ruler_line" style="width: 440px;" />
									</td>
								</tr>
							</table>
							
							<!-- Images Upload -->
							<table cellspacing="0" cellpadding="5" width="100%" border="0">
								<tr>
									<td valign="top" colspan="2">
										<div class="tab"
											style="position: relative; margin-bottom: 5px">
											<a href="javascript:" class="hover"><span class="l"></span>
												<span id="images_lblTitle"
												style="padding: 0 10px">Image(Support for 1 picture file)</span> 
												<span class="r"></span></a>
											<div class="clear"></div>
											<div style="position: absolute; right: 0; top: 0;">
												<span id="images_btnUpload">
												  <input class="button" type="file" id="fileUpload_images" name="fileUpload_images"/>
												</span>
											</div>
										</div> 
										<table id="tb_Image_upload_id" class="upload" width="100%" cellpadding="2" cellspacing="0">
                                               <tr id="images_upload_tips_id" bgcolor="#ffffff">
										       <td style="font-size:15px;">Tips：If you want to show profile picture,please select a picture and upload.</td>
										       </tr>
										</table>
									</td>
								</tr>
							</table>
							<!-- Attachments Upload -->
							
							<!-- Vedio Upload -->
							
							<table cellspacing="0" cellpadding="5" width="100%" border="0">
							<tr>
									<td align="left" colspan="2">Content Description：</td>
								</tr>
								<tr>
									<td valign="top" colspan="2" id="text">
									  <textarea name="content" id="txt_content_id" style="height: 350px; width: 100%; word-wrap: break-word; visibility: hidden;">${feedbackModel.content }</textarea>
									</td>
								</tr>
							</table>
							<div id="jspForm_Panel_ShowReplay" style="width: 99%;">
							  
							</div>

						</div>
						
					</td>
				</tr>
			</table>
			<p class="tipbox" id="tipbox" style="display: none;">tip</p>
		</form>
	</div>
	
<% //File upload Setting %>
<!-- uploadify 上传 -->
<script src="${base}/static/js/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${base}/static/js/uploadify/uploadify.css"/>
<script type="text/javascript">

<% //Upload Picture %>
$(function() {
    $("#fileUpload_images").uploadify({
        height : 20,
        width : 100,
        removeCompleted : true,
        removeTimeout:1,
        multi:false,
        queueSizeLimit:2,
        fileSizeLimit:'2MB',
        swf : '${base}/static/js/uploadify/uploadify.swf',
        uploader : '${base}/admin/uploadifyJson.html;jsessionid=<%=session.getId()%>2',
        cancelImg :'${base}/static/js/uploadify/uploadify-cancel.png',
        debug : false,
        fileObjName : 'file',
        buttonText : 'Upload',
        button_image_url:'${base}/static/js/uploadify/uploadify-cancel.png',
        fileTypeExts : '*.jpg;*.jpge;*.gif;*.png;',
        onSelectError :function(file, errorCode, errorMsg) {
        	switch (errorCode){
        	case -100:
        		 //alert("File [" + file.name + "]"+errorMsg);  
                 break; 
        	case -110:  
                //alert("File [" + file.name + "]" + $('#fileUpload_images').uploadify('settings', 'fileSizeLimit') + errorMsg);  
                break;  
            case -120:  
                alert("File [" + file.name + "] "+errorMsg);  
                break;  
            case -130:  
                alert("[" + file.name + "] "+errorMsg);  
                break;     
        	}
        },
        onUploadSuccess :function(file, data, response){
        	var jsondatas=eval("("+data+")");
        	var strHtml ="";
        	function p(s) {
        	   return s < 10 ? '0' + s: s;
        	 }
        	var js_date = new Date();
        	var str_date = p(js_date.getHours())+":"+p(js_date.getMinutes())+":"+p(js_date.getSeconds())+"&nbsp;"+p((js_date.getMonth()+1))+"/"+p(js_date.getDate())+"/"+js_date.getFullYear();
        	
        	$(jsondatas).each(function()
        	{
        		strHtml += '<tr id="file_li_images" bgcolor="#ffffff">';
        		strHtml += '<td style=\"width: 20px;\">';
        		strHtml += '<img src="${base}/static/image/common/pic_default.png"></td>';
        		strHtml += '<td><span id="att_file_name_id"> '+this.fileName+' </span><span style="color:red;">('+this.fileSize+')</span></td>';
        		strHtml += '<td>FileName：<input style="width:250px;" type="text" name="attachment_name" value="'+file.name+'"></td>';
        		strHtml += '<td style="width: 300px;"><label class="upload_label" style="width: 160px;"></label> <span class="swfuploadprogress">100%</span></td>';
        		strHtml += '<td style="width: 150px;">'+str_date+'</td>';
        		strHtml += '<td style="width: 20px;"><img id="'+this.fileName+'" class="del" onclick="delFile(this)"" src="${base}/static/image/delete.gif">';
        		strHtml += '<input type="hidden" name="profile_img" value="'+this.filePath+'">';
        		strHtml += '</td>';
        		strHtml += '</tr>';
           });
        	$("#images_upload_tips_id").html("")
        	$("#tb_Image_upload_id").append(strHtml);
        	
        }  
    });
    
});


<% //Upload Vedio %>


</script>

<% //Submit Form Validate %>
<script type="text/javascript">
$(document).ready(function(){
	  //$("#title_id").focus();
});

function student_name_id(){
	var student_name_id =$("#student_name_id").val();  // $("#channel_id").val();
	if( student_name_id.length==0){
		$("#student_name_id").focus();
		jBox.alert("Dear, This field(Name) is required.","Message");
		return false;
	}else{ return true; }
	
}

function check_student_type(){
	   var txt_student_type = $("#student_type").val();
	   if(txt_student_type == ""){
		   $("#student_type").focus();
		   jBox.alert("Dear, This field(Student Type) is required.","Message");
		   return false;
		}else{return true;}
}

function check_course_type_id(){
	var txt_course_type = $("#drp_course_type_id").val();
	if(txt_course_type==""){
		jBox.alert("Dear, This field(Course Type) is required.","Message");
		return false;
	}else{ return true; }
}

function check_drp_from_city(){
	var txt_drp_from_city = $("#drp_from_city").val();
	if(txt_drp_from_city==""){
		jBox.alert("Dear, This field(From City/Province) is required.","Message");
		return false;
	}else{ return true; }
}

function check_txt_purpose(){
	var txt_purpose = $("#purpose_id").val();
	if(txt_purpose==""){
		jBox.alert("Dear, This field(Purpose) is required.","Message");
		return false;
	}else{ return true; }
}


function check_study_date(){
	var txt_study_date = $("input[name='study_date']").val();
	if(txt_study_date==""){
		jBox.alert("This field(Study date 学习开始日期 ) is required.","Message");
		return false;
	}else{ return true; }
}

function check_interview_date(){
	var txt_interview_date = $("input[name='interview_date']").val();
	if(txt_interview_date==""){
		jBox.alert("This field(Interview date 访谈日期 ) is required.","Message");
		return false;
	}else{ return true; }
}


function check_title(){
	var str_title = $("#title_id").val();
	var txt_title =  $.trim(str_title);
	if(txt_title=="" || txt_title==null){
		$("#title_id").focus();
		jBox.alert("Dear, This field(Title) is required.","Message");
		return false;
	}else{ return true; }
}


function check_content(){
	var txt_txt_content_id = $("#txt_content_id").val();
	if(glb_content_value.html().trim()==""){
		jBox.alert("Dear, This field(Content) is required.","Message");
		return false;
	}else{ return true; }
}



function check_sortNumber(){
	   var v_softNumber = $("#sortNumber_id").val();
	   var reg = /^[\d]+$/;
	   if(v_softNumber.length==0){
		   jBox.tip("Dear, This field(sortNumber) is required.");
		   $("#sortNumber_id").focus();
		   return false;
	   }else{
		   if(!reg.test(v_softNumber)){
			   jBox.tip("Dear, This field(sortNumber) must is number.");
			   $("#sortNumber_id").focus();
			   return false;
		   }else{return true;}
	   }
	   
}

function checkForm(){
	   if(student_name_id()==false || check_student_type()==false || check_course_type_id()==false || check_drp_from_city()==false || check_txt_purpose()==false || check_study_date()==false || check_interview_date()==false || check_title()==false || check_content()==false){
		   return false;
	   }else{
		   return true;
	   }
}

</script>


</body>
</html>