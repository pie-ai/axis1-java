/**
 * Copyright 2001-2004 The Apache Software Foundation.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * Author: Eran Chinthaka - Lanka Software Foundation
 * Date: Dec 22, 2004
 * Time: 11:42:51 AM
 */
package org.apache.axis.addressing;

import junit.framework.TestCase;

import javax.xml.namespace.QName;


public class EndpointReferenceTypeTest extends TestCase {

    EndpointReferenceType endpointReferenceType;
    private String headerType = AddressingConstants.WSA_FROM;
    private String address = "htttp://wwww.openource.lk/~chinthaka";

    public static void main(String[] args) {
        junit.textui.TestRunner.run(EndpointReferenceTypeTest.class);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        endpointReferenceType = new EndpointReferenceType(headerType, address);
    }

    public void testGetAndSetMessageInformationHeaderType() {
        assertEquals("MessageInformationHeaderType not set properly in the constructor", headerType, endpointReferenceType.getMessageInformationHeaderType());

        endpointReferenceType.setMessageInformationHeaderType(AddressingConstants.WSA_REPLY_TO);
        assertEquals("MessageInformationHeaderType not set properly in the setter method", AddressingConstants.WSA_REPLY_TO, endpointReferenceType.getMessageInformationHeaderType());
    }

    public void testGetAndSetAddress() {
        assertEquals("Address not set properly in the constructor", address, endpointReferenceType.getAddress());

        String newAddress = "http://www.axis2.com";
        endpointReferenceType.setAddress(newAddress);
        assertEquals("Address not set properly in the setter method", newAddress, endpointReferenceType.getAddress());
    }

    public void testGetAndSetPortType() {
        QName portType = new QName("www.someport.com", "port");
        endpointReferenceType.setPortType(portType);
        assertEquals("PortType not set/get properly", portType, endpointReferenceType.getPortType());
    }

    public void testGetAndSetReferenceProperties() {
        AnyContentType anyContentType = new AnyContentType();
        for(int i=0; i < 10; i++){
           anyContentType.addReferenceValue(new QName("http://www.opensouce.lk/"+i, ""+i), "value "+i*100);
        }
        endpointReferenceType.setReferenceProperties(anyContentType);

        AnyContentType retrievedAnyContentType = endpointReferenceType.getReferenceProperties();
        for(int i=0; i < 10; i++){
           String value = retrievedAnyContentType.getReferenceValue(new QName("http://www.opensouce.lk/"+i, ""+i));
            assertEquals("Input value differs from what is taken out from AnyContentType", value, "value "+i*100);
        }

    }

    public void testGetAndSetReferenceParameters() {
       AnyContentType anyContentType = new AnyContentType();
        for(int i=0; i < 10; i++){
           anyContentType.addReferenceValue(new QName("http://www.opensouce.lk/"+i, ""+i), "value "+i*50);
        }
        endpointReferenceType.setReferenceParameters(anyContentType);

        AnyContentType retrievedAnyContentType = endpointReferenceType.getReferenceParameters();
        for(int i=0; i < 10; i++){
           String value = retrievedAnyContentType.getReferenceValue(new QName("http://www.opensouce.lk/"+i, ""+i));
            assertEquals("Input value differs from what is taken out from AnyContentType", value, "value "+i*50);
        }
    }

    public void testGetAndSetServiceName() {
        ServiceName serviceName = new ServiceName(new QName("www.someservicename.org", "service"));
        endpointReferenceType.setServiceName(serviceName);
        ServiceName retrievedServiceName = endpointReferenceType.getServiceName();
        assertEquals("ServiceName name has not been get/set properly", serviceName.getName(), retrievedServiceName.getName());

        serviceName = new ServiceName(new QName("www.someservicename.org", "service"), "portName");
        endpointReferenceType.setServiceName(serviceName);
        retrievedServiceName = endpointReferenceType.getServiceName();
        assertEquals("ServiceName name has not been get/set properly", serviceName.getName(), retrievedServiceName.getName());
        assertEquals("ServiceName portName has not been get/set properly", serviceName.getPortName(), retrievedServiceName.getPortName());
    }

}