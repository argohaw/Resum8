import React from "react";
import { Layout } from "antd";
import AliveBtn from "./AliveBtn";

const { Header } = Layout;

function TitleBar() {
  return (
    <Header
      style={{
        background: "#fff",
        display: "flex",
        alignItems: "center",
        justifyContent: "space-between",
        boxShadow: "0 2px 8px #f0f1f2",
      }}
    >
      <span style={{ width: 80 }} /> {/* Spacing for left side */}
      <div style={{
        flex: 1,
        textAlign: "center",
        fontSize: 28,
        fontWeight: 600,
        letterSpacing: "0.075em"
      }}>
        RESUM8
      </div>
      <AliveBtn />
    </Header>
  );
}
export default TitleBar;
