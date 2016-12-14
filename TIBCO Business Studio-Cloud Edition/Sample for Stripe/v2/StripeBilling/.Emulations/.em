<?xml version="1.0" encoding="ASCII"?>
<emulation:EmulationData xmlns:emulation="http:///emulation.ecore" isBW="true" location="StripeBilling">
  <ProcessNode Id="mashStripe.ListInvoice" Name="mashStripe.ListInvoice" ModelType="BW" moduleName="StripeBilling">
    <Operation Name="get" serviceName="invoice">
      <Inputs Id="StripeBilling_mashStripe.ListInvoice_get_getInput" Name="getInput" isDefault="true"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="mashStripe.createStripeSubscription" Name="mashStripe.createStripeSubscription" ModelType="BW" moduleName="StripeBilling">
    <Operation Name="put" serviceName="v1-package_key">
      <Inputs Id="StripeBilling_mashStripe.createStripeSubscription_put_putInput" Name="putInput" isDefault="true"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="mashStripe.plugIn" Name="mashStripe.plugIn" ModelType="BW" moduleName="StripeBilling">
    <Operation Name="post" serviceName="account">
      <Inputs Id="StripeBilling_mashStripe.plugIn_post_postInput" Name="postInput" isDefault="true"/>
    </Operation>
    <Operation Name="get" serviceName="charges">
      <Inputs Id="StripeBilling_mashStripe.plugIn_get_getInput" Name="getInput" isDefault="true"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="mashStripe.pushPostpaidCalculation" Name="mashStripe.pushPostpaidCalculation" ModelType="BW" moduleName="StripeBilling">
    <Operation Name="post" serviceName="pushcalculation">
      <Inputs Id="StripeBilling_mashStripe.pushPostpaidCalculation_post_postInput" Name="postInput" isDefault="true">
        <informations Name="item" tnsName="tns:" nameSpaceValue="http://xmlns.example.com/20160821233952PLT" nameSpace="xmlns:tns" partType="Type">
          <nameSpaces prefix="tns" nameSapce="http://xmlns.example.com/20160821233952PLT"/>
          <nameSpaces prefix="tns0" nameSapce="http://tns.tibco.com/bw/REST"/>
          <nameSpaces prefix="xs" nameSapce="http://www.w3.org/2001/XMLSchema"/>
          <Parameter Name="item" type="string"/>
          <SoapMessage>SimpleType</SoapMessage>
        </informations>
        <SoapMessage>type</SoapMessage>
      </Inputs>
      <Inputs Id="StripeBilling_mashStripe.pushPostpaidCalculation_post_postInput" Name="postInput" isDefault="true">
        <informations Name="item" tnsName="tns:" nameSpaceValue="http://xmlns.example.com/20160821233952PLT" nameSpace="xmlns:tns" partType="Type">
          <nameSpaces prefix="tns" nameSapce="http://xmlns.example.com/20160821233952PLT"/>
          <nameSpaces prefix="tns0" nameSapce="http://tns.tibco.com/bw/REST"/>
          <nameSpaces prefix="xs" nameSapce="http://www.w3.org/2001/XMLSchema"/>
          <Parameter Name="item" Value="asd" type="string"/>
          <SoapMessage>SimpleType</SoapMessage>
        </informations>
        <SoapMessage>type</SoapMessage>
      </Inputs>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="mashStripe.subprocess.addUserToPackages" Name="mashStripe.subprocess.addUserToPackages" ModelType="BW" moduleName="StripeBilling">
    <Operation Name="callProcess" serviceName="mashStripe.subprocess.addUserToPackages">
      <Inputs Id="StripeBilling_mashStripe.subprocess.addUserToPackages_callProcess_Start" Name="Start" isDefault="true" type="CALLPROCESS"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="mashStripe.subprocess.getListPostpaid" Name="mashStripe.subprocess.getListPostpaid" ModelType="BW" moduleName="StripeBilling">
    <Operation Name="callProcess" serviceName="mashStripe.subprocess.getListPostpaid">
      <Inputs Id="StripeBilling_mashStripe.subprocess.getListPostpaid_callProcess_Start" Name="Start" isDefault="true" type="CALLPROCESS"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="mashStripe.subprocess.putStripeInvoiceItem" Name="mashStripe.subprocess.putStripeInvoiceItem" ModelType="BW" moduleName="StripeBilling">
    <Operation Name="callProcess" serviceName="mashStripe.subprocess.putStripeInvoiceItem">
      <Inputs Id="StripeBilling_mashStripe.subprocess.putStripeInvoiceItem_callProcess_Start" Name="Start" isDefault="true" type="CALLPROCESS"/>
    </Operation>
  </ProcessNode>
</emulation:EmulationData>
