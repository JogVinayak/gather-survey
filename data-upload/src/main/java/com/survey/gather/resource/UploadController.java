package com.survey.gather.resource;

import com.survey.gather.mail.model.EMail;
import com.survey.gather.mail.service.EmailService;
import com.survey.gather.mail.service.SurveyMailSender;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jdk.nashorn.internal.runtime.JSONFunctions;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    EmailService emailService;

    @Autowired
    SurveyMailSender sender;

    @ApiOperation(value = "Submit text form", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created a new form"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/submit-filled-form")
    ResponseEntity<?> add(@RequestBody String jsonString) {
        Document doc = Document.parse(jsonString);
        doc.append("createdDate", ZonedDateTime.now().toString());
        Document json = mongoTemplate.insert(doc, "filled-forms");
        return new ResponseEntity<>(json, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get list of forms with specific filter", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieved list of forms"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })

    @RequestMapping(method = RequestMethod.GET, value = "/get-form/{filter-key}/{filter-value}")
    ResponseEntity<?> get(@PathVariable("filter-key") String filterKey, @PathVariable("filter-value")String filterValue){
        log.info("Reached get-form");
        Query query = new Query();
        query.addCriteria(Criteria.where(filterKey).regex(filterValue));

        List<Document> docList = mongoTemplate.find(query, Document.class, "filled-forms");

        return new ResponseEntity<>(docList, HttpStatus.OK);
    }

    @GetMapping("/mail")
    public ResponseEntity<?> mail(){
        try {
            EMail mail = new EMail();
            mail.setContent("This is the main text content");
            mail.setFrom("no-reply@gmail.com");
            mail.setTo("jog@live.in");
            Map<String, Object> modelMap = new HashMap<>();
            modelMap.put("firstName", "Dinesh");
            modelMap.put("lastName", "Kumar");
            mail.setModel(modelMap);
            emailService.sendMessageUsingThymeleafTemplate("jog@live.in", "test subject", modelMap);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Mail Sent successfully", HttpStatus.OK);
    }
}
