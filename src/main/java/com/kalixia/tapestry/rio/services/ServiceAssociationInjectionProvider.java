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
import org.apache.tapestry5.ioc.ObjectLocator;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.services.ClassTransformation;
import org.apache.tapestry5.services.InjectionProvider;
import org.rioproject.associations.Association;
import org.rioproject.associations.AssociationDescriptor;
import org.rioproject.associations.AssociationManagement;
import org.rioproject.associations.AssociationMgmt;
import org.rioproject.associations.ServiceAssociation;
import java.rmi.RMISecurityManager;
import java.security.Permission;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ServiceAssociationInjectionProvider implements InjectionProvider {
    private final RMISecurityManager rioSecurityManager;

    public ServiceAssociationInjectionProvider() {
        rioSecurityManager = new RMISecurityManager() {
            public void checkPermission(Permission perm) {
                // do nothing -- allow everything!
            }
        };
    }

    public boolean provideInjection(String fieldName, Class fieldType, ObjectLocator locator,
                                    ClassTransformation transformation, MutableComponentModel componentModel) {
        SecurityManager old = System.getSecurityManager();
        System.setSecurityManager(rioSecurityManager);
        try {

            ServiceAssociation annotation = transformation.getFieldAnnotation(fieldName, ServiceAssociation.class);
            if (annotation == null) return false;

            AssociationDescriptor descriptor = AssociationDescriptor.create(annotation.name(),
                    null, annotation.serviceType(), annotation.type(), DiscoveryGroupManagement.ALL_GROUPS);
            AssociationManagement aMgr = new AssociationMgmt();
            Association association = aMgr.addAssociationDescriptor(descriptor);

            try {
                Future serviceFuture = association.getServiceFuture();
                Object inject = serviceFuture.get(30, TimeUnit.SECONDS);        // TODO: allow for custom setting

                assert inject != null;

                transformation.injectField(fieldName, inject);

                // If we make it this far without an exception, then we were successful
                // and should claim the field.

                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            return false;
        } finally {
            System.setSecurityManager(old);
        }
    }
}
