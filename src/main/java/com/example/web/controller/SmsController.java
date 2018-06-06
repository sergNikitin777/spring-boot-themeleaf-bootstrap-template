package com.example.web.controller;

import com.example.web.pojo.QueueAnswerPojo;
import com.example.web.pojo.QueuePojo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SmsController {

    CloseableHttpClient httpClient=HttpClientBuilder.create().build();
    ClientHttpRequestFactory clientHttpRequestFactory=new HttpComponentsClientHttpRequestFactory(httpClient);
    private RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

    @RequestMapping(value = "/admin/sms/send", method = RequestMethod.POST)
    public ResponseEntity sendMessage(QueuePojo queuePojo) {
        String url = "http://json.gate.iqsms.ru/send/";
        try{
            HttpEntity<QueuePojo> request = new HttpEntity<>(queuePojo);
            return new ResponseEntity<>(restTemplate.postForObject(new URI(url),request, QueueAnswerPojo.class), HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
