package data;


import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.Line;
import world.Point;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class DataReader {

    private static Logger log = LoggerFactory.getLogger(DataReader.class);

    public static Set load(String path) throws IOException {
        HashSet<Line> objects = new HashSet<>();
        JSONArray parser = new JSONArray(Files.readString(Path.of(path), StandardCharsets.UTF_8));
        parser.forEach(lines -> {
            JSONArray line = (JSONArray) lines;
            for(var l :line) {
                JSONObject points = (JSONObject) ((JSONObject) l).get("line") ;
                Point p1 = new Point(points.getDouble("x1"), points.getDouble("y1"), points.getDouble("z1"));
                Point p2 = new Point(points.getDouble("x2"), points.getDouble("y2"), points.getDouble("z2"));
                objects.add(new Line(p1, p2));
            }
        });
        System.out.println("Size "+objects.size());
        return objects;
    }

    public static void main(String[] args) throws IOException {
        Set<Line> e = load("/Users/Kasia/Documents/projects/virtual-camera/program/src/main/resources/json_files/data1.json");
        System.out.println(e.toString());
    }


}
