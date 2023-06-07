package kz.iitu.pcsystem.util;

import kz.iitu.pcsystem.entity.ComponentEntity;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Util {
    public static void mapBooleanField(Map<String, String> map, String fieldName) {
        String field = map.get(fieldName);
        if (field != null) {
            if (field.equals("Нет")) map.put(fieldName, "false");
            else map.put(fieldName, "true");
        }
    }

    public static void serveComponentImage(ComponentEntity component, byte[] image) {
        System.out.println(component.getImageUri());
        if (!component.getImageUri().contains("shop.kz")) {
            System.out.println("continue");
            // already saved to resource folder and imageUri is ours
            return;
        }

        System.out.println("serving image");

        String imageFileName = component.getId().replaceAll("\\s", "_")
                .replaceAll("\\\\", "")
                .replaceAll("/", "") +
                "." + FilenameUtils.getExtension(component.getImageUri());
        Path resourceDirectory = Paths.get("src", "main", "resources", "static", "images", imageFileName);
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        String motherboardImagePath = absolutePath;
        try (FileOutputStream fos = new FileOutputStream(motherboardImagePath)) {
            fos.write(image);
        } catch (IOException e) {
            throw new RuntimeException("Could not write file to " + motherboardImagePath, e);
        }

        component.setImageUri("/static/images/" + component.getId()); // update shop.kz image uri to ours
    }
}
