import React, { useState } from "react";
import { Form, Input, Upload, Button, message } from "antd";
import { UploadOutlined } from "@ant-design/icons";
import axios from "axios";

function ResumeForm({ onResult }) {
  const [loading, setLoading] = useState(false);

  const onFinish = async ({ jobDescription, resume }) => {
    setLoading(true);
    console.log("jobDescription: ", jobDescription);
    console.log("resume: ", resume);
    const formData = new FormData();
    formData.append("jobDesc", jobDescription);
    formData.append("resumePdf", resume.fileList[0].originFileObj);

    try {
      const { data } = await axios.post("/api/analyze", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      console.log("result: ", data);
      onResult(data);
    } catch (e) {
      message.error("Failed to analyze. Please try again.");
    }
    setLoading(false);
  };

  return (
    <>
      <div>
        <Form layout="vertical" onFinish={onFinish}>
          <Form.Item
            label="Job Description"
            name="jobDescription"
            rules={[
              { required: true, message: "Please input the job description" },
            ]}
          >
            <Input.TextArea
              rows={6}
              placeholder="Paste the job description here..."
            />
          </Form.Item>
          <Form.Item
            name="resume"
            label="Upload Resume PDF"
            valuePropName="file"
            getValueFromEvent={(e) => (e.file ? e : null)}
            rules={[
              { required: true, message: "Please upload your resume (PDF)" },
            ]}
          >
            <Upload
              maxCount={1}
              accept="application/pdf"
              beforeUpload={() => false}
              showUploadList={true}
            >
              <Button icon={<UploadOutlined />}>Select PDF File</Button>
            </Upload>
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit" loading={loading}>
              Analyze Resume
            </Button>
          </Form.Item>
        </Form>
      </div>
    </>
  );
}

export default ResumeForm;
