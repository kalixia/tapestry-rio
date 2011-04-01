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

import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.services.InjectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RioModule {
    private static final Logger logger = LoggerFactory.getLogger(RioModule.class);

    public static void contributeInjectionProvider(OrderedConfiguration<InjectionProvider> configuration) {
        configuration.add("ServiceAssociation", new ServiceAssociationInjectionProvider());
    }

}
