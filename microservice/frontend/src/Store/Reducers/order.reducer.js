import * as OrderActions from "../Actions/order.actions";

const initialState = {
  orders: [],
  paymentDetails: {},
  billingDetails: {},
  shippingDetails: {},
  lineItems: [],
  confirmOrderDetails: {},
  orderDetails: {},
  nextId: "",
  getOrders: false,
  getNextId: false,
  settingPaymentDetails: false,
  settingBillingDetails: false,
  settingShippingDetails: false,
  settingConfirmOrderDetails: false,
  errorNextId: null,
  errorOrders: null,
  placingOrder: false,
  errorPlacingOrder: null,
};

export default function (state = initialState, action) {
  switch (action.type) {
    case OrderActions.GET_ORDER.REQUEST:
      return {
        ...state,
        getOrders: true,
      };
    case OrderActions.GET_ORDER.SUCCESS:
      console.log(action.payload);
      return {
        ...state,
        orders: action.payload.data,
        getOrders: false,
      };
    case OrderActions.GET_ORDER.FAILURE:
      return {
        ...state,
        getOrders: false,
        errorOrders: action.payload,
      };
    case OrderActions.SET_LINEITEMS:
      return {
        ...state,
        lineItems: action.payload,
      };
    case OrderActions.SET_PAYMENT_DETAILS:
      return {
        ...state,
        paymentDetails: action.payload,
      };
    case OrderActions.SET_BILLING_DETAILS:
      return {
        ...state,
        billingDetails: action.payload,
      };
    case OrderActions.SET_SHIPPING_DETAILS:
      return {
        ...state,
        shippingDetails: action.payload,
      };
    default:
      return state;
  }
}
