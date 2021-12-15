import { call, put, takeLatest } from "redux-saga/effects";
import { OrderApi } from "../../Services/order.service";
import { GET_ORDER, getOrdersByUserId } from "../Actions/order.actions";

function* fetchOrdersByUserId(action) {
  try {
    const data = yield call(OrderApi.ordersByUserId, action.payload);

    yield put(getOrdersByUserId.success(data));
  } catch (e) {
    yield put(getOrdersByUserId.failure(e));
  }
}

function* orderSaga() {
  yield takeLatest(GET_ORDER.REQUEST, fetchOrdersByUserId);
}

export default orderSaga;
