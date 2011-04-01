import com.kalixia.tapestry.rio.services.RioConstants;

public class MyModule {
    public void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
        configuration.add(RioConstants.DISCOVERY_TIMEOUT, "10");     // discovery times out after 10 seconds
    }
}