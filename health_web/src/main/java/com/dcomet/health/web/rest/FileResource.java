package com.dcomet.health.web.rest;

import com.dcomet.fw.util.DateUtil;
import com.dcomet.health.dao.data.FileAttachmentData;
import com.dcomet.health.dao.data.FileDData;
import com.dcomet.health.dao.data.FileHData;
import com.dcomet.health.domain.FileAttachment;
import com.dcomet.health.domain.FileAttachmentSearchCriteria;
import com.dcomet.health.domain.FileAttachmentSearchRequest;
import com.dcomet.health.service.business.FileService;
import com.dcomet.module.dao.data.UserPhotoData;
import com.dcomet.module.domain.User;
import com.dcomet.module.master.service.DCometMasterService;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.apache.tika.Tika;
import javax.ws.rs.core.SecurityContext;
import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Path("file")
public class FileResource extends BaseResource {

    @Autowired
    @Qualifier("masterService")
    private DCometMasterService masterService;

    @Autowired
    @Qualifier("fileService")
    private FileService fileService;

    @POST
    @Path("/user/photo/upload")
    @Consumes("multipart/form-data")
    @Produces("appliation/json")
    public Response uploadPhoto(@QueryParam("id") Integer fkUserId, MultipartFormDataInput input) throws IOException {

        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");
        InputPart inputPart = inputParts.get(0);
        InputStream inputStream = inputPart.getBody(InputStream.class, null);

        byte[] bytes = IOUtils.toByteArray(inputStream);

        UserPhotoData photo = new UserPhotoData();
        photo.setFkUserId(fkUserId);
        photo.setPhoto(bytes);
        UserPhotoData savedPhoto = masterService.saveUserPhoto(fkUserId, photo);

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("imageId", savedPhoto.getId());
        } catch (JSONException e) {
            throw new IOException(e);
        }
        return Response.status(200).entity(jsonObject.toString()).build();
    }

    @GET
    @Path("/user/photo")
    @Produces("image/jpg")
    public Response getUserPhoto(@QueryParam("id") Integer fkUserId, @QueryParam("size") String size) {

        UserPhotoData photo = masterService.getPhotoByUserId(fkUserId);
        return makeResponse(photo, size);

    }

    @GET
    @Path("/photo")
    @Produces("image/jpg")
    public Response getPhoto(@QueryParam("id") Integer id, @QueryParam("size") String size) {
        UserPhotoData photo = masterService.getPhotoById(id);
        return makeResponse(photo, size);
    }

    Response makeResponse(UserPhotoData photo, String size) {

        ByteArrayOutputStream bo = new ByteArrayOutputStream(2048);
        try {

            InputStream in = new ByteArrayInputStream(photo.getPhoto());
            BufferedImage image = ImageIO.read(in);
            if (size != null) {
                float maxDimension = 750.0f;
                if (size.equals("thumbnail")) {
                    maxDimension = 200.0f;
                } else if (size.equals("tinythumbnail")) {
                    maxDimension = 100.0f;
                }
                int origWidth = image.getWidth();
                float widthFactor = origWidth / maxDimension;
                int origHeight = image.getHeight();
                float heightFactor = origHeight / maxDimension;
                float factor = widthFactor > heightFactor ? widthFactor : heightFactor;
                int imageType = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
                image = resizeImageWithHint(image, (int) (origWidth / factor), (int) (origHeight / factor), imageType);
            }
            ImageIO.write(image, "jpeg", bo);
        } catch (IOException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Date expires = new Date(System.currentTimeMillis() + 3600 * 1000);
        return Response.ok().expires(expires).entity(bo.toByteArray()).build();

    }
//
//    @GET
//    @Path("/cleanse")
//    @Produces("text/plain")
//    //@Scheduled(cron = "0 30 23 * * ?")
//    public void scheduledFileCleanse() {
//        this.cleanseFiles();
//    }
//
//    public synchronized Response cleanseFiles() {
//        this.fileStoreCleaner.verifyFileActivations();
//        return Response.ok().entity("File cleansing complete").build();
//
//    }
//
//    /**
//     * header sample { Content-Type=[image/png], Content-Disposition=[form-data;
//     * name="file"; filename="filename.extension"] }
//     *
//     */
//    //get uploaded filename, is there a easy way in RESTEasy?
//    private String getFileName(MultivaluedMapImpl<String, String> header) {
//
//        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
//
//        for (String filename : contentDisposition) {
//            if ((filename.trim().startsWith("filename"))) {
//
//                String[] name = filename.split("=");
//
//                String finalFileName = name[1].trim().replaceAll("\"", "");
//                return finalFileName;
//            }
//        }
//        return "unknown";
//    }
//
//    //save to somewhere
//    private void writeFile(byte[] content, File file) throws IOException {
//
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//
//        FileOutputStream fop = new FileOutputStream(file);
//
//        fop.write(content);
//        fop.flush();
//        fop.close();
//
//    }

    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int width, int height, int type) {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    @Produces("appliation/json")
    public Response uploadFile(@Context final SecurityContext securityContext, MultipartFormDataInput input) throws IOException {
        User user = getLoginUser(securityContext);

        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");
        InputPart inputPart = inputParts.get(0);
        InputStream inputStream = inputPart.getBody(InputStream.class, null);
        byte[] bytes = IOUtils.toByteArray(inputStream);

        FileDData fileDData = new FileDData();
        fileDData.setData(bytes);
        fileDData.setCreatedUserRid(user.getId());
        fileDData.setCreatedDateTime(DateUtil.convertStringToCalendar(DateUtil.convertDateTimeToString(DateUtil.convertTimeBasedOnTimeZone(getEntity(user.getEntityRid()).getEntityTimezone()).getTime())));
        fileDData = fileService.storeFile(fileDData);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("imageId", fileDData.getId());
        } catch (JSONException e) {
            throw new IOException(e);
        }
        return Response.status(200).entity(jsonObject.toString()).build();
    }

    @GET
    @Path("/image")
    public Response getFile(@QueryParam("imageId") int imageId, @QueryParam("size") String size) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream(2048);
        String mimeType = null;
        try {
            FileDData file = fileService.getFile(imageId);
            mimeType = new Tika().detect(file.getData());
            InputStream in = new ByteArrayInputStream(file.getData());
            mimeType = mimeType.toLowerCase();
            mimeType = "application/x-tika-ooxml".equalsIgnoreCase(mimeType) ? "application/vnd.ms-excel" : mimeType;

            if (mimeType.startsWith("image")) {
                BufferedImage image = ImageIO.read(in);
                if (size != null) {
                    float maxDimension = 750.0f;
                    if (size.equals("thumbnail")) {
                        maxDimension = 200.0f;
                    } else if (size.equals("tinythumbnail")) {
                        maxDimension = 100.0f;
                    }
                    int origWidth = image.getWidth();
                    float widthFactor = origWidth / maxDimension;
                    int origHeight = image.getHeight();
                    float heightFactor = origHeight / maxDimension;
                    float factor = widthFactor > heightFactor ? widthFactor : heightFactor;
                    int imageType = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
                    image = resizeImageWithHint(image, (int) (origWidth / factor), (int) (origHeight / factor), imageType);
                }
                ImageIO.write(image, "jpeg", bo);
                mimeType = "image/jpg";
            } else if (size != null && size.equalsIgnoreCase("download")) {
                byte[] image = new byte[1024];
                for (int content; (content = in.read(image)) != -1;) {
                    bo.write(image, 0, content);
                }
            }
        } catch (IOException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Date expires = new Date(System.currentTimeMillis() + 3600 * 1000);
        return Response.ok(bo.toByteArray(), mimeType).expires(expires).build();
    }

    @GET
    @Path("/mimetype/{imageId}")
    @Produces("image/jpg")
    public Response getFile(@PathParam("imageId") int imageId) {
        FileDData file = fileService.getFile(imageId);
        String mimeType = new Tika().detect(file.getData());
        mimeType = "application/x-tika-ooxml".equalsIgnoreCase(mimeType) ? "application/vnd.ms-excel" : mimeType;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("imageId", file.getId());
            jsonObject.put("mimeType", mimeType);
        } catch (JSONException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(jsonObject.toString()).build();
    }

    @POST
    @Path("/info/delete")
    @Consumes("application/json")
    @Produces("application/json")
    public void deleteFile(@QueryParam("Id") Integer id) {
        fileService.deleteFile(id);
    }

    @POST
    @Path("/info")
    @Consumes("application/json")
    @Produces("application/json")
    public FileHData getFileInfo(@QueryParam("Id") Integer id, @QueryParam("Type") Integer type) {
        return fileService.getFileInfo(id, type);
    }

    @POST
    @Path("/info/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void saveFileInfo(@Context final SecurityContext securityContext, FileHData fileInfo) {
        User user = getLoginUser(securityContext);
        fileInfo.setCreatedUserRid(user.getId());
        fileInfo.setCreatedDateTime(DateUtil.convertStringToCalendar(DateUtil.convertDateTimeToString(DateUtil.convertTimeBasedOnTimeZone(getEntity(user.getEntityRid()).getEntityTimezone()).getTime())));
        fileInfo.setFlhEntityRID(user.getEntityRid());
        fileService.saveFileInfo(fileInfo);
    }

    // File Attachments 
    @POST
    @Path("/upload/attachments")
    @Consumes("multipart/form-data")
    @Produces("appliation/json")
