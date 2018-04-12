<%@page import="util.core.MethodUtil" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../path.jsp" %>
    <meta id="QQEnglish_EmulateIE7" http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <%@ include file="../Common/headerInlineCss.jsp" %>
    <%@ include file="../Common/jsCssResources.jsp" %>

    <script type="text/javascript">
        //Add and Edit
        function feedbackGridAddAndEdit(title, url, type) {
            if (type == 1) { //edit
                var rows = $('#companyEventsGrid').datagrid('getSelections');
                var editview_url = "admin/Feedback/editView.html?feedbackId=" + title;
                parent.window.document.getElementById("main").contentWindow.location.href = "${base}/admin/elements/loading.html?action_url=" + encodeURIComponent(editview_url);
            } else {
                var newView_url = "${base}/admin/Feedback/addView.html?returnUrl=${base}/admin/Feedback/index.html?spm=${spm}";
                parent.window.document.getElementById("main").contentWindow.location.href = "${base}/admin/elements/loading.html?action_url=" + encodeURIComponent(newView_url);
            }
        }

        //Del
        function feedbackGridDel() {
            var rows = $('#companyEventsGrid').datagrid('getSelections');
            if (rows.length > 0) {
                var ids = '';
                for (var i = 0; i < rows.length; i++) {
                    ids += 'ids=' + rows[i].id + '&';
                }
                ids = ids.substring(0, ids.length - 1);
                var url = '${base}/admin/Feedback/del.html?' + ids;
                $.messager.confirm('Confirm', 'Are you sure want to delete?', function (r) {
                    if (r) {
                        $.get(url, function (result) {
                            if (result.success) {
                                $('#companyEventsGrid').datagrid('reload');
                                $('#companyEventsGrid').datagrid('clearSelections');
                            } else {
                                $.messager.show({title: 'Error', msg: result.msg});
                            }
                        }, 'json');
                    }
                });
            } else {
                $.messager.alert('Message', 'Please select a row first!', 'info');
            }
        }

        //Reload
        function feedbackGridReload() {
            $('#companyEventsGrid').datagrid('options').pageNumber = 1;
            $('#companyEventsGrid').datagrid('reload', {});
        }

        //companyEventsGridSubmit  submit
        function companyEventsGridSubmit(url) {
            $('#contentExtFm').form('submit', {
                url: url,
                onSubmit: function () {
                    return $(this).form('validate');
                },
                success: function (result) {
                    var result = eval('(' + result + ')');
                    if (result.success) {
                        $('#contentExtDlg').dialog('close');      // close the dialog
                        $('#companyEventsGrid').datagrid('reload');    // reload the user data
                    } else {
                        $.messager.show({title: 'Error', msg: result.msg});
                    }
                }
            });
        }

        //高级搜索 del row
        function contentExtSearchRemove(curr) {
            if ($(curr).closest('table').find('tr').size() > 2) {
                $(curr).closest('tr').remove();
            } else {
                alert('This row can not be deleted!');
            }
        }

        //高级查询
        function contentExtSearch() {
            $('<div/>').dialog({
                href: '${base}/admin/ContentExt/searchDlg.html',
                modal: true,
                title: 'Advanced Search',
                top: 120,
                width: 680,
                buttons: [{
                    text: 'Add a row',
                    iconCls: 'icon-add',
                    handler: function () {
                        var currObj = $(this).closest('.panel').find('table');
                        currObj.find('tr:last').clone().appendTo(currObj);
                        //currObj.find('tr:last td *[disabled]').removeAttr("disabled");
                    }
                }, {
                    text: 'Okay',
                    iconCls: 'icon-ok',
                    handler: function () {
                        $('#companyEventsGrid').datagrid('options').pageNumber = 1;
                        $('#companyEventsGrid').datagrid('reload', serializeObjectEx($('#contentExtSearchFm')));
                    }
                }, {
                    text: 'Cancel',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        $(this).closest('.window-body').dialog('destroy');
                    }
                }],
                onClose: function () {
                    $(this).dialog('destroy');
                }
            });
        }

        // Search
        function contentSearch_input(fm, grid, type) {
            var gd = $('#' + grid);
            gd.datagrid('options').pageNumber = 1;
            var obj = $('#' + fm).serializeJson();
            obj['searchType'] = type;
            gd.datagrid({
                queryParams: obj
            });
        }


        //clear Form
        function contentFormRest(fm, method) {
            $('#' + fm).form(method);
        }

    </script>
    <title>Publish Feedback</title>
