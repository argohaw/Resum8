package com.sriram.Resum8.util;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class CommonUtil
{

    public static String extractTextFromPdf(MultipartFile pdfFile) throws IOException, TikaException
    {
        Tika tika = new Tika();
        byte[] pdfBytes = pdfFile.getBytes();
        String text = tika.parseToString(new ByteArrayInputStream(pdfBytes));
        return text;
    }
}
