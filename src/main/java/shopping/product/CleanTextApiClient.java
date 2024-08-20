package shopping.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.thymeleaf.util.StringUtils;

public class CleanTextApiClient {
    private final RestClient restClient;

    public CleanTextApiClient() {
        this.restClient = RestClient.builder()
                .baseUrl("https://www.purgomalum.com") // base URL 설정
                .build();
    }

    public boolean isProfanityText(String text) {
        ResponseEntity<String> response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/service/containsprofanity")
                        .queryParam("text", text) // 쿼리 파라미터 추가
                        .build())
                .retrieve()
                .toEntity(String.class); // 응답을 문자열로 받기

        return Boolean.parseBoolean(response.getBody());
    }


//    public static void main(String[] args) {
//        CleanTextApiClient client = new CleanTextApiClient();
//        boolean aa = client.isProfanityText("fuck");
//        System.out.println(aa);
//    }

}