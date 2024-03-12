package com.mycompany.app;
import java.util.Scanner;


import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;


import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;



import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{

    public static double computeAverageDistance(List<Integer> points1, List<Integer> points2, int threshold1, int threshold2) {

        if (points1.size() != points2.size()) {
            throw new IllegalArgumentException("Arrays must have the same length");
        }

        int totalDistance = 0;
        int count = 0;
        for (int i = 0; i < points1.size(); i++) {
            if (points1.get(i) >= threshold1 && points2.get(i) >= threshold2) {
                totalDistance += Math.abs(points1.get(i) - points2.get(i));
                count++;
            }
        }

        if (count == 0) {
            // Handle the case where no elements meet the thresholds
            return 0.0;
        }

        return (double) totalDistance / count;
    }

    // Example usage
    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) -> {
            // İlk liste için parametreleri ayrıştır
            String input1 = req.queryParams("input1");
            Scanner sc1 = new Scanner(input1);
            sc1.useDelimiter("[;\r\n]+");
            ArrayList<Integer> inputList1 = new ArrayList<>();
            while (sc1.hasNext()) {
                int value = Integer.parseInt(sc1.next().replaceAll("\\s", ""));
                inputList1.add(value);
            }
            sc1.close();

            // İkinci liste için parametreleri ayrıştır
            String input2 = req.queryParams("input2");
            Scanner sc2 = new Scanner(input2);
            sc2.useDelimiter("[;\r\n]+");
            ArrayList<Integer> inputList2 = new ArrayList<>();
            while (sc2.hasNext()) {
                int value = Integer.parseInt(sc2.next().replaceAll("\\s", ""));
                inputList2.add(value);
            }
            sc2.close();

            // Eşik değerlerini ayrıştır
            int threshold1 = Integer.parseInt(req.queryParams("threshold1"));
            int threshold2 = Integer.parseInt(req.queryParams("threshold2"));

            // computeAverageDistance metodunu çağır
            double result = App.computeAverageDistance(inputList1, inputList2, threshold1, threshold2);

            Map map = new HashMap();
            map.put("result", result);
            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());



        get("/compute",
                (rq, rs) -> {
                    Map map = new HashMap();
                    map.put("result", "not computed yet!");
                    return new ModelAndView(map, "compute.mustache");
                },
                new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}

