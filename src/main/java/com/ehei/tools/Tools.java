package com.ehei.tools;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.HOURS;

public final class Tools {
    private static final Logger logger = LogManager.getLogger(Tools.class);

    public Tools() throws InstantiationException {
        throw new InstantiationException("YOU CANNOT INSTANTIATE THIS CLASS, use getInstance()");
    }


    public static void log(Level level, String message) {
        logger.log(level, message);
    }


    public static String toMd5(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] inputBytes = value.getBytes();
            byte[] hashBytes = md.digest(inputBytes);
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            log(Level.ERROR, e.getMessage());
            return null;
        }
    }

    public static long getHourDifference(LocalDateTime startDate, LocalDateTime endDate) {
        return HOURS.between(endDate, startDate);
    }

    public static String saveFile(HttpServlet servlet, HttpServletRequest request, String file) throws ServletException, IOException {
        Part filePart = request.getPart(file); // Récupère le fichier envoyé
        String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        String newFileName = UUID.randomUUID() + "_" + originalFileName;

        String uploadPath = servlet.getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadsDir = new File(uploadPath);
        if (!uploadsDir.exists()) {
            uploadsDir.mkdir();
        }

        File fileToSave = new File(uploadsDir, newFileName);
        System.out.println("Enregistrement du fichier à : " + uploadPath + File.separator + newFileName);
        filePart.write(fileToSave.getAbsolutePath());

        return newFileName;
    }


}

