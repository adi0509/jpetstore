import { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";

import { getOrderByOrderId } from "../Store/Actions/order.actions";

import { Col, Alert } from "react-bootstrap";
import {
  PaymentDetails,
  CheckoutForm,
  LineItemTable,
} from "../Components/index";

import { useParams } from "react-router-dom";

const ViewOrderPage = () => {
  const dispatch = useDispatch();
  const { order } = useSelector((state) => state.order);

  const { orderId } = useParams();

  useEffect(() => {
    dispatch(getOrderByOrderId.request(orderId));
  }, []);

  return (
    <Col md="6" className="mx-auto">
      <Alert variant="primary" className="text-dark">
        Order #{orderId} {order.orderdate} 09:20:00
      </Alert>

      <PaymentDetails
        title="Payment Details"
        formData={order.payment}
        readOnly={true}
      />

      <CheckoutForm
        title="Billing Address"
        formData={order.billing}
        readOnly={true}
      />

      <CheckoutForm
        title="Shipping Address"
        formData={order.shipping}
        readOnly={true}
      />

      <LineItemTable />
      <p>
        <strong>Total: $</strong>
      </p>
    </Col>
  );
};

export default ViewOrderPage;
