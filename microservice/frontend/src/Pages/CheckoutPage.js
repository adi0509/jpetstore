import { useState } from "react";

import { Row, Col, Form, Button } from "react-bootstrap";

import { CheckoutForm, PaymentDetails } from "../Components";

const CheckoutPage = () => {
  const [paymentDetails, setPaymentDetails] = useState({
    cardType: "test data",
    cardNumber: "test data",
    expiryDate: "test data",
  });

  const [billingDetails, setBillingDetails] = useState({
    cardType: "test data",
    cardNumber: "test data",
    expiryDate: "test data",
    fName: "test data",
    lName: "test data",
    addr1: "test data",
    addr2: "test data",
    city: "test data",
    state: "test data",
    zip: "test data",
    country: "test data",
  });

  const handleInputField = (name) => (e) => {
    e.preventDefault();

    setBillingDetails({
      ...billingDetails,
      [name]: e.target.value,
    });
  };

  return (
    <Col className="mx-auto" md={6}>
      <PaymentDetails
        formData={billingDetails}
        handleInputField={handleInputField}
        title="Payment details"
      />

      <CheckoutForm
        formData={billingDetails}
        handleInputField={handleInputField}
      />

      <Row className="mb-2">
        <Col md={1} className="row">
          <Form.Check
            type="checkbox"
            id="default-checkbox"
            //   onChange={(e) => console.log(e)}
            //   checked={formData.listOption}
            onChange={(e) => console.log(e.target.checked)}
            //   onChange={handleInputField("listOption")}
          />
        </Col>
        <Col md={5} className="mt-0">
          <Form.Label>Ship to different address...</Form.Label>
        </Col>
      </Row>

      <Col className="mx-auto mb-3" md={6}>
        <Button className="btn btn-success mx-auto">Continue</Button>
      </Col>
    </Col>
  );
};

export default CheckoutPage;
