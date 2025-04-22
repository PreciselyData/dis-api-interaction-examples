import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SampleQuery {
    public static void main(String[] args) throws Exception {
        String token = "YOUR_TOKEN_HERE";

        String query = """
            query GetByAddress {
                getByAddress(address: "4700 CALLE BOLERO, CAMARILLO, CA 93012") {
                    addresses {
                        data {
                            crimeIndex {
                                data {
                                    violentCrimeIndexNational
                                    propertyCrimeIndexNational
                                    compositeCrimeCategory {
                                        value
                                        description
                                    }
                                }
                            }
                            earthRisk {
                                data {
                                    nameOfNearestFault
                                    distanceToNearestFaultMiles
                                    faultType
                                }
                            }
                        }
                    }
                }
            }
            """;

        String jsonBody = "{ \"query\": " + toJsonString(query) + " }";

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://data-graph-dev.libigdata.cloud.precisely.services/data-graph/graphql"))
            .header("Authorization", "Bearer " + token)
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
            .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }

    private static String toJsonString(String s) {
        return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n") + "\"";
    }
}
