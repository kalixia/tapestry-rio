import com.kalixia.tapestry.rio.services.RioConstants;
import java.util.concurrent.TimeUnit;

public class MyModule {
    public void contributeApplicationDefaults(MappedConfiguration<String, String> configuration) {
        configuration.add(RioConstants.DISCOVERY_TIMEOUT, "10");            // discovery times out after 10 ...
        configuration.add(RioConstants.DISCOVERY_UNIT, TimeUnit.SECONDS);   // ... seconds
    }
}