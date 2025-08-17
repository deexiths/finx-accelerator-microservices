import React from 'react';

const DownloadPage = ({ s3BucketLink, onGenerateMoreMicroservices, onDownloadZip }) => {
  return (
    <div>
      <h2>Result Page</h2>
      <button onClick={onDownloadZip}>Download Zipped Folder</button>
      <button onClick={onGenerateMoreMicroservices}>Generate More Microservices</button>
    </div>
  );
};

export default DownloadPage;
