package org.me.qrapp.service;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

public interface QrCodeService {

    BufferedImage generateQrCodeImage(String text, int size) throws Exception;

    void generateQrCodeToFile(String text, int size, Path outputFile) throws Exception;
}

