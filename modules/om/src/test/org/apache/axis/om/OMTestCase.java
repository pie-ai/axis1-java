/*
 * Copyright 2004,2005 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.axis.om;

import org.apache.axis.om.impl.llom.builder.StAXSOAPModelBuilder;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class OMTestCase extends AbstractTestCase {
    protected static final String IN_FILE_NAME = "soap/soapmessage.xml";
    protected StAXSOAPModelBuilder builder;
    protected OMFactory ombuilderFactory;

    protected SOAPEnvelope soapEnvelope;

    public OMTestCase(String testName) {
        super(testName);
        ombuilderFactory = OMFactory.newInstance();
    }

    protected void setUp() throws Exception {
        super.setUp();
        soapEnvelope = (SOAPEnvelope) getOMBuilder("").getDocumentElement();
    }

    protected StAXSOAPModelBuilder getOMBuilder(String fileName) throws Exception {
        if (fileName == "" || fileName == null) {
            fileName = IN_FILE_NAME;
        }
        XMLStreamReader parser = XMLInputFactory.newInstance().createXMLStreamReader(new FileReader(getTestResourceFile(fileName)));
        builder = new StAXSOAPModelBuilder(OMFactory.newInstance(), parser);
        return builder;
    }

    protected StAXSOAPModelBuilder getOMBuilder(InputStream in) throws Exception {
        XMLStreamReader parser = XMLInputFactory.newInstance().createXMLStreamReader(in);
        builder = new StAXSOAPModelBuilder(OMFactory.newInstance(), parser);
        return builder;
    }

    protected XMLStreamWriter getStAXStreamWriter(OutputStream out) throws XMLStreamException {
        return XMLOutputFactory.newInstance().createXMLStreamWriter(out);
    }


}