</head>
<body class="main">
<div id="mContainer">
    <table cellpadding="0" cellspacing="0" width="100%">
        <tr>
            <td>
            </td>
        </tr>
        <tr id="path">
            <td>
                <div class="path">
                    <div class="menublock" id="menublock">
                        <div class="nav">
                            <a href="${base}/admin/elements/loading.html?action_url=${base}/admin/Feedback/index.html?parm=${spm}"
                               class="hover"
                               style="left:0px;z-index:99;background: url(${base}/static/image/common/title_hover_200.png);width:190px;">
                                Feedback</a>
                            <div class="clear"></div>
                        </div>
                    </div>
                    <div class="btnblock" id="btnblock" style="top: 0px">
                        <c:forEach items="${buttons}" var="button">
                            ${button}
                        </c:forEach>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td class="content" id="tdcontent">
                <div class="form" id="formblock">
                    <form id="contentSearchFmBase" method="post">
                        <table cellspacing="0" cellpadding="2" width="100%" border="0">
                            <%-- First Search --%>
                            <tr>
                                <td style="width: 70px">
                                    Name：
                                </td>
                                <td>
                                    <input name="student_name" type="text" style="width:200px" class="Line"/>
                                </td>
                                <td style="width: 70px">
                                    From City：
                                </td>
                                <td>
                                    <select name="from_city" style="width:185px;">
                                        <option value="">--Please Select--</option>
                                        <c:forEach items="${listProvinceModel }" var="pv">
                                            <option value="${pv.id }">${pv.province_name }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td style="width: 70px">
                                    Date：
                                </td>
                                <td>
                                    <input name="start_date" class="easyui-datebox"
                                           data-options="panelWidth:245,editable:false" id="start_date_id" type="text"
                                           style="height: 28px;width:140px" class="Line"/>
                                    - <input name="end_date" class="easyui-datebox"
                                             data-options="panelWidth:245,editable:false" id="end_date_id" type="text"
                                             style="height: 28px;width:140px" class="Line"/>
                                </td>
                            </tr>
                            <%-- Second Search --%>
                            <tr>
                                <td style="width: 70px">
                                    Keywords：
                                </td>
                                <td>
                                    <input name="content" type="text" style="width:200px" id="content" class="Line"/>
                                </td>
                                <td style="width: 90px">
                                    Student Type：
                                </td>
                                <td>
                                    <select name="student_type" id="student_type" style="width:185px;">
                                        <option value="">--Please Select--</option>
                                        <c:forEach items="${listStudentTypeModel }" var="st">
                                            <option value="${st.type_id }">${st.type_name }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td align="center" colspan="2">
                                    <input type="button"
                                           onclick="contentSearch_input('contentSearchFmBase','companyEventsGrid',1)"
                                           name="btn_search" value="Search" id="btn_search"
                                           class="button4 btn_bg_orange"/>
                                    <input type="button" onclick="contentFormRest('contentSearchFmBase','reset')"
                                           name="btn_search" value="Clear" id="btn_reset" class="button4"/>
                                    <input type="button"
                                           onclick="javascript:window.open('http://www2.kuaikuenglish.com/feedback/lists')"
                                           value="View List" class="button4"/>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <!-- DataGrid Start -->
                <div class="grid">
                    <div data-options="region:'center',border : false">
                        <!-- datagrid toolbar -->
                        <table id="companyEventsGrid" class="easyui-datagrid" data-options="singleSelect: false,
                            url:'${base}/admin/Feedback/data.html',idField:'id',striped: true,
                            frozenColumns : [ [ {field : 'ck',checkbox : true}] ],
                            columns:[ [

                            {field:'id',title:'Operations',hidden:false,width:'64',halign:'center',align:'center',sortable:'false', formatter: rowformatter},

                            {field:'student_name',title:'Student Name',hidden:false,width:'160',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
                                return value;
                            }},

                            {field:'provinceModel.province_name',title:'From City',hidden:false,width:'135',halign:'center',align:'center',sortable:'false', formatter: function(value,row,index){
                                return value;
                            }},

                            {field:'studentTypeModel.type_name',title:'Type of Student',hidden:false,width:'135',halign:'center',align:'center',sortable:'false', formatter: function(value,row,index){
                                return value;
                            }},

                            {field:'purpose',title:'Purpose',hidden:true,width:'335',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
                                return value;
                            }},

                            {field:'courseTypeModel.cource_name',title:'Type of Course',hidden:false,width:'200',halign:'center',align:'center',sortable:'false', formatter: function(value,row,index){
                                return value;
                            }},

                            {field:'countryModel.country_name',title:'Country from',hidden:false,width:'80',halign:'center',align:'center',sortable:'false', formatter: function(value,row,index){
                                return value;
                            }},

                            {field:'agenciesModel.simple_name',title:'Agency',hidden:false,width:'150',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
                                return value;
                            }},

                            {field:'createTime',title:'Created Time',hidden:false,width:'135',halign:'center',align:'center',sortable:'true', formatter: function(value,row,index){
                                return value;
                            }}
                            ] ],
                            toolbar:'#companyEventsGridToolbar'
                        "/>

                    </div>
                </div>
                <!-- DataGrid End -->
            </td>
        </tr>
    </table>
    <p class="tipbox" id="tipbox" style="display: none"></p>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#label_link_url_id").hide();
        $('#channel_list_id').combotree({
            multiple: false,
            url: '${base}/admin/Channel/comboxTree.html?t=' + Math.random()
        });
    });

    //DataGrid Formatter
    function rowformatter(value, row, index) {
        return "<a target=\"_black\" href=\"http://www2.kuaikuenglish.com/feedback/details/" + value + "\"><img title=ViewDetail border=0 src=\"${base}/static/image/common/view.gif\"></a>&nbsp;<a onclick=\"javascript:feedbackGridAddAndEdit('" + value + "','','1')\" href=\"javascript:void(0);\"><img border=\"0\" src=\"${base}/static/image/common/edit.gif\"></a>";
    }

</script>
</body>
</html>