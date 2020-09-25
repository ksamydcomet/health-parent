package com.dcomet.health.web.rest;

import com.dcomet.health.dao.data.PatientImageData;
import com.dcomet.health.domain.Patient;
import com.dcomet.health.domain.PatientSearchCriteria;
import com.dcomet.health.domain.PatientSearchRequest;
import com.dcomet.health.domain.reportmodel.DoctorWiseReport;
import com.dcomet.health.service.business.ClinicalService;
import com.dcomet.module.domain.User;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Path("patient/v1")
public class PatientResource extends BaseResource {

    @Autowired
    @Qualifier("clinicalService")
    public ClinicalService clinicalService;

    @POST
    @Path("/search")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Patient> search(@Context final SecurityContext securityContext, PatientSearchCriteria patientSearchCriteria) {
        PatientSearchRequest patientSearchRequest = new PatientSearchRequest();
        addSecurityContext(securityContext, patientSearchRequest);
        List<Criterion> searchCriterionList = new ArrayList<>();
        searchCriterionList.add(Restrictions.eq("patEntRid", patientSearchRequest.getEntityRid()));
        patientSearchRequest.setSearchCriterionList(searchCriterionList);
        patientSearchRequest.addPatientSearchCriteria(patientSearchCriteria);
        return clinicalService.getPatient(patientSearchRequest, true);
    }

    @GET
    @Path("/allvisits/{entityRid}/{fromDate}/{toDate}/{visitType}")
    @Produces("application/json")
    public List<Object[]> getAllVisitReport(@PathParam("entityRid") final Integer entityRid, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate, @PathParam("visitType") final String visitType) throws Exception {
//        String entityRids = " ";
//        entityRids = getEntityRid(entityRid);
        return clinicalService.getAllVisitReport(entityRid, fromDate, toDate, visitType);
    }
    
    @GET
    @Path("/newReg/{entityRid}/{curDate}")
    @Produces("application/json")
    public List<Object[]> getNewRegistrationReports(@PathParam("entityRid") final Integer entityRid, @PathParam("curDate") final String curDate) throws Exception {
        return clinicalService.getNewRegistrationReport(entityRid, curDate);
    }

    @GET
    @Path("/reviewVisit/{entityRid}/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getReviewVisitReports(@PathParam("entityRid") final Integer entityRid, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        return clinicalService.getReviewVisitReport(entityRid, fromDate, toDate);
    }

    @GET
    @Path("/appointment/{entityRid}/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getAppointmentReports(@PathParam("entityRid") final Integer entityRid, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        return clinicalService.getAppointmentReport(entityRid, fromDate, toDate);
    }

    @GET
    @Path("/dateWise/{entityRid}/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getDateWiseReports(@PathParam("entityRid") final Integer entityRid, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        return clinicalService.getDateWiseReport(entityRid, fromDate, toDate);
    }

    @GET
    @Path("/specialityWise/{entityRid}/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getSpecialityWiseReports(@PathParam("entityRid") final Integer entityRid, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        return clinicalService.getSpecialityWiseReport(entityRid, fromDate, toDate);
    }

//    @GET
//    @Path("/cancelledAppointment/{entityRid}/{fromDate}/{toDate}")
//    @Produces("application/json")
//    public List<Object[]> getCancelledAppointmentReports(@PathParam("entityRid") final Integer entityRid, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
//        return clinicalService.getCancelledAppointmentReport(entityRid, fromDate, toDate);
//    }

    @GET
    @Path("/referralType/{entityRid}/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getReferralTypeReports(@PathParam("entityRid") final Integer entityRid, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        return clinicalService.getReferralTypeReport(entityRid, fromDate, toDate);
    }

    @GET
    @Path("/doctorWiseReport/{entityRid}/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<DoctorWiseReport> getDoctorWiseReports(@PathParam("entityRid") final Integer entityRid, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        return clinicalService.getDoctorWiseReport(entityRid, fromDate, toDate);
    }

    @GET
    @Path("/appointmentWiseReport/{entityRid}/{fromDate}/{toDate}")
    @Produces("application/json")
    public List<Object[]> getAppointmentWiseReports(@PathParam("entityRid") final Integer entityRid, @PathParam("fromDate") final String fromDate, @PathParam("toDate") final String toDate) throws Exception {
        return clinicalService.getAppointmentWiseReport(entityRid, fromDate, toDate);
    }

    @POST
    @Path("/patient/photo/upload")
    @Consumes("multipart/form-data")
    @Produces("appliation/json")
    public Response uploadPhoto(@QueryParam("patRid") Integer patRid, MultipartFormDataInput input, @Context final SecurityContext securityContext) throws IOException {
        User user = getLoginUser(securityContext);
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");
        InputPart inputPart = inputParts.get(0);
        InputStream inputStream = inputPart.getBody(InputStream.class, null);

        byte[] bytes = IOUtils.toByteArray(inputStream);

        PatientImageData photo = new PatientImageData();
        photo.setPatImgPatRid(patRid);
        photo.setPatImgPhoto(bytes);
        photo.setPatImgEntityRid(user.getEntityRid());
        PatientImageData savedPhoto = clinicalService.savePatientPhoto(photo);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("imageId", savedPhoto.getId());
        } catch (JSONException e) {
            throw new IOException(e);
        }
        return Response.status(200).entity(jsonObject.toString()).build();
    }

    @GET
    @Path("/pat/image")
    @Produces("image/jpg")
    public Response getPhotoByImgPatRid(@QueryParam("imgPatRid") Integer patRid, @QueryParam("size") String size) {
        PatientImageData photo = clinicalService.getPatPhotoByImgPatRid(patRid);
        return makeResponse(photo, size);
    }

    @GET
    @Path("/photo")
    @Produces("image/jpg")
    public Response getPhoto(@QueryParam("imgId") Integer id, @QueryParam("size") String size) {
        PatientImageData photo = clinicalService.getPatPhotoById(id);
        return makeResponse(photo, size);
    }

    Response makeResponse(PatientImageData photo, String size) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream(2048);
        try {
            InputStream in = new ByteArrayInputStream(photo.getPatImgPhoto());
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

    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int width, int height, int type) {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return resizedImage;
    }

    @POST
    @Path("/img/search")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer imgSearch(@Context final SecurityContext securityContext, @QueryParam("imgPatRid") Integer imgPatRID) {
        PatientImageData photo = clinicalService.getPatPhotoByImgPatRid(imgPatRID);
        return photo.getId();
    }

    @POST
    @Path("/info/save")
    @Consumes("application/json")
    @Produces("application/json")
    public void saveFileInfo(@Context final SecurityContext securityContext, PatientImageData fileInfo) {
        User user = getLoginUser(securityContext);
        fileInfo.setCreatedUserRid(user.getId());
        fileInfo.setPatImgEntityRid(user.getEntityRid());
        clinicalService.savePatientImage(fileInfo);
    }

    @POST
    @Path("/savegrid")
    @Consumes("application/json")
    @Produces("application/json")
    public Integer save(@Context final SecurityContext securityContext, Patient Patient) {
        addSecurityContext(securityContext, Patient);
        return clinicalService.savePatient(Patient);
    }
}
