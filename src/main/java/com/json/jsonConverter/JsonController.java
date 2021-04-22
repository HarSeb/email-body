package com.json.jsonConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@RestController
public class JsonController {
    //create html from stored JSON file
    @GetMapping(value = "/Json", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String jsonConversion() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //read json file and convert to customer object
        Email_Body email_body = objectMapper.readValue(new File("email_Body.json"), Email_Body.class);

        return "<html>\n" + "<header><title>Email</title></header>\n" +
                "<body>\n" + "Hi \n" +email_body.getName()+ ","+"<br />"+"<br />"+"\n Please verify your address \n"+email_body.getAddress() +"<br />\n Thanks and Regards \n"+"</body>\n" + "</html>";

    }


    //create html from requestBody
    @GetMapping (value="/email", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    public @ResponseBody String email_body(@RequestBody Email_Body email_body)
    {
        System.out.print(email_body.getName()+email_body.getAddress()+email_body.getEmail());
        return "<html>\n" + "<header><title>Email</title></header>\n" +
                "<body>\n" + "Hi \n" +email_body.getName()+ ","+"<br />"+"<br />"+"\n Please verify your email \n"+email_body.getEmail() +"<br />\n Thanks and Regards \n"+"</body>\n" + "</html>";
    }

    //create html from requestBody
    @GetMapping (value="/email1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    public @ResponseBody String email_body1(HttpEntity<String> request) throws JSONException {
        System.out.print("request**********"+request);
        String email_Body="<html>\n" + "<header><title>Email</title></header>\n"+ "<body>\n"+"\n ";
        if (request != null)
        {
            String json =request.getBody().toString();
            JSONObject obj = new JSONObject(json);
            for(int i = 0; i<obj.names().length(); i++){
                email_Body=  email_Body+obj.names().getString(i)+": "+obj.get(obj.names().getString(i))+"\n";
                System.out.print("key = " + obj.names().getString(i) + " value = " + obj.get(obj.names().getString(i)));
            }
        }
        email_Body=email_Body+"</body>\n" + "</html>";
        return email_Body;
        /*System.out.print(email_body.getName()+email_body.getAddress()+email_body.getEmail());
        return "<html>\n" + "<header><title>Email</title></header>\n" ;/*+
                "<body>\n" + "Hi \n" +email_body.getName()+ ","+"<br />"+"<br />"+"\n Please verify your email \n"+email_body.getEmail() +"<br />\n Thanks and Regards \n"+"</body>\n" + "</html>";*/


    }


    /*//create html from requestBody-Post
    @PostMapping (value="/email_post", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    public @ResponseBody String email_body_post(@RequestBody Email_Body email_body)
    {
        System.out.print(email_body.getName()+email_body.getAddress()+email_body.getEmail());
        return "<html>\n" + "<header><title>Email</title></header>\n" +
                "<body>\n" + "Hi \n" +email_body.getName()+ ","+"<br />"+"<br />"+"\n Please verify your email \n"+email_body.getEmail() +"<br />\n Thanks and Regards \n"+"</body>\n" + "</html>";
    }*/

}