package com.example.mediplus.vaccineslottracker.beans;

import java.util.List;

public class Session {

    private Long center_id;
    private String name;
    private String address;
    private String state_name;
    private String district_name;
    private String block_name;
    private Long pincode;
    private String from;
    private String to;
    private int lat;
    private String fee_type;
    private String session_id;
    private String date;
    private int available_capacity;
    private String fee;
    private int min_age_limit;
    private String vaccine;
    private List<String> slots;

    public Long getCenter_id() {
        return center_id;
    }

    public void setCenter_id(Long center_id) {
        this.center_id = center_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getBlock_name() {
        return block_name;
    }

    public void setBlock_name(String block_name) {
        this.block_name = block_name;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAvailable_capacity() {
        return available_capacity;
    }

    public void setAvailable_capacity(int available_capacity) {
        this.available_capacity = available_capacity;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public int getMin_age_limit() {
        return min_age_limit;
    }

    public void setMin_age_limit(int min_age_limit) {
        this.min_age_limit = min_age_limit;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public List<String> getSlots() {
        return slots;
    }

    public void setSlots(List<String> slots) {
        this.slots = slots;
    }

    @Override
    public String toString() {
        return "Session{" +
                "center_id=" + center_id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", state_name='" + state_name + '\'' +
                ", district_name='" + district_name + '\'' +
                ", block_name='" + block_name + '\'' +
                ", pincode=" + pincode +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", lat=" + lat +
                ", fee_type='" + fee_type + '\'' +
                ", session_id='" + session_id + '\'' +
                ", date='" + date + '\'' +
                ", available_capacity=" + available_capacity +
                ", fee='" + fee + '\'' +
                ", min_age_limit=" + min_age_limit +
                ", vaccine='" + vaccine + '\'' +
                ", slots=" + slots +
                '}';
    }
}
