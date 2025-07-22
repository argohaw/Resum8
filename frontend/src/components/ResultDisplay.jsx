import React from "react";
import { Card, Typography, Table} from "antd";
const { Title } = Typography;

function ResultDisplay({ result }) {
  if (!result) return null;

  const tableData = Object.entries(result).map(([key, value], index) => ({
    key: index,
    attribute: key.charAt(0).toUpperCase() + key.slice(1),
    detail: value,
  }));

  const columns = [
    {
      title: "Attribute",
      dataIndex: "attribute",
      key: "attribute",
      width: 150,
    },
    {
      title: "Detail",
      dataIndex: "detail",
      key: "detail",
    },
  ];

  return (
    <Card>
      <Title level={4}>Resume Analysis Result</Title>
      <Table columns={columns} dataSource={tableData} pagination={false} />
    </Card>
  );
}

export default ResultDisplay;
