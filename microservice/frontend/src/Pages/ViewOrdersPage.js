import { useEffect } from "react";

import { useSelector, useDispatch } from "react-redux";

import { ViewOrderTable } from "../Components";

import { getOrdersByUserId } from "../Store/Actions/order.actions";

const ViewOrdersPage = () => {
  const dispatch = useDispatch();
  const { username } = useSelector((state) => state.auth.cred);

  const { orders } = useSelector((state) => state.order);

  useEffect(() => {
    dispatch(getOrdersByUserId.request(username));
  }, []);

  if (username == undefined || username == null) {
    return <h1>Please login to view your orders.</h1>;
  } else {
    return <div>{orders && <ViewOrderTable orders={orders} />}</div>;
  }
};

export default ViewOrdersPage;
