/*
   Copyright 2011 Kalixia, SARL.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.kalixia.tapestry.rio.services;

import net.jini.discovery.DiscoveryGroupManagement;
import java.util.concurrent.TimeUnit;

/**
 * Defines the names of symbols used to configure tapestry-rio module.
 *
 * @see org.apache.tapestry5.ioc.services.SymbolSource
 */
public class RioConstants {

    /**
     * Indicates the groups used for discovery of Rio services.
     * Defaults to {@link DiscoveryGroupManagement#ALL_GROUPS}.
     */
    public static final String DISCOVERY_GROUPS = "rio.discovery.groups";

    /**
     * Indicates the timeout to use when discovering Rio services.
     */
    public static final String DISCOVERY_TIMEOUT = "rio.discovery.timeout";

    /**
     * Indicates the {@link TimeUnit} used for timeout of Rio services discovery.
     */
    public static final String DISCOVERY_UNIT = "rio.discovery.unit";
}
