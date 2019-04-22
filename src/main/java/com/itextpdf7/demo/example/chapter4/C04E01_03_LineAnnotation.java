package com.itextpdf7.demo.example.chapter4;/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/*
 * This example is part of the iText 7 tutorial.
 */

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfLineAnnotation;
import java.io.File;
import java.io.IOException;

/**
 * Simple line annotation example.
 */
public class C04E01_03_LineAnnotation {

    public static final String DEST = "F:/pdftest/chapter04/line_annotation2.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C04E01_03_LineAnnotation().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
        PdfPage page = pdf.addNewPage();

        PdfArray lineEndings = new PdfArray();
        lineEndings.add(new PdfName("Diamond"));
        lineEndings.add(new PdfName("Diamond"));

        //Create line annotation with inside caption
        PdfAnnotation annotation = new PdfLineAnnotation(
            new Rectangle(0, 0),
            new float[]{20, 790, page.getPageSize().getWidth() - 20, 790})
                .setLineEndingStyles((lineEndings))
                .setContentsAsCaption(true)
                .setTitle(new PdfString("iText"))
                .setContents("The example of line annotation")
                .setColor(ColorConstants.BLUE);
        page.addAnnotation(annotation);

        //Close document
        pdf.close();

    }
}
