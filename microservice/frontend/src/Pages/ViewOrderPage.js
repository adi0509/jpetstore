import { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";

import { getOrderByOrderId } from "../Store/Actions/order.actions";

import { Col } from "react-bootstrap";
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
      <PaymentDetails formData={order} readOnly={true} />
      <CheckoutForm formData={order} readOnly={true} />
      <CheckoutForm formData={order} readOnly={true} />
      {/* <LineItemTable /> */}
    </Col>
  );
};

export default ViewOrderPage;
