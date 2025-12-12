package com.api.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GlobalData {
    public static final int priorityLast = 10;
    public static final boolean enabled = true;
    public static Integer locationId;
    public static List<Integer> locationIds = new ArrayList<>();
    public static Integer characterId;
    public static List<Integer> characterIds = new ArrayList<>();
    public static Integer episodeId;
    public static List<Integer> episodeIds = new ArrayList<>();

    public static List<String> bookIds = new ArrayList<>();

    public static String authToken;
    public static String userId;
}
