import { Row, Col } from "react-bootstrap";

import { HomePageList } from "../Components/index";

const HomePage = () => {
  return (
    <div>
      <Row md={12}>
        <Col md={4}>
          <HomePageList />
        </Col>
        <Col>images will come here</Col>
      </Row>
    </div>
  );
};
export default HomePage;
