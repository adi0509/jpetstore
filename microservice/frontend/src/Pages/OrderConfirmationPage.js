import { useState } from "react";
import { CheckoutForm, LineItemTable, PaymentDetails } from "../Components";
import { Col, Button, Alert } from "react-bootstrap";

const OrderConfirmationPage = () => {
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

  return (
    <>
      <h5 className="mb-2 mt-2 text-center">
        Please confirm the information below and then press continue...
      </h5>
      <Col md="6" className="mx-auto">
        <Alert variant="primary" className="text-dark">
          Order #12997 2021/12/12 09:20:00
        </Alert>

        <CheckoutForm
          title="Billing Address"
          formData={billingDetails}
          readOnly={true}
        />

        <CheckoutForm
          title="Shipping Address"
          formData={billingDetails}
          readOnly={true}
        />
        <Col className="mx-auto mb-3" md={6}>
          <Button className="btn btn-success mx-auto">Confirm</Button>
        </Col>
      </Col>
    </>
  );
};

export default OrderConfirmationPage;
