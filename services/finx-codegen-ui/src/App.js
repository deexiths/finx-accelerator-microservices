import React, { useState } from 'react';
import './App.css';
import FormData from './Components/FormData';
import axios from 'axios';

function App() {
  const [generatorResponse, setGeneratorResponse] = useState('');
  
  const handleSubmit = async (formData) => {
    try {
      await axios.post('/api/store-form-data', formData);
      const response = await axios.post('/api/run-generator', formData);
      setGeneratorResponse(response.data.message);
    } catch (error) {
      console.error(error);
    }
  }

  const handleZipFolder = async () => {
    try {
      const response = await axios.post('/api/create-zip', /* */);
      console.log(response.data); // Handle the response as needed
    } catch (error) {
      console.error(error);
    }
  }

  const handleUploadToS3 = async () => {
    try {
      const response = await axios.post('/api/upload-to-s3', /*  */);
      console.log(response.data);

    } catch (error) {
      console.error(error);
    }
  };

  const handleDownload = async () => {
    try {
      
      const response = await axios.get('/api/download');

      const { downloadURL } = response.data;

      // Trigger download using the download URL
      const downloadLink = document.createElement('a');
      downloadLink.href = downloadURL;
      downloadLink.download = 'downloadedFile.zip';
      document.body.appendChild(downloadLink);
      downloadLink.click();
      document.body.removeChild(downloadLink);
    } catch (error) {
      console.error('Error fetching download URL:', error);
    }
  };
  


  return (
    <div className="App">
      <h1>FinCuro Microservice Generator</h1>
      <FormData onSubmit={handleSubmit} />
      <div className="generator-response">
        {generatorResponse && console.log("Generator Response: " + generatorResponse)}
      </div>
      <div className="additional-buttons">
        <button onClick={handleZipFolder}>Zip Folder</button>
        <button onClick={handleUploadToS3}>Upload to S3</button>
        <button onClick={handleDownload}>Download</button>
          
      </div>
    </div>
  );
}

export default App;
