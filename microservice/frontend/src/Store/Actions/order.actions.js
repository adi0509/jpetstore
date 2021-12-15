import { createRequestTypes, action } from "../../Utils/redux";

export const GET_ORDER = createRequestTypes("GET_ORDER");
export const SET_LINEITEMS = "SET_LINEITEMS";
export const SET_PAYMENT_DETAILS = "SET_PAYMENT_DETAILS";
export const SET_BILLING_DETAILS = "SET_BILLING_DETAILS";
export const SET_SHIPPING_DETAILS = "SET_SHIPPING_DETAILS";

export const getOrdersByUserId = {
  request: (userId) => action(GET_ORDER.REQUEST, userId),
  success: (orders) => action(GET_ORDER.SUCCESS, orders),
  failure: (error) => action(GET_ORDER.FAILURE, error),
};

export const setLineItems = {
  request: (lineItems) => action(SET_LINEITEMS, lineItems),
};

export const setPaymentDetails = {
  request: (paymentDetails) => action(SET_PAYMENT_DETAILS, paymentDetails),
};

export const setBillingDetails = {
  request: (billingDetails) => action(SET_BILLING_DETAILS, billingDetails),
};

export const setShippingDetails = {
  request: (shippingDetails) => action(SET_SHIPPING_DETAILS, shippingDetails),
};
