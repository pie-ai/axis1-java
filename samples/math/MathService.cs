﻿//------------------------------------------------------------------------------
// <autogenerated>
//     This code was generated by a tool.
//     Runtime Version: 1.1.4322.573
//
//     Changes to this file may cause incorrect behavior and will be lost if 
//     the code is regenerated.
// </autogenerated>
//------------------------------------------------------------------------------

// 
// This source code was auto-generated by wsdl, Version=1.1.4322.573.
// 
using System.Diagnostics;
using System.Xml.Serialization;
using System;
using System.Web.Services.Protocols;
using System.ComponentModel;
using System.Web.Services;


/// <remarks/>
[System.Diagnostics.DebuggerStepThroughAttribute()]
[System.ComponentModel.DesignerCategoryAttribute("code")]
[System.Web.Services.WebServiceBindingAttribute(Name="MathSoapBinding", Namespace="http://math.samples/")]
public class MathService : System.Web.Services.Protocols.SoapHttpClientProtocol {
    
    /// <remarks/>
    public MathService() {
        this.Url = "http://localhost:8080/axis/services/Math";
    }
    
    /// <remarks/>
    [System.Web.Services.Protocols.SoapDocumentMethodAttribute("", RequestElementName="Add", RequestNamespace="http://math.samples/", ResponseElementName="AddResponse", ResponseNamespace="http://math.samples/", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
    [return: System.Xml.Serialization.XmlElementAttribute("AddResult")]
    public System.Single add(System.Single A, System.Single B) {
        object[] results = this.Invoke("add", new object[] {
                    A,
                    B});
        return ((System.Single)(results[0]));
    }
    
    /// <remarks/>
    public System.IAsyncResult Beginadd(System.Single A, System.Single B, System.AsyncCallback callback, object asyncState) {
        return this.BeginInvoke("add", new object[] {
                    A,
                    B}, callback, asyncState);
    }
    
    /// <remarks/>
    public System.Single Endadd(System.IAsyncResult asyncResult) {
        object[] results = this.EndInvoke(asyncResult);
        return ((System.Single)(results[0]));
    }
    
    public static void Main(String[] args) {
        MathService m = new MathService();
        Console.WriteLine(m.add(4,5));
    }
}