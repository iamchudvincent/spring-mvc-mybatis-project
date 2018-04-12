package model;

import java.sql.Timestamp;

public class FeedbackModel extends BaseModel {

    /**
     * Feedback Model
     */

    private String id;
    private String student_name;
    private String from_city;
    private String student_type;
    private String purpose;
    private String course_type;
    private java.sql.Timestamp study_date;
    private java.sql.Timestamp interview_date;
    private String profile_img;
    private String title;
    private String content;
    private java.sql.Timestamp createTime;
    private String country_id;
    private String agency_id;

    private StudentTypeModel studentTypeModel;
    private CourseTypeModel courseTypeModel;
    private ProvinceModel provinceModel;
    private CountryModel countryModel;
    private AgenciesModel agenciesModel;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getFrom_city() {
        return from_city;
    }

    public void setFrom_city(String from_city) {
        this.from_city = from_city;
    }

    public String getStudent_type() {
        return student_type;
    }

    public void setStudent_type(String student_type) {
        this.student_type = student_type;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCourse_type() {
        return course_type;
    }

    public void setCourse_type(String course_type) {
        this.course_type = course_type;
    }

    @com.alibaba.fastjson.annotation.JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public java.sql.Timestamp getStudy_date() {
        return study_date;
    }

    public void setStudy_date(java.sql.Timestamp study_date) {
        this.study_date = study_date;
    }

    @com.alibaba.fastjson.annotation.JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public java.sql.Timestamp getInterview_date() {
        return interview_date;
    }

    public void setInterview_date(java.sql.Timestamp interview_date) {
        this.interview_date = interview_date;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StudentTypeModel getStudentTypeModel() {
        return studentTypeModel;
    }

    public void setStudentTypeModel(StudentTypeModel studentTypeModel) {
        this.studentTypeModel = studentTypeModel;
    }

    public CourseTypeModel getCourseTypeModel() {
        return courseTypeModel;
    }

    public void setCourseTypeModel(CourseTypeModel courseTypeModel) {
        this.courseTypeModel = courseTypeModel;
    }

    public ProvinceModel getProvinceModel() {
        return provinceModel;
    }

    public void setProvinceModel(ProvinceModel provinceModel) {
        this.provinceModel = provinceModel;
    }

    public String toString() {
        return com.alibaba.fastjson.JSON.toJSONString(this);
    }

    @com.alibaba.fastjson.annotation.JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }

    public CountryModel getCountryModel() {
        return countryModel;
    }

    public void setCountryModel(CountryModel countryModel) {
        this.countryModel = countryModel;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public AgenciesModel getAgenciesModel() {
        return agenciesModel;
    }

    public void setAgenciesModel(AgenciesModel agenciesModel) {
        this.agenciesModel = agenciesModel;
    }

    public String getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(String agency_id) {
        this.agency_id = agency_id;
    }


}