//    , @QueryParam("taActive") Integer taActive
    public Response uploadAttachment(@QueryParam("refRid") Integer refRid, @QueryParam("taFileDesc") String fileDesc, @QueryParam("taType") Integer taType, MultipartFormDataInput input, @Context final SecurityContext securityContext) throws IOException {
        User user = new User();
        String fileName = "";
        addSecurityContext(securityContext, user);
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");
        InputPart inputPart = inputParts.get(0);
        MultivaluedMap<String, String> header = inputPart.getHeaders();
        fileName = getFileName(header);

        InputStream inputStream = inputPart.getBody(InputStream.class, null);
        byte[] bytes = IOUtils.toByteArray(inputStream);

        FileAttachmentData file = new FileAttachmentData();
//        if (id != null) {
//            file.setId(id);
//        }
        file.setTaRefRid(refRid);
        file.setTaFileDesc(fileDesc);
        file.setTaFile(bytes);
        file.setTaFileName(fileName);
        file.setTaType(taType);
        file.setTaEntityRid(user.getEntityRid());
//        file.setTaActive(taActive);
        FileAttachmentData savedfiles = fileService.saveFileAttachment(refRid, taType, file);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("fileId", savedfiles.getId());
        } catch (JSONException e) {
            throw new IOException(e);
        }
        return Response.status(200).entity(jsonObject.toString()).build();
    }

    private String getFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {

                String[] name = filename.split("=");

                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }

    // Download Code
    @GET
    @Path("/download/file")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFilebyQuery(@QueryParam("id") Integer id, @QueryParam("type") Integer type) throws IOException, SQLException {
        FileAttachmentData file = fileService.getFileById(id, type);
        return download(file);
    }

    public Response download(FileAttachmentData file) throws IOException, SQLException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = new ByteArrayInputStream(file.getTaFile());
        int data = in.read();
        while (data >= 0) {
            out.write((char) data);
            data = in.read();
        }
        out.flush();
        Response.ResponseBuilder responseBuilder = Response.ok(out.toByteArray());
        responseBuilder.header("Content-Disposition", "attachment; filename=" + file.getTaFileName());

        return responseBuilder.build();
    }

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<FileAttachment> search(FileAttachmentSearchCriteria fileAttachmentSearchCriteria) {
        FileAttachmentSearchRequest fileAttachmentSearchRequest = new FileAttachmentSearchRequest();
        fileAttachmentSearchRequest.addFileAttachmentCriteria(fileAttachmentSearchCriteria);
        return fileService.getFiles(fileAttachmentSearchRequest);
    }

}
