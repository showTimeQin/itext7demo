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

import com.itextpdf.forms.PdfPageFormCopier;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.File;
import java.io.IOException;

public class C06E07_Combine_Forms {
    public static final String DEST = "F:/pdftest/chapter06/combined_forms.pdf";
    public static final String SRC1 = "src/main/resources/static/pdf/subscribe.pdf";
    public static final String SRC2 = "src/main/resources/static/pdf/state.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C06E07_Combine_Forms().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        PdfDocument destPdfDocument = new PdfDocument(new PdfWriter(dest));
        PdfDocument[] sources = new PdfDocument[] {
                new PdfDocument(new PdfReader(SRC1)),
                new PdfDocument(new PdfReader(SRC2))
        };
        PdfPageFormCopier formCopier = new PdfPageFormCopier();
        for (PdfDocument sourcePdfDocument : sources) {
            sourcePdfDocument.copyPagesTo(
                    1, sourcePdfDocument.getNumberOfPages(),
                    destPdfDocument, formCopier);
            sourcePdfDocument.close();
        }
        destPdfDocument.close();
    }
}