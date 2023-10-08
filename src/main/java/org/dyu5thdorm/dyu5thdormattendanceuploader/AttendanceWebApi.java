package org.dyu5thdorm.dyu5thdormattendanceuploader;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:attendance.properties")
public class AttendanceWebApi {
    @Value("${api.login}")
    private String loginLink;
    @Value("${api.insert}")
    private String saveLink;
    @Value("${api.account}")
    private String account;
    @Value("${api.password}")
    private String password;
    @Value("${format.date}")
    private String dateFormat;
    DateTimeFormatter dateTimeFormatter;

    @PostConstruct
    void init() {
        dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    public void login() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = prepareLoginRequest();
        HttpClient
                .newHttpClient()
                .send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                ).statusCode();
    }

    public Integer insert(List<String> students, LocalDate date) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = prepareInsertRequest(students, date);
        return HttpClient
                .newHttpClient()
                .send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                ).statusCode();
    }

    private HttpRequest prepareInsertRequest(List<String> students, LocalDate date) throws URISyntaxException {
        return HttpRequest
                .newBuilder()
                .uri(
                        new URI(saveLink)
                )
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .POST(
                        HttpRequest.BodyPublishers.ofString(
                                getInsertPayload(students, date)
                        )
                )
                .build();
    }

    private String getInsertPayload(List<String> students, LocalDate date) {
        List<String> payload = new ArrayList<>();
        payload.add("s_smye=112");
        payload.add("s_smty=1");
        payload.add(
                String.format("s_dt=%s", date.format(dateTimeFormatter))
        );
        for (String student : students) {
            payload.add("sf_stno[]=" + student);
        }
        payload.add("dmuster_status=dmuster_add");
        return String.join("&", payload);
    }

    private HttpRequest prepareLoginRequest() throws URISyntaxException {
        return HttpRequest
                .newBuilder()
                .uri(
                        new URI(loginLink)
                )
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .POST(
                        HttpRequest.BodyPublishers.ofString(
                                getLoginPayload(account, password)
                        )
                )
                .build();
    }

    private String getLoginPayload(String account, String password) {
        return String.format(
                "login_id=%s&login_passwd=%s", account, password
        );
    }
}
