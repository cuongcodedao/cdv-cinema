package com.project.cdv_cinema.DataGenerate;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class QrCodeGenerator {

    private static String imageDir = "images/";


    public static String QRCodeGeneratorAndSaving(String qrContent, Long bookingId){
        String filePath = bookingId + ".png";
        int width = 300;
        int height = 300;

        try {
            Path imagePath = Paths.get(imageDir+"/qrcode");

            if (!Files.exists(imagePath)) {
                Files.createDirectories(imagePath);
            }
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            // Generate the QR Code
            BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, width, height);

            // Save it as a file
            Path path = imagePath.resolve(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            return path.toString();
        } catch (WriterException | IOException e) {
            System.err.println("Error generating QR Code: " + e.getMessage());
        }
        return null;
    }
}
