import React from "react";
import { Layout } from "antd";
const { Footer } = Layout;

function FooterBar() {
  return (
    <Footer style={{
      background: "#fafafa",
      textAlign: "center",
      fontSize: 16,
      position: "fixed",
      left: 0,
      bottom: 0,
      width: "100%",
      boxShadow: "0 -2px 8px #f0f1f2"
    }}>
      project owned by sriram
    </Footer>
  );
}
export default FooterBar;
