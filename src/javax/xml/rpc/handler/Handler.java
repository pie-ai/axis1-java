/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Axis" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

package javax.xml.rpc.handler;

import javax.xml.rpc.namespace.QName;

/**
 * A handler provides a mechanism for processing of service
 * context, plugging in additional RPC processing behavior and
 * enhancing functionality of a JAX-RPC runtime system.
 * <p>A JAX-RPC handler is required to implement the
 * <code>javax.xml.rpc.handler.Handler</code> interface.
 */
public interface Handler {

    /**
     * The handleRequest method processes the request message.
     *
     * @param context - MessageContext parameter provides access to the request
     *                  message.
     *
     * @throws JAXRPCException - if any handler specific runtime error happens.
     *         The HandlerChain terminates the further processing of this
     *         handler chain.
     *         SOAPFaultException - if SOAP fault is generated by this handler.
     *         The HandlerChain catches this exception, terminates the further
     *         processing of the request handlers in this handler chain and
     *         invokes the handleFault method on this handler
     */
    public boolean handleRequest(MessageContext context);

    /**
     * The handleResponse method processes the response message.
     *
     * @param context - MessageContext parameter provides access to the response
     *                  message
     *
     * @return Processing mode.  Return true to indicate continued processing of
     *         the response handler chain. The HandlerChain invokes the
     *         handleRespons method on the next Handler in the handler chain. 
     *         Return false to indicate blocking of the response handler chain.
     *         In this  case, no other response handlers in the handler chain
     *         are invoked.
     *
     * @throws JAXRPCException - if any handler specific runtime error happens.
     *         The HandlerChain terminates the further processing of this handler
     *         chain.
     */
    public boolean handleResponse(MessageContext context);

    /**
     * The handleFault method processes the SOAP faults based on the SOAP
     * message processing model.
     *
     * @param  context - MessageContext parameter provides access to the SOAP
     *         message.
     * @throws JAXRPCException - if any handler specific runtime error
     */
    public boolean handleFault(MessageContext context);

    /**
     * The init method to enable the Handler instance to initialize itself. The
     * init method passes the handler configuration properties as a Map
     * instance. These configuration properties are used to configure the
     * Handler (for example: setup access to an external resource or service)
     * during initialization.
     *
     * @param HandlerInfo - Configuration for the initialization of this handler
     *
     * @throws JAXRPCException - If initialization of the handler fails
     */
    public abstract void init(HandlerInfo config);

    /**
     * The destroy method indicates the end of lifecycle for a Handler
     * instance. An Handler implementation class should release any
     * resources that it had acquired over its lifecycle. The JAX-RPC
     * runtime system invokes the destroy method, when the Handler
     * instance is no longer needed.
     * @throws  JAXRPCException  If any error during destroy
     */
    public abstract void destroy();

    /**
     * Gets the header blocks processed by this Handler instance.
     *
     * @return The header blocks.
     */
    public QName[] getHeaders();
}
