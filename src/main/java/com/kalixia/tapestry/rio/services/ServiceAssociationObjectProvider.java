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
import org.apache.tapestry5.ioc.AnnotationProvider;
import org.apache.tapestry5.ioc.ObjectLocator;
import org.apache.tapestry5.ioc.ObjectProvider;
import org.rioproject.associations.Association;
import org.rioproject.associations.AssociationDescriptor;
import org.rioproject.associations.AssociationManagement;
import org.rioproject.associations.AssociationMgmt;
import org.rioproject.associations.ServiceAssociation;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ServiceAssociationObjectProvider implements ObjectProvider {

    @SuppressWarnings("unchecked")
    public <T> T provide(Class<T> objectType, AnnotationProvider annotationProvider, ObjectLocator locator) {
        ServiceAssociation annotation = annotationProvider.getAnnotation(ServiceAssociation.class);

        if (annotation == null) return null;

        AssociationDescriptor descriptor = AssociationDescriptor.create(annotation.name(),
                null, annotation.serviceType(), annotation.type(), DiscoveryGroupManagement.ALL_GROUPS);
        AssociationManagement aMgr = new AssociationMgmt();
        Association<T> association = aMgr.addAssociationDescriptor(descriptor);

        try {
            return association.getServiceFuture().get(30, TimeUnit.SECONDS);        // TODO: allow for custom setting
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

}
