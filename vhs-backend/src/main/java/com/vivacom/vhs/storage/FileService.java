package com.vivacom.vhs.storage;


import com.vivacom.vhs.constants.FileType;
import com.vivacom.vhs.constants.Status;
import com.vivacom.vhs.utils.StatusDto;
import com.vivacom.vhs.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

@Slf4j
@Service
public class FileService {

    public StatusDto uploadImage(MultipartFile imageFile) {
        StatusDto response = new StatusDto();
        String directory = "static/images/";
        if (imageFile.isEmpty()) {
            response.setResult("ERROR");
            response.setMessage("FIle not found.");
            return response;
        }
        if (!imageFile.getContentType().equals("image/jpeg")) {
            response.setResult("ERROR");
            response.setMessage("File is not in JPG format");
            return response;
        }
        try {
            Files.copy(imageFile.getInputStream(),
                    Paths.get(directory + File.separator + imageFile.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setResult("SUCCESS");
        return response;
    }

    public StatusDto uploadImage2(MultipartFile imageFile) {
        StatusDto response = new StatusDto();
        String directory = "static/images/";
        String fileLocation = new File(directory).getAbsolutePath();
        if (imageFile.isEmpty()) {
            response.setResult("ERROR");
            response.setMessage("FIle not found.");
            return response;
        }
        if (!imageFile.getContentType().equals("image/jpeg")) {
            response.setResult("ERROR");
            response.setMessage("File is not in JPG format");
            return response;
        }
        try {
            Files.copy(imageFile.getInputStream(),
                    Paths.get(fileLocation + File.separator + imageFile.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setResult("SUCCESS");
        return response;
    }

    public StatusDto uploadImage3(MultipartFile imageFile, String registrationNo) {
        StatusDto response = new StatusDto();
        String directory = "static/images/";
        String fileLocation = new File(directory).getAbsolutePath();
        if (imageFile.isEmpty()) {
            response.setResult("ERROR");
            response.setMessage("FIle not found.");
            return response;
        }
        if (!imageFile.getContentType().equals("image/jpeg")) {
            response.setResult("ERROR");
            response.setMessage("File is not in JPG format");
            return response;
        }
        String[] split = registrationNo.split("\\.");
        try {
            String fileName = ArrayUtils.isEmpty(split) ? registrationNo : split[0];
            String fileExtension = imageFile.getContentType().equals("image/jpeg") ? ".jpg" : "jpeg";

            Files.copy(imageFile.getInputStream(),
                    Paths.get(fileLocation + File.separator + fileName + fileExtension),
                    StandardCopyOption.REPLACE_EXISTING);
            response.setResult("SUCCESS");
            response.setMessage(fileName + fileExtension);
        } catch (IOException e) {
            e.printStackTrace();
            response.setResult("FAILED");
        }

        return response;
    }

//    public Response deleteDocuments(String reference, String extension){
//        Response response = new Response();
//        String directory = "static/images/";
//        if (extension.equals(".pdf")){
//            directory = "static/certificates/";
//        }
//
//        Path fileToBeDeleted = Paths.get(directory + reference);
//        try {
//            Files.delete(fileToBeDeleted);
//            response.setResult("SUCCESS");
//        } catch (IOException e) {
//            response.setResult("FAILED");
//            e.printStackTrace();
//        }
//        return response;
//    }

    public StatusDto deleteFile(String reference, String extension, String fileType){
        StatusDto response = new StatusDto();
        String directory = null;
        if (fileType.equalsIgnoreCase(FileType.IMAGE_FILE)){
            directory = "static/images/";
        }
        if (fileType.equalsIgnoreCase(FileType.DOCUMENT_FILE)){
            directory = "static/documents/";
        }

        Path fileToBeDeleted = Paths.get(directory + reference+extension);
        try {
            Files.delete(fileToBeDeleted);
            response.setResult("SUCCESS");
        } catch (IOException e) {
            response.setResult("FAILED");
            e.printStackTrace();
        }
        return response;
    }

    public String readFile(MultipartFile file){
        uploadFile(file, "Temp", FileType.PDF_FILE);
        String directory = "static/prescription/";
        String pdfText ="";
        String fileLocation = new File(directory).getAbsolutePath();
        String filePath = fileLocation + File.separator + "Temp.pdf";
        File pfdFile = new File(filePath);
        try {
            PDDocument document = PDDocument.load(pfdFile);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfText = pdfStripper.getText(document);
            document.close();
            deleteFile("Temp.pdf","", FileType.PDF_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pdfText;

    }

    @Value("${image.storage.location}")
    private String imageStorageLocation;

    @Value("${document.storage.location}")
    private String documentStorageLocation;

    public StatusDto uploadFile(MultipartFile file, String fileName, String type){
        StatusDto response = new StatusDto();
        String directory = "";
        String directoryPath = "";
        if (type.equalsIgnoreCase(FileType.IMAGE_FILE)){
           directory = imageStorageLocation;
           directoryPath = "image/";
        }
        if (type.equalsIgnoreCase(FileType.DOCUMENT_FILE)){
            directory = documentStorageLocation;
            directoryPath = "document/";
        }

        String fileLocation = new File(directory).getAbsolutePath();
        String[] split = fileName.split("\\.");
        try {
            String file_name = ArrayUtils.isEmpty(split) ? fileName : split[0];
            String fileExtension = Utility.getFileExtension(file.getOriginalFilename());

            Files.copy(file.getInputStream(),
                    Paths.get(fileLocation + File.separator + file_name + fileExtension),
                    StandardCopyOption.REPLACE_EXISTING);
            response.setResult("SUCCESS");
            response.setMessage(directoryPath + file_name + fileExtension);
        } catch (IOException e) {
            e.printStackTrace();
            response.setResult("FAILED");
        }
        return response;
    }
//    public Response uploadCertificate(MultipartFile file, String referenceNo){
//        Response response = new Response();
//        String directory = "static/documents/";
//        String fileLocation = new File(directory).getAbsolutePath();
//        if (!file.getContentType().equals("application/pdf")){
//            response.setResult("ERROR");
//            response.setError("File is not in pdf format");
//            return response;
//        }
//        try {
//            String fileName = referenceNo.isEmpty() ? file.getOriginalFilename() : referenceNo;
//            String fileExtension = file.getContentType().equals("application/pdf") ? ".pdf" : ".docx";
//
//            Files.copy(file.getInputStream(),
//                    Paths.get(fileLocation + File.separator + fileName + fileExtension),
//                    StandardCopyOption.REPLACE_EXISTING);
//            response.setResult("SUCCESS");
//            response.setMessage(fileName + fileExtension);
//        } catch (IOException e) {
//            e.printStackTrace();
//            response.setResult("FAILED");
//        }
//        return response;
//    }

    public String getFilePath(String fileName) {
        String directory = "static/images/";
        String fileLocation = new File(directory).getAbsolutePath();
        String fullPath = fileLocation + fileName;
        System.out.println(fullPath);

        Path resolvePath = Paths.get(fileLocation).toAbsolutePath().normalize().resolve(fileName);
        if (!Files.exists(resolvePath)) {
            try {
                throw new FileNotFoundException(fileName + " not found");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return String.valueOf(resolvePath);
    }


    public String storeImage(String fileName, String base64EncodedFile, String extension) throws Exception {
        String directory = "static/images";
        storeFile(fileName, base64EncodedFile, extension, directory);
        return String.format("images/%s.%s", fileName, extension);
    }

    public String storeDoc(String fileName, String base64EncodedFile, String extension) throws Exception {
        String directory = "static/docs";
        storeFile(fileName, base64EncodedFile, extension, directory);
        return String.format("docs/%s.%s", fileName, extension);
    }

    public String storeTestimony(String fileName, String base64EncodedFile, String extension) throws Exception {
        String directory = "static/testimonies";
        storeFile(fileName, base64EncodedFile, extension, directory);
        return String.format("testimonies/%s.%s", fileName, extension);
    }

    private void storeFile(String fileName, String base64EncodedFile, String extension, String directory) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(base64EncodedFile);
        String fullName = String.format("%s.%s", fileName, extension);
        String fileLocation = new File(directory).getAbsolutePath() + "/" + fullName;
        FileOutputStream fos = new FileOutputStream(fileLocation);
        fos.write(decoded);
        fos.close();

        log.debug("File storing. Name: {}, Extension: {}, Directory: {}", fileName, extension, directory);
    }

    public String storeFile(MultipartFile file, String fileName) {

        String directory = "static/" + fileName;

        String generatedFilename = getGeneratedFilename(file.getOriginalFilename());

        String fullPath = new File(directory).getAbsolutePath() + "/" + generatedFilename;

        try {

            FileOutputStream fos = new FileOutputStream(fullPath);
            fos.write(file.getBytes());
            return generatedFilename;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getGeneratedFilename(String originalFileName) {

        String fileExtension = getFileExtension(originalFileName);

        return RandomStringUtils.randomAlphanumeric(10) + "." + fileExtension;
    }

    private String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

}
