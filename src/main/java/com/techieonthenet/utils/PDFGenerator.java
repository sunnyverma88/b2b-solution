package com.techieonthenet.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;

/**
 * The type Pdf generator.
 */
public class PDFGenerator {

    private static final String OUTPUT_FILE = "test.pdf";
    private static final String UTF_8 = "UTF-8";

    /**
     * Generate pdf.
     *
     * @throws Exception the exception
     */
    public void generatePdf() throws Exception {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/email/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(UTF_8);
        TemplateEngine templateEngineEmail = new TemplateEngine();
        templateEngineEmail.setTemplateResolver(templateResolver);
        Data data = exampleDataForJohnDoe();

        Context context = new Context();
        context.setVariable("data", data);
        try {
            String renderedHtmlContent = templateEngineEmail.process("template", context);
            String xHtml = convertToXhtml(renderedHtmlContent);
            ITextRenderer renderer = new ITextRenderer();
            renderer.getFontResolver().addFont("Code39.ttf", IDENTITY_H, EMBEDDED);
            String baseUrl = FileSystems
                    .getDefault()
                    .getPath("src", "main", "resources" , "templates" , "email")
                    .toUri()
                    .toURL()
                    .toString();
            renderer.setDocumentFromString(xHtml, baseUrl);
            renderer.layout();
            // And finally, we create the PDF:
            OutputStream outputStream = new FileOutputStream(OUTPUT_FILE);
            renderer.createPDF(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Data exampleDataForJohnDoe() {
        Data data = new Data();
        data.setFirstname("John");
        data.setLastname("Doe");
        data.setStreet("Example Street 1");
        data.setZipCode("12345");
        data.setCity("Example City");
        return data;
    }

    private String convertToXhtml(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setInputEncoding(UTF_8);
        tidy.setOutputEncoding(UTF_8);
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString(UTF_8);
    }

    /**
     * The type Data.
     */
    static class Data {
        private String firstname;
        private String lastname;
        private String street;
        private String zipCode;
        private String city;

        /**
         * Gets firstname.
         *
         * @return the firstname
         */
        public String getFirstname() {
            return firstname;
        }

        /**
         * Sets firstname.
         *
         * @param firstname the firstname
         */
        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        /**
         * Gets lastname.
         *
         * @return the lastname
         */
        public String getLastname() {
            return lastname;
        }

        /**
         * Sets lastname.
         *
         * @param lastname the lastname
         */
        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        /**
         * Gets street.
         *
         * @return the street
         */
        public String getStreet() {
            return street;
        }

        /**
         * Sets street.
         *
         * @param street the street
         */
        public void setStreet(String street) {
            this.street = street;
        }

        /**
         * Gets zip code.
         *
         * @return the zip code
         */
        public String getZipCode() {
            return zipCode;
        }

        /**
         * Sets zip code.
         *
         * @param zipCode the zip code
         */
        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        /**
         * Gets city.
         *
         * @return the city
         */
        public String getCity() {
            return city;
        }

        /**
         * Sets city.
         *
         * @param city the city
         */
        public void setCity(String city) {
            this.city = city;
        }
    }

}
