<?xml version="1.0" encoding="ASCII"?>
<emulation:EmulationData xmlns:emulation="http:///emulation.ecore" isBW="true" location="GoogleAPISuite.module">
  <ProcessNode Id="googleapisuite.googleplus.GooglePlusAPI" Name="googleapisuite.googleplus.GooglePlusAPI" ModelType="BW" moduleName="GoogleAPISuite.module">
    <Operation Name="get" serviceName="getUserActivities">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.googleplus.GooglePlusAPI_get_getInput" Name="getInput" isDefault="true"/>
    </Operation>
    <Operation Name="get" serviceName="listCommentsForActivity">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.googleplus.GooglePlusAPI_get_getInput" Name="getInput" isDefault="true">
        <informations Name="parameters" tnsName="tns:" nameSpaceValue="http://xmlns.example.com/20160811094907PLT" nameSpace="xmlns:tns" partType="Element">
          <nameSpaces prefix="tns" nameSapce="http://xmlns.example.com/20160811094907PLT"/>
          <nameSpaces prefix="tns0" nameSapce="http://xmlns.example.com/GetProfile/headerParameters"/>
          <nameSpaces prefix="tns1" nameSapce="http://tns.tibco.com/bw/REST"/>
          <nameSpaces prefix="tns2" nameSapce="http://xmlns.example.com/GetProfile/parameters"/>
          <nameSpaces prefix="tns3" nameSapce="http://xmlns.example.com/GooglePlusAPI/parameters"/>
          <nameSpaces prefix="xs" nameSapce="http://www.w3.org/2001/XMLSchema"/>
          <Parameter Name="tns2:getProfile1GetParameters">
            <parameters Name="tns2:accessToken" type="string" nameSpace="http://xmlns.example.com/GetProfile/parameters"/>
            <parameters Name="tns2:tokenType" type="string" nameSpace="http://xmlns.example.com/GetProfile/parameters" canDelete="true"/>
          </Parameter>
        </informations>
        <SoapMessage>type</SoapMessage>
      </Inputs>
    </Operation>
    <Operation Name="get" serviceName="searchActivities">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.googleplus.GooglePlusAPI_get_getInput" Name="getInput" isDefault="true"/>
    </Operation>
    <Operation Name="get" serviceName="getProfile">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.googleplus.GooglePlusAPI_get_getInput" Name="getInput" isDefault="true"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="googleapisuite.googleplus.userapi.getProfile" Name="googleapisuite.googleplus.userapi.getProfile" ModelType="BW" moduleName="GoogleAPISuite.module">
    <Operation Name="callProcess" serviceName="googleapisuite.googleplus.userapi.getProfile">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.googleplus.userapi.getProfile_callProcess_Start" Name="Start" isDefault="true" type="CALLPROCESS"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="googleapisuite.googleplus.userapi.getUserActivities" Name="googleapisuite.googleplus.userapi.getUserActivities" ModelType="BW" moduleName="GoogleAPISuite.module">
    <Operation Name="callProcess" serviceName="googleapisuite.googleplus.userapi.getUserActivities">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.googleplus.userapi.getUserActivities_callProcess_Start" Name="Start" isDefault="true" type="CALLPROCESS"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="googleapisuite.googleplus.userapi.listCommentsForActivities" Name="googleapisuite.googleplus.userapi.listCommentsForActivities" ModelType="BW" moduleName="GoogleAPISuite.module">
    <Operation Name="callProcess" serviceName="googleapisuite.googleplus.userapi.listCommentsForActivities">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.googleplus.userapi.listCommentsForActivities_callProcess_Start" Name="Start" isDefault="true" type="CALLPROCESS"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="googleapisuite.googleplus.userapi.searchActivities" Name="googleapisuite.googleplus.userapi.searchActivities" ModelType="BW" moduleName="GoogleAPISuite.module">
    <Operation Name="callProcess" serviceName="googleapisuite.googleplus.userapi.searchActivities">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.googleplus.userapi.searchActivities_callProcess_Start" Name="Start" isDefault="true" type="CALLPROCESS"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="googleapisuite.maps.directionsAPI" Name="googleapisuite.maps.directionsAPI" ModelType="BW" moduleName="GoogleAPISuite.module">
    <Operation Name="get" serviceName="directions">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.maps.directionsAPI_get_getInput" Name="getInput" isDefault="true"/>
    </Operation>
    <Operation Name="get" serviceName="distanceMatrix">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.maps.directionsAPI_get_getInput" Name="getInput" isDefault="true"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="googleapisuite.maps.directionsapi.directionsSub" Name="googleapisuite.maps.directionsapi.directionsSub" ModelType="BW" moduleName="GoogleAPISuite.module">
    <Operation Name="callProcess" serviceName="googleapisuite.maps.directionsapi.directionsSub">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.maps.directionsapi.directionsSub_callProcess_Start" Name="Start" isDefault="true" type="CALLPROCESS"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="googleapisuite.maps.directionsapi.distanceMatrix" Name="googleapisuite.maps.directionsapi.distanceMatrix" ModelType="BW" moduleName="GoogleAPISuite.module">
    <Operation Name="callProcess" serviceName="googleapisuite.maps.directionsapi.distanceMatrix">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.maps.directionsapi.distanceMatrix_callProcess_Start" Name="Start" isDefault="true" type="CALLPROCESS"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="googleapisuite.maps.placesAPI" Name="googleapisuite.maps.placesAPI" ModelType="BW" moduleName="GoogleAPISuite.module">
    <Operation Name="get" serviceName="nearby">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.maps.placesAPI_get_getInput" Name="getInput" isDefault="true"/>
    </Operation>
    <Operation Name="get" serviceName="places">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.maps.placesAPI_get_getInput" Name="getInput" isDefault="true"/>
    </Operation>
    <Operation Name="get" serviceName="placesearch">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.maps.placesAPI_get_getInput" Name="getInput" isDefault="true"/>
    </Operation>
    <Operation Name="get" serviceName="elevation">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.maps.placesAPI_get_getInput" Name="getInput" isDefault="true"/>
    </Operation>
    <Operation Name="get" serviceName="latlng">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.maps.placesAPI_get_getInput" Name="getInput" isDefault="true"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="googleapisuite.maps.placesapi.elevationSub" Name="googleapisuite.maps.placesapi.elevationSub" ModelType="BW" moduleName="GoogleAPISuite.module">
    <Operation Name="callProcess" serviceName="googleapisuite.maps.placesapi.elevationSub">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.maps.placesapi.elevationSub_callProcess_Start" Name="Start" isDefault="true" type="CALLPROCESS"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="googleapisuite.maps.placesapi.getLatLng" Name="googleapisuite.maps.placesapi.getLatLng" ModelType="BW" moduleName="GoogleAPISuite.module">
    <Operation Name="callProcess" serviceName="googleapisuite.maps.placesapi.getLatLng">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.maps.placesapi.getLatLng_callProcess_Start" Name="Start" isDefault="true" type="CALLPROCESS"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="googleapisuite.maps.placesapi.nearbySub" Name="googleapisuite.maps.placesapi.nearbySub" ModelType="BW" moduleName="GoogleAPISuite.module">
    <Operation Name="callProcess" serviceName="googleapisuite.maps.placesapi.nearbySub">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.maps.placesapi.nearbySub_callProcess_Start" Name="Start" isDefault="true" type="CALLPROCESS"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="googleapisuite.maps.placesapi.placeSearchSub" Name="googleapisuite.maps.placesapi.placeSearchSub" ModelType="BW" moduleName="GoogleAPISuite.module">
    <Operation Name="callProcess" serviceName="googleapisuite.maps.placesapi.placeSearchSub">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.maps.placesapi.placeSearchSub_callProcess_Start" Name="Start" isDefault="true" type="CALLPROCESS"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="googleapisuite.maps.placesapi.placesDetailsSub" Name="googleapisuite.maps.placesapi.placesDetailsSub" ModelType="BW" moduleName="GoogleAPISuite.module">
    <Operation Name="callProcess" serviceName="googleapisuite.maps.placesapi.placesDetailsSub">
      <Inputs Id="GoogleAPISuite.module_googleapisuite.maps.placesapi.placesDetailsSub_callProcess_Start" Name="Start" isDefault="true" type="CALLPROCESS"/>
    </Operation>
  </ProcessNode>
</emulation:EmulationData>
