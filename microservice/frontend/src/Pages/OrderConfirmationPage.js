import { useState } from "react";
import { useSelector, useDispatch } from "react-redux";

import { todaysDate, todaysTime } from "../Utils/helper";

import { CheckoutForm } from "../Components";
import { Col, Button, Alert } from "react-bootstrap";

import { placeOrder } from "../Store/Actions/order.actions";

const OrderConfirmationPage = () => {
  const dispatch = useDispatch();

  const { username } = useSelector((state) => state.auth.cred);
  const { billingDetails, shippingDetails, paymentDetails, lineItems } =
    useSelector((state) => state.order);

  const handlePlaceOrder = () => {
    const d = new Date();
    const orderDetails = {
      orderId: "2",
      userId: username,
      orderdate: todaysDate(),
      shipAddr1: shippingDetails.addr1,
      shipAddr2: shippingDetails.addr2,
      shipcity: shippingDetails.city,
      shipstate: shippingDetails.state,
      shipzip: shippingDetails.zip,
      shipcountry: shippingDetails.country,
      billaddr1: billingDetails.addr1,
      billaddr2: billingDetails.addr2,
      billcity: billingDetails.city,
      billstate: billingDetails.state,
      billzip: billingDetails.zip,
      billcountry: billingDetails.country,
      courier: "1",
      totalprice: "1",
      billtofirstname: billingDetails.fName,
      billtolastname: billingDetails.lName,
      shiptofirstname: shippingDetails.fName,
      shiptolastname: shippingDetails.lName,
      creditcard: paymentDetails.cardNumber,
      exprdate: paymentDetails.expiryDate,
      cardtype: paymentDetails.cardType,
      locale: "1",
      lineItems: [...lineItems],
    };

    console.log(orderDetails);
    dispatch(placeOrder.request(orderDetails));
  };

  return (
    <>
      <h5 className="mb-2 mt-2 text-center">
        Please confirm the information below and then press continue...
      </h5>
      <Col md="6" className="mx-auto">
        <Alert variant="primary" className="text-dark">
          Order {todaysDate() + " " + todaysTime()}
        </Alert>

        <CheckoutForm
          title="Billing Address"
          formData={billingDetails}
          readOnly={true}
        />

        <CheckoutForm
          title="Shipping Address"
          formData={shippingDetails}
          readOnly={true}
        />
        <Col className="mx-auto mb-3" md={6}>
          <Button
            className="btn btn-success mx-auto"
            onClick={handlePlaceOrder}
          >
            Confirm
          </Button>
        </Col>
      </Col>
    </>
  );
};

export default OrderConfirmationPage;
