<?xml version="1.0" encoding="ASCII"?>
<emulation:EmulationData xmlns:emulation="http:///emulation.ecore" isBW="true" location="DropboxConnector">
  <ProcessNode Id="dropboxconnector.DeleteFileFolder" Name="dropboxconnector.DeleteFileFolder" ModelType="BW" moduleName="DropboxConnector">
    <Operation Name="post" serviceName="deletefilefolder">
      <Inputs Id="DropboxConnector_dropboxconnector.DeleteFileFolder_post_postInput" Name="postInput" isDefault="true"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="dropboxconnector.DownloadFile" Name="dropboxconnector.DownloadFile" ModelType="BW" moduleName="DropboxConnector">
    <Operation Name="get" serviceName="downloadfile">
      <Inputs Id="DropboxConnector_dropboxconnector.DownloadFile_get_getInput" Name="getInput" isDefault="true"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="dropboxconnector.GetMetaData" Name="dropboxconnector.GetMetaData" ModelType="BW" moduleName="DropboxConnector">
    <Operation Name="post" serviceName="getmetadata">
      <Inputs Id="DropboxConnector_dropboxconnector.GetMetaData_post_postInput" Name="postInput" isDefault="true"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="dropboxconnector.MoveCopyFile" Name="dropboxconnector.MoveCopyFile" ModelType="BW" moduleName="DropboxConnector">
    <Operation Name="put" serviceName="movecopyfile">
      <Inputs Id="DropboxConnector_dropboxconnector.MoveCopyFile_put_putInput" Name="putInput" isDefault="true"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="dropboxconnector.SearchFile" Name="dropboxconnector.SearchFile" ModelType="BW" moduleName="DropboxConnector">
    <Operation Name="post" serviceName="searchfile">
      <Inputs Id="DropboxConnector_dropboxconnector.SearchFile_post_postInput" Name="postInput" isDefault="true"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="dropboxconnector.SessionFileUpload" Name="dropboxconnector.SessionFileUpload" ModelType="BW" moduleName="DropboxConnector">
    <Operation Name="post" serviceName="resource">
      <Inputs Id="DropboxConnector_dropboxconnector.SessionFileUpload_post_postInput" Name="postInput" isDefault="true"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="dropboxconnector.UploadFile" Name="dropboxconnector.UploadFile" ModelType="BW" moduleName="DropboxConnector">
    <Operation Name="post" serviceName="uploadfile">
      <Inputs Id="DropboxConnector_dropboxconnector.UploadFile_post_postInput" Name="postInput" isDefault="true"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="dropboxconnector.CreateFolder" Name="dropboxconnector.CreateFolder" ModelType="BW" moduleName="DropboxConnector">
    <Operation Name="post" serviceName="CreateFolder">
      <Inputs Id="DropboxConnector_dropboxconnector.CreateFolder_post_postInput" Name="postInput" isDefault="true"/>
    </Operation>
  </ProcessNode>
  <ProcessNode Id="dropboxconnector.ListFolder" Name="dropboxconnector.ListFolder" ModelType="BW" moduleName="DropboxConnector">
    <Operation Name="post" serviceName="listfolder">
      <Inputs Id="DropboxConnector_dropboxconnector.ListFolder_post_postInput" Name="postInput" isDefault="true"/>
    </Operation>
  </ProcessNode>
</emulation:EmulationData>
