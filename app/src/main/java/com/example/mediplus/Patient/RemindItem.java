package com.example.mediplus.Patient;

public class RemindItem {
    String name, type, dosage, repeat, imageResource;
    int remind_hour, remind_minute;

    public RemindItem(String name, String type, String dosage, String repeat, int reminder_hour, int reminder_minute, String imageResource) {
        this.name = name;
        this.type = type;
        this.dosage = dosage;
        this.remind_hour = reminder_hour;
        this.remind_minute = reminder_minute;
        this.imageResource = imageResource;
        this.repeat = repeat;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDosage() {
        return dosage;
    }

    public String getRepeat() {
        return repeat;
    }

    public int getRemindHour() {
        return remind_hour;
    }

    public int getRemindMinute() {
        return remind_minute;
    }

    public String getImageResource() {
        return imageResource;
    }
}
