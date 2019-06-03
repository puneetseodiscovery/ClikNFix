package com.cliknfix.user.responseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TechDetailResponseModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("email_verified_at")
        @Expose
        private Object emailVerifiedAt;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("adhar_card_no")
        @Expose
        private String adharCardNo;
        @SerializedName("pan_card")
        @Expose
        private String panCard;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("documents")
        @Expose
        private String documents;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("remember_token")
        @Expose
        private String rememberToken;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("adhar_card_document")
        @Expose
        private String adharCardDocument;
        @SerializedName("pan_card_document")
        @Expose
        private String panCardDocument;
        @SerializedName("police_verification")
        @Expose
        private String policeVerification;
        @SerializedName("address_proof")
        @Expose
        private String addressProof;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("login_status")
        @Expose
        private String loginStatus;
        @SerializedName("service_category")
        @Expose
        private String serviceCategory;
        @SerializedName("availability")
        @Expose
        private String availability;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("lng")
        @Expose
        private String lng;
        @SerializedName("device_token")
        @Expose
        private String deviceToken;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getEmailVerifiedAt() {
            return emailVerifiedAt;
        }

        public void setEmailVerifiedAt(Object emailVerifiedAt) {
            this.emailVerifiedAt = emailVerifiedAt;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAdharCardNo() {
            return adharCardNo;
        }

        public void setAdharCardNo(String adharCardNo) {
            this.adharCardNo = adharCardNo;
        }

        public String getPanCard() {
            return panCard;
        }

        public void setPanCard(String panCard) {
            this.panCard = panCard;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDocuments() {
            return documents;
        }

        public void setDocuments(String documents) {
            this.documents = documents;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getRememberToken() {
            return rememberToken;
        }

        public void setRememberToken(String rememberToken) {
            this.rememberToken = rememberToken;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getAdharCardDocument() {
            return adharCardDocument;
        }

        public void setAdharCardDocument(String adharCardDocument) {
            this.adharCardDocument = adharCardDocument;
        }

        public String getPanCardDocument() {
            return panCardDocument;
        }

        public void setPanCardDocument(String panCardDocument) {
            this.panCardDocument = panCardDocument;
        }

        public String getPoliceVerification() {
            return policeVerification;
        }

        public void setPoliceVerification(String policeVerification) {
            this.policeVerification = policeVerification;
        }

        public String getAddressProof() {
            return addressProof;
        }

        public void setAddressProof(String addressProof) {
            this.addressProof = addressProof;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLoginStatus() {
            return loginStatus;
        }

        public void setLoginStatus(String loginStatus) {
            this.loginStatus = loginStatus;
        }

        public String getServiceCategory() {
            return serviceCategory;
        }

        public void setServiceCategory(String serviceCategory) {
            this.serviceCategory = serviceCategory;
        }

        public String getAvailability() {
            return availability;
        }

        public void setAvailability(String availability) {
            this.availability = availability;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }

    }

}
