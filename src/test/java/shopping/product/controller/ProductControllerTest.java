package shopping.product.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int serverPort;

    private String serverUrl;

    private RestClient restClient;

    @Autowired
    private RestClient.Builder restClientBuilder;

    @BeforeEach
    public void MyRestClient() {
        this.restClient = restClientBuilder.build();
        serverUrl = "http://localhost:"+ serverPort;
    }


    @Test
    void getProduct() {
        String url = serverUrl + "/api/products/8146027";
        String expected = """
                {"id":8146027,"name":"아이스 카페 아메리카노 T","price":4500,"imgeUrl":"https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"}""";

        String actual = restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getProducts(){
        String url = serverUrl + "/api/products";

        String expected = """
                {"8146027":{"id":8146027,"name":"아이스 카페 아메리카노 T","price":4500,"imgeUrl":"https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"},"1111111":{"id":1111111,"name":"아이스 라뗴","price":4500,"imgeUrl":"https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"}}""";
        var actual = restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);

        System.out.println(actual);

    }

    @Test
    void putProduct(){
        String url = serverUrl + "/api/products";
        var json = """
                    {"id":999999,"name":"녹차라뗴","price":9900,"imgeUrl":"https://aaa.jpg"}
                """;


        long productId = restClient.post()
                .uri(url)
                .header("Content-Type", "application/json")
                .body(json)
                .retrieve()
                .body(Long.class)
                ;

        var actual = restClient.get()
                .uri(url+"/"+productId)
                .retrieve()
                .body(String.class);

        var expected = """
                {"id":%d,"name":"녹차라뗴","price":9900,"imgeUrl":"https://aaa.jpg"}""".formatted(productId);

        System.out.println("insertResult: "+actual);
        assertThat(actual).isEqualTo(expected);

    }


    @Test
    void updateProudct() {
        String url = serverUrl + "/api/products";
        var json = """
                    {"id":999999,"name":"녹차라뗴","price":9900,"imgeUrl":"https://aaa.jpg"}
                """;


        long productId = restClient.post()
                .uri(url)
                .header("Content-Type", "application/json")
                .body(json)
                .retrieve()
                .body(Long.class)
                ;

        var expected = """
                {"id":%d,"name":"오렌지주스","price":9000,"imgeUrl":"https://bbb.jpg"}""".formatted(productId);

        boolean result = restClient.put()
                .uri(url+ "/" + productId)
                .header("Content-Type", "application/json")
                .body(expected)
                .retrieve()
                .body(boolean.class)
                ;

        var actual = restClient.get()
                .uri(url+"/"+productId)
                .retrieve()
                .body(String.class);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void deleteProduct() {
        String url = serverUrl + "/api/products";
        var json = """
                    {"id":999999,"name":"녹차라뗴","price":9900,"imgeUrl":"https://aaa.jpg"}
                """;


        long productId = restClient.post()
                .uri(url)
                .header("Content-Type", "application/json")
                .body(json)
                .retrieve()
                .body(Long.class)
                ;


        boolean result = restClient.delete()
                .uri(url+ "/" + productId)
                .header("Content-Type", "application/json")
                .retrieve()
                .body(boolean.class)
                ;

        getProducts();
    }
}