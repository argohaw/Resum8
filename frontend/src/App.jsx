import React, { useState } from "react";
import { Layout, Row, Col } from "antd";
import ResumeForm from "./components/ResumeForm";
import ResultDisplay from "./components/ResultDisplay";
import TitleBar from "./components/TitleBar";
import FooterBar from "./components/FooterBar";

function App() {
  const [result, setResult] = useState(null);

  return (
    <Layout style={{ minHeight: "100vh" }}>
      <TitleBar />
      <Layout.Content style={{ padding: "2rem", marginTop: 64, marginBottom: 48 }}>
        <Row gutter={32}>
          <Col span={12}>
            <ResumeForm onResult={setResult} />
          </Col>
          <Col span={12}>
            <ResultDisplay result={result} />
          </Col>
        </Row>
      </Layout.Content>
      <FooterBar />
    </Layout>
  );
}

export default App;
