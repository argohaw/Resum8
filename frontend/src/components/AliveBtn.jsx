import React, { useEffect, useState } from "react";
import { Button, Tooltip } from "antd";
import axios from "axios";

function AliveBtn() {
  const [alive, setAlive] = useState(false);
  const [ollamaAlive, setOllamaAlive] = useState(false);
  const [loading, setLoading] = useState(false);

  const checkAlive = async () => {
    setLoading(true);
    try {
      const resp = await axios.get("http://localhost:8080/api/alive");
      setAlive(resp.status === 200);
    } catch {
      setAlive(false);
    }
    setLoading(false);
  };

  const checkOllamaAlive = async () => {
    try {
      const resp = await axios.get("http//localhost:8080/api/ollama-alive");
      setOllamaAlive(resp.status === 200);
    } catch {
      setOllamaAlive(false);
    }
  };

  useEffect(() => {
    checkAlive();
    checkOllamaAlive();

    const interval = setInterval(() => {
      checkAlive();
      checkOllamaAlive();
    }, 10000);

    return () => clearInterval(interval);
  }, []);

  return (
    <>
      <Tooltip title="Backend status (auto-updates every 10s)">
        <Button
          type="primary"
          shape="circle"
          size="large"
          loading={loading}
          onClick={checkAlive}
          style={{
            backgroundColor: alive ? "#5cb85c" : "#d9534f",
            borderColor: alive ? "#5cb85c" : "#d9534f",
            marginLeft: 16,
          }}
        />
      </Tooltip>
      <Tooltip title="Ollama status (auto-updates every 10s)">
        <Button
          type="primary"
          shape="circle"
          size="large"
          loading={loading}
          onClick={checkOllamaAlive}
          style={{
            backgroundColor: alive ? "#5cb85c" : "#d9534f",
            borderColor: alive ? "#5cb85c" : "#d9534f",
            marginLeft: 16,
          }}
        />
      </Tooltip>
    </>
  );
}
export default AliveBtn;
