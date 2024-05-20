package mav.goode.jasperreportservice.client;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/*
Authentication
        If your JasperReports Server requires authentication, you need to handle the login session. This can be done through:

        Direct URL Access with Credentials:

        Some servers allow appending credentials to the URL for direct access:

        perl
        Copy code
        http://<username>:<password>@<server-host>:<server-port>/jasperserver/flow.html?_flowId=viewReportFlow&reportUnit=/path/to/report&decorate=no
        Using a REST API for Authentication:

        You can use the JasperReports Server REST API to authenticate and then access the report. Here’s a brief example in Java using the Apache HttpClient:
*/
public class JasperReportsClient {

    public static void main(String[] args) throws Exception {
        String jasperServerUrl = "http://localhost:8080/jasperserver";
        String reportPath = "/reports/sales/monthly_sales_report";
        String username = "your-username";
        String password = "your-password";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Login to the server
            HttpPost loginPost = new HttpPost(jasperServerUrl + "/rest/login");
            loginPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            String loginPayload = "j_username=" + username + "&j_password=" + password;
            loginPost.setEntity(new StringEntity(loginPayload));
            client.execute(loginPost);

            // Access the report
            HttpGet reportGet = new HttpGet(jasperServerUrl + "/flow.html?_flowId=viewReportFlow&reportUnit=" + reportPath + "&decorate=no");
            String response = EntityUtils.toString(client.execute(reportGet).getEntity());
            System.out.println(response);
        }
    }
}

