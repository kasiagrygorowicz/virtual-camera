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

        JSONObject parser = new JSONObject(Files.readString(Path.of(path), StandardCharsets.UTF_8));

        JSONArray figures = parser.getJSONArray("figure");

        figures.forEach(f -> {
            JSONArray lines = ((JSONObject) f).getJSONArray("lines");
            lines.forEach(l -> {
                JSONObject line = (JSONObject) l;

                Point p1 = new Point(line.getDouble("x"), line.getDouble("y"), line.getDouble("z"));
                Point p2 = new Point(line.getDouble("x"), line.getDouble("y"), line.getDouble("z"));

                objects.add(new Line(p1, p2));
            });
        });

        return objects;
    }

    public static void main(String[] args) throws IOException {
        Set<Line> e = load("/Users/Kasia/Documents/projects/virtual-camera/program/src/main/resources/json_files/data1.json");
        System.out.println(e.toString());
    }


}
