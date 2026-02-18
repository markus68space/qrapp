package org.me.qrapp.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QrCodeServiceImpl implements QrCodeService {

    @Override
    public BufferedImage generateQrCodeImage(String text, int size) throws Exception {

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); // Medium
        hints.put(EncodeHintType.MARGIN, 1); // Small border

        BitMatrix matrix = new MultiFormatWriter().encode(
                text,
                BarcodeFormat.QR_CODE,
                size,
                size,
                hints
        );

        return MatrixToImageWriter.toBufferedImage(matrix);
    }

    @Override
    public void generateQrCodeToFile(String text, int size, Path outputFile) throws Exception {

        BufferedImage image = generateQrCodeImage(text, size);

        String format = getFormat(outputFile.toString()); // png, jpg, etc.

        javax.imageio.ImageIO.write(image, format, outputFile.toFile());
    }

    private String getFormat(String filename) {
        int dot = filename.lastIndexOf('.');
        return (dot == -1) ? "png" : filename.substring(dot + 1);
    }
}

