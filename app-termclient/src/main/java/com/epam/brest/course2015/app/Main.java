package com.epam.brest.course2015.app;

import com.epam.brest.course2015.domain.Detail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

/**
 * Created by pavel on 11/14/15.
 */

@Component
public class Main {

    @Value("${server.protocol}://${server.host}:${server.port}/${server.prefix}/")
    private String url;

    @Value("${point.docs}")
    private String urlDocs;

    @Value("${point.doc}")
    private String urlDoc;

    @Value("${point.docPr}")
    private String urlDocPr;

    @Value("${point.docInc}")
    private String urlDocInc;

    @Value("${point.docHeads}")
    private String urlDocHeads;

    @Value("${point.details}")
    private String urlDetails;

    @Value("${point.detail}")
    private String urlDetail;

    @Value("${point.curState}")
    private String urlCurState;

    Scanner sc = new Scanner(System.in);
    ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
    RestTemplate restTemplate = new RestTemplate(requestFactory);
    {
        restTemplate.setErrorHandler(new CustomResponseErrorHandler());
    }

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        Main main = ctx.getBean(Main.class);
        main.menu();
    }

    private void menu() {

        int swValue = 0;

        System.out.println("============================================");
        System.out.println("|   MENU SELECTION DEMO                    |");
        System.out.println("============================================");
        System.out.println("| Options:                                 |");
        System.out.println("|        1. Get current state of storage   |");
        System.out.println("|        2. Get all documents              |");
        System.out.println("|        3. Get documents head             |");
        System.out.println("|        4. Get document by id             |");
        System.out.println("|        5. Add document                   |");
        System.out.println("|        6. Delete document by id          |");
        System.out.println("|        7. Get all details                |");
        System.out.println("|        8. Add detail                     |");
        System.out.println("|        9. Delete detail by id            |");
        System.out.println("|        10. Exit                          |");
        System.out.println("============================================");
        while (swValue != 10) {
            System.out.print("Select option: ");
            if (sc.hasNextInt()) {
                swValue = sc.nextInt();
                checkValue(swValue);
            } else {
                System.out.println("Bad value: " + sc.next());
            }
        }
    }

    private void checkValue(int item) {
        switch (item) {
            case 1:
                getCurrentState();
                break;
            case 2:
                getAllDocs();
                break;
            case 3:
                getAllDocHeads();
                break;
            case 4:
                getDocById();
                break;
            case 5:
                addDocum();
                break;
            case 6:
                deleteDocById();
                break;
            case 7:
                getAllDetails();
                break;
            case 8:
                addDetail();
                break;
            case 9:
                deleteDetailById();
                break;
            case 10:
                System.out.println("Exit.");
                break;
            default:
                System.out.println("Invalid selection.");
                break;
        }
    }

    private void getCurrentState() {
        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.getForEntity(url + urlCurState, Object.class);
            System.out.println("    Docs: " + responseEntity.getBody());
        } catch (CustomException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }

    private void getAllDocs() {
        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.getForEntity(url + urlDocs, Object.class);
            System.out.println("    Docs: " + responseEntity.getBody());
        } catch (CustomException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }

    private void getAllDocHeads() {
        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.getForEntity(url + urlDocHeads, Object.class);
            System.out.println("    DocHeads: " + responseEntity.getBody());
        } catch (CustomException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }

    private void getDocById() {
        Integer docId = 0;
        System.out.print("    Enter document Id: ");
        if(sc.hasNextInt()) {
            docId = sc.nextInt();
        }

        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.getForEntity(url + urlDoc + "/" + docId.toString(), Object.class);
            System.out.println("    Document: " + responseEntity.getBody());
        } catch (CustomException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }

//    TODO
    private void addDocum() {
    }

    private void deleteDocById() {
        Integer docId = 0;
        System.out.print("    Enter document Id: ");
        if(sc.hasNextInt()) {
            docId = sc.nextInt();
        }

        try {
            restTemplate.delete(url + urlDoc + "/" + docId.toString(), Object.class);
            System.out.println("    Document is deleted");
        } catch (CustomException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }

    private void getAllDetails() {
        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.getForEntity(url + urlDetails, Object.class);
            System.out.println("    Details: " + responseEntity.getBody());
        } catch (CustomException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }

    private void addDetail() {
        String detName = "";
        System.out.print("    Enter new detail name: ");
        if(sc.hasNext()) {
            detName = sc.next();
        }
        Detail newDetail = new Detail(detName);

        try {
            restTemplate.postForObject(url + urlDetail, newDetail, Object.class);
            System.out.println("    Detail is added");
        } catch (CustomException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }

    private void deleteDetailById() {
        Integer detailId = 0;
        System.out.print("    Enter detail Id: ");
        if(sc.hasNextInt()) {
            detailId = sc.nextInt();
        }

        try {
            restTemplate.delete(url + urlDetail + "/" + detailId.toString(), Object.class);
            System.out.println("    Detail is deleted");
        } catch (CustomException ex) {
            System.out.println("    ERROR: " + ex.getMessage());
        }
    }
}