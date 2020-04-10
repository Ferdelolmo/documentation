import com.datadog.api.v1.client.api.SyntheticsApi;

public class SyntheticsApiExample {

    public static void main(String[] args) {
        SyntheticsApi apiInstance = new SyntheticsApi();
        try {
            SyntheticsDevices result = apiInstance.getAllDevices();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SyntheticsApi#getAllDevices");
            e.printStackTrace();
        }
    }
}