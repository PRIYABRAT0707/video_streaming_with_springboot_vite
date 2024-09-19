package com.lernercurve.course.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class VideoUploadLocation {
    @Id
    private String locationId;
    private String username;
    private LocalDateTime uploadedDateTime;
    private double longitude;
    private double latitude;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String placeName;
    private String description;
    private double accuracy;
    private double altitude;
    private String timezone;
    private List<String> photos;
    private List<String> categories;
    private String type;
    private double rating;
    private List<String> reviews;
    private String contactNumber;
    private String website;
    private String openingHours;
    private String closingHours;
    private boolean isPublic;
    private String geoJson;
    private double userRating;
    private boolean verified;
    private String category;
    private List<String> tags;

}
