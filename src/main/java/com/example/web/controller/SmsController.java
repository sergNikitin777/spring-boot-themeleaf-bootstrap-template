package com.example.web.controller;

import com.example.persistance.entity.UserApplication;
import com.example.persistance.repository.UserApplicationRepository;
import com.example.web.pojo.QueueAnswerPojo;
import com.example.web.pojo.QueuePojo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SmsController {

    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    ClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
    private RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
    private final UserApplicationRepository userApplicationRepository;

    @RequestMapping(value = "/admin/sms/send", method = RequestMethod.POST)
    public ResponseEntity sendMessage(QueuePojo queuePojo) {
        String url = "http://json.gate.iqsms.ru/send/";
        try {
            HttpEntity<QueuePojo> request = new HttpEntity<>(queuePojo);
            return new ResponseEntity<>(restTemplate.postForObject(new URI(url), request, QueueAnswerPojo.class), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/admin/sms/send/{id}", method = RequestMethod.POST)
    public ResponseEntity<QueueAnswerPojo> sendMessage(QueuePojo queuePojo, @PathVariable("id") Integer id) {
        String url = "http://json.gate.iqsms.ru/send/";
        ResponseEntity<QueueAnswerPojo> responseEntity = null;
        try {
            HttpEntity<QueuePojo> request = new HttpEntity<>(queuePojo);
            responseEntity = new ResponseEntity<>(restTemplate.postForObject(new URI(url), request, QueueAnswerPojo.class), HttpStatus.OK);
            if (queuePojo != null && id != null) {
                UserApplication userApplication = userApplicationRepository.findOne(id);
                QueueAnswerPojo queueAnswerPojo = responseEntity.getBody();
                if (queueAnswerPojo.getStatus().equals("ok"))
                    if (queueAnswerPojo.getMessages() != null) {
                        if (queueAnswerPojo.getMessages().get(0) != null) {
                            userApplication.setSmscClientId(queueAnswerPojo.getMessages().get(0).getSmscId());
                            userApplication.setSmsClientStatus(queueAnswerPojo.getMessages().get(0).getStatus());
                        }
                        if (queueAnswerPojo.getMessages().size() != 1) {
                            userApplication.setSmscClientId(queueAnswerPojo.getMessages().get(1).getSmscId());
                            userApplication.setSmsDriverStatus(queueAnswerPojo.getMessages().get(1).getStatus());
                        }
                    }
                userApplicationRepository.save(userApplication);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }

}
