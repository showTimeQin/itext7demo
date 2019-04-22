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

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;

public class C06E09_FillOutFlattenAndMergeForms {
    public static final String DEST1 = "F:/pdftest/chapter06/fill_out_flatten_forms_merge.pdf";
    public static final String DEST2 = "F:/pdftest/chapter06/fill_out_flatten_forms_smart_merge.pdf";
    public static final String SRC = "src/main/resources/static/pdf/state.pdf";
    public static final String DATA = "src/main/resources/static/data/united_states.csv";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST1);
        file.getParentFile().mkdirs();
        file = new File(DEST2);
        file.getParentFile().mkdirs();
        new C06E09_FillOutFlattenAndMergeForms().createPdf(DEST1, DEST2);
    }

    public void createPdf(String dest1, String dest2) throws IOException {
        PdfDocument destPdfDocument = new PdfDocument(new PdfWriter(dest1));
        //Smart mode
        PdfDocument destPdfDocumentSmartMode = new PdfDocument(new PdfWriter(dest2).setSmartMode(true));

        BufferedReader bufferedReader = new BufferedReader(new FileReader(DATA));
        String line;
        boolean headerLine = true;
        while ((line = bufferedReader.readLine()) != null) {
            if (headerLine) {
                headerLine = false;
                continue;
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfDocument sourcePdfDocument = new PdfDocument(new PdfReader(SRC), new PdfWriter(baos));

            //Read fields
            PdfAcroForm form = PdfAcroForm.getAcroForm(sourcePdfDocument, true);
            StringTokenizer tokenizer = new StringTokenizer(line, ";");
            Map<String, PdfFormField> fields = form.getFormFields();

            //Fill out fields
            fields.get("name").setValue(tokenizer.nextToken());
            fields.get("abbr").setValue(tokenizer.nextToken());
            fields.get("capital").setValue(tokenizer.nextToken());
            fields.get("city").setValue(tokenizer.nextToken());
            fields.get("population").setValue(tokenizer.nextToken());
            fields.get("surface").setValue(tokenizer.nextToken());
            fields.get("timezone1").setValue(tokenizer.nextToken());
            fields.get("timezone2").setValue(tokenizer.nextToken());
            fields.get("dst").setValue(tokenizer.nextToken());

            //Flatten fields
            form.flattenFields();

            sourcePdfDocument.close();
            sourcePdfDocument = new PdfDocument(new PdfReader(new ByteArrayInputStream(baos.toByteArray())));

            //Copy pages
            sourcePdfDocument.copyPagesTo(1, sourcePdfDocument.getNumberOfPages(), destPdfDocument, null);
            sourcePdfDocument.copyPagesTo(1, sourcePdfDocument.getNumberOfPages(), destPdfDocumentSmartMode, null);

            sourcePdfDocument.close();
        }

        bufferedReader.close();

        destPdfDocument.close();
        destPdfDocumentSmartMode.close();
    }
}