package com.itextpdf7.demo.example.chapter6;/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/*
 * This example is part of the iText 7 tutorial.
 */

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class C06E05_88th_Oscar_CombineXofY {
    public static final String SRC1 = "src/main/resources/static/pdf/88th_reminder_list.pdf";
    public static final String SRC2 = "src/main/resources/static/pdf/88th_noms_announcement.pdf";
    public static final String DEST = "F:/pdftest/chapter06/88th_oscar_combined_documents_xy_pages.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C06E05_88th_Oscar_CombineXofY().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF document with output intent
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        PdfMerger merger = new PdfMerger(pdf);

        //Add pages from the first document
        PdfDocument firstSourcePdf = new PdfDocument(new PdfReader(SRC1));
        merger.merge(firstSourcePdf, Arrays.asList(1, 5, 7, 1));

        //Add pages from the second pdf document
        PdfDocument secondSourcePdf = new PdfDocument(new PdfReader(SRC2));
        merger.merge(secondSourcePdf, Arrays.asList(1, 15));

        firstSourcePdf.close();
        secondSourcePdf.close();
        pdf.close();
    }
}

