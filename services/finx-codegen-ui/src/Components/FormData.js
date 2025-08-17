import React, { useState } from 'react';
import '../assets/styles/FormData.css';

const FormData = ({ onSubmit }) => {
  const initialFormData = {
    groupId: '',
    swaggerDocumentPath: '',
    applicationName: '',
    version: '',
    basePath: '',
    applicationTargetPath: '',
    controllersPackage: '',
  };

  const [Data, setFormData] = useState(initialFormData);

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(Data);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...Data, [name]: value });
  };

  return (
    <form onSubmit={handleSubmit} className="form-container">
      <div className="form-group">
        <label htmlFor="groupId">Group ID:</label>
        <input type="text" id="groupId" name="groupId" value={Data.groupId} onChange={handleChange} />
      </div>
      <div className="form-group">
        <label htmlFor="swaggerDocumentPath">Swagger Document Path:</label>
        <input type="text" id="swaggerDocumentPath" name="swaggerDocumentPath" value={Data.swaggerDocumentPath} onChange={handleChange} />
      </div>
      <div className="form-group">
        <label htmlFor="applicationName">Application Name:</label>
        <input type="text" id="applicationName" name="applicationName" value={Data.applicationName} onChange={handleChange} />
      </div>
      <div className="form-group">
        <label htmlFor="version">Version:</label>
        <input type="text" id="version" name="version" value={Data.version} onChange={handleChange} />
      </div>
      <div className="form-group">
        <label htmlFor="basePath">Base Path:</label>
        <input type="text" id="basePath" name="basePath" value={Data.basePath} onChange={handleChange} />
      </div>
      <div className="form-group">
        <label htmlFor="applicationTargetPath">Application Target Path:</label>
        <input type="text" id="applicationTargetPath" name="applicationTargetPath" value={Data.applicationTargetPath} onChange={handleChange} />
      </div>
      <div className="form-group">
        <label htmlFor="controllersPackage">Controllers Package:</label>
        <input type="text" id="controllersPackage" name="controllersPackage" value={Data.controllersPackage} onChange={handleChange} />
      </div>
      <button type="submit" className="submit-button">Submit</button>
    </form>
  );
};

export default FormData;
