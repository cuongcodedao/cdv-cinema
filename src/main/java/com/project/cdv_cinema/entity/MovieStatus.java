package com.project.cdv_cinema.entity;

public enum MovieStatus {
    NOW_SHOWING("Đang chiếu"),
    COMING_SOON("Sắp chiếu"),
    FINISHED("Đã chiếu");

    private final String displayName;

    MovieStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